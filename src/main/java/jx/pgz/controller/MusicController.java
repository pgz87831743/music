package jx.pgz.controller;


import io.swagger.annotations.Api;
import jx.pgz.dao.sys.entity.Music;
import jx.pgz.dao.sys.service.MusicService;
import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/music")
@RestController
@Api(tags = "音乐")
public class MusicController {

    @Resource
    private MusicService musicService;

    @GetMapping("/getlist")
    public Result<List<Music>> getlist(){
        return Result.ok(musicService.list());
    }


    @PutMapping("/update")
    public Result<String> getlist(@RequestBody List<Music> musics){
        musicService.updateBatchById(musics);
        return Result.ok(null,"操作成功");
    }
}
