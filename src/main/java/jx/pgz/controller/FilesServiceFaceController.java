package jx.pgz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jx.pgz.dao.sys.entity.SysFile;
import jx.pgz.enums.FileTypeEnum;
import jx.pgz.server.FilesServiceFace;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

@RequestMapping("/file")
@RestController
@Api(tags = "文件管理")
public class FilesServiceFaceController {

    @Resource
    FilesServiceFace filesServiceFace;

    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public List<SysFile> list(@ApiParam("文件") MultipartFile[] files, @ApiParam("文件类型") FileTypeEnum fileTypeEnum) {
        return filesServiceFace.upload(files, fileTypeEnum);
    }

    @GetMapping("download/{fileId}")
    @ApiOperation("下载")
    public void download(@ApiParam("附件Id") @PathVariable("fileId") String fileId) {
        filesServiceFace.download(fileId);
    }

    @DeleteMapping("delete/{fileId}")
    @ApiOperation("删除")
    public boolean delete(@ApiParam("附件Id") @PathVariable("fileId") String fileId) {
        return filesServiceFace.delete(fileId);
    }

}