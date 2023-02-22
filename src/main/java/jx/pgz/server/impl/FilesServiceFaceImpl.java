package jx.pgz.server.impl;


import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import jx.pgz.dao.sys.entity.SysFile;
import jx.pgz.dao.sys.service.SysFileService;
import jx.pgz.dao.yw.entity.Position;
import jx.pgz.dao.yw.entity.Running;
import jx.pgz.dao.yw.service.PositionService;
import jx.pgz.dao.yw.service.RunningService;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilesServiceFaceImpl implements FilesServiceFace, InitializingBean {

    @Resource
    private SysFileService filesService;


    @Value("${attachment.rootPath}")
    private String filePath;

    @Value("${attachment.accessPath}")
    private String accessPath;

    @Value("${attachment.api}")
    private String accessApi;

    @Resource
    private HttpServletResponse response;


    @Resource
    private PositionService positionService;

    @Resource
    private RunningService runningService;


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

            try (OutputStream out = Files.newOutputStream(Paths.get(filePath + fileSource.getPath()))) {
                FileCopyUtils.copy(file.getInputStream(), out);
                fileHandler(Paths.get(filePath + fileSource.getPath()).toFile(), fileTypeEnum);
            } catch (Exception e) {
                throw new RuntimeException("文件保存失败");
            }

        }

        filesService.saveBatch(filesInfo);

        filesInfo.forEach(s -> {
            s.setAccessPath(accessApi + s.getPath());
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


    public void fileHandler(File file, FileTypeEnum fileTypeEnum) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            CsvImportParams csvImportParams = new CsvImportParams();
            if (FileTypeEnum.POSITION.equals(fileTypeEnum)) {
                List<Position> objects = CsvImportUtil.importCsv(fileInputStream, Position.class, csvImportParams);
                positionService.saveOrUpdateBatch(objects);
            }

            if (FileTypeEnum.RUNNING.equals(fileTypeEnum)) {
                List<Running> objects = CsvImportUtil.importCsv(fileInputStream, Running.class, csvImportParams);
                runningService.saveOrUpdateBatch(objects);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // File
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
