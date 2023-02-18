package jx.pgz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jx.pgz.dao.sys.entity.SysFile;
import jx.pgz.enums.FileTypeEnum;

import jx.pgz.security.IgnoreAuth;
import jx.pgz.server.FilesServiceFace;
import jx.pgz.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/file")
@RestController
@Api(tags = "文件管理")
public class FilesServiceFaceController {

    @Resource
    FilesServiceFace filesServiceFace;

    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result<List<SysFile>> list(@ApiParam("文件") MultipartFile[] files, @ApiParam("文件类型") FileTypeEnum fileTypeEnum) {
        return Result.ok(filesServiceFace.upload(files, fileTypeEnum));
    }

    @GetMapping("download/{fileId}")
    @ApiOperation("下载")
    @IgnoreAuth
    public void download(@ApiParam("附件Id") @PathVariable("fileId") String fileId) {
        filesServiceFace.download(fileId);
    }

    @DeleteMapping("delete/{fileId}")
    @ApiOperation("删除")
    public Result<String> delete(@ApiParam("附件Id") @PathVariable("fileId") String fileId) {
        boolean delete = filesServiceFace.delete(fileId);
        return Result.ok(delete?"删除成功":"删除失败");
    }

}
