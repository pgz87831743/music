package jx.pgz.server.impl;


import jx.pgz.dao.entity.SysFile;
import jx.pgz.dao.service.SysFileService;
import jx.pgz.enums.FileTypeEnum;
import jx.pgz.server.FilesServiceFace;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilesServiceFaceImpl implements FilesServiceFace, InitializingBean {

    @Resource
    SysFileService filesService;


    @Value("${attachment.rootPath}")
    public String filePath;

    @Value("${attachment.accessPath}")
    public String accessPath;

    @Value("${attachment.api}")
    public String accessApi;

    @Resource
    HttpServletResponse response;


    @Override
    public List<SysFile> upload(MultipartFile[] files, FileTypeEnum fileTypeEnum) {

        List<SysFile> filesInfo = new ArrayList<>();
        for (MultipartFile file : files) {
            SysFile fileSource = new SysFile();
            try (InputStream in = file.getInputStream()) {
                String md5Hex = DigestUtils.md5DigestAsHex(in);
                String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
                fileSource.setPath(fileTypeEnum.toString() + "/" + md5Hex + suffix);
                fileSource.setName(file.getOriginalFilename());
                fileSource.setCreateTime(LocalDateTime.now());
                fileSource.setMd5(md5Hex);
                filesInfo.add(fileSource);
            } catch (Exception e) {
                throw new RuntimeException("文件上传失败");
            }

            try (OutputStream out = new FileOutputStream(filePath + fileSource.getPath())) {
                FileCopyUtils.copy(file.getInputStream(), out);
            } catch (Exception e) {
                throw new RuntimeException("文件保存失败");
            }

        }

        filesService.saveBatch(filesInfo);

        filesInfo.forEach(s -> {
            s.setUrl(accessApi + s.getPath());
        });

        return filesInfo;


    }

    @Override
    public void download(String fileId) {
        SysFile attachment = filesService.getById(fileId);
        if (attachment != null) {
            String filePath = this.filePath + attachment.getPath();
            byte[] bytes;
            try {
                bytes = FileCopyUtils.copyToByteArray(new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException("文件下载失败");
            }
            try {
                response.setContentType("application/octet-stream;charset=UTF_8");
                response.setHeader("Content-Disposition", "attachment;filename=" +
                        //要保存的文件名
                        new String(attachment.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                response.getOutputStream().write(bytes);
            } catch (IOException e) {
                throw new RuntimeException("流异常");
            }
        }
    }


    @Override
    public boolean delete(String fileId) {
        SysFile files = filesService.getById(fileId);
        if (files != null) {
            String filePath = this.filePath + files.getPath();
            File file = new File(filePath);
            if (file.exists()) {
                filesService.removeById(fileId);
                return file.delete();
            }
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FileTypeEnum[] values = FileTypeEnum.values();
        for (FileTypeEnum value : values) {
            String dirName = value.toString();
            File file = new File(filePath + dirName);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
