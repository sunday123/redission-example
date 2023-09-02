package com.ij34.controller;


import com.ij34.model.HelloDto;
import com.ij34.redis.queue.DelayedMessageSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录测试 
 */
@Api(tags="acc")
@RestController
@RequestMapping("/user/")
public class LoginController {
    @Autowired
    private DelayedMessageSender messageSender;


    @ApiOperation("set")
    @PostMapping("set")
    public void set(@RequestBody HelloDto record) {
        messageSender.sendDelayedMessage(record, 60);
    }

    @ApiOperation("get")
    @GetMapping("get")
    public List<HelloDto> get() {
        return messageSender.readAll();
    }



    // 测试注销  ---- http://localhost:8081/acc/logout
    @ApiOperation("remove")
    @PostMapping("remove")
    public Boolean remove(@RequestBody HelloDto record) {
        return messageSender.remove(record);
    }

}
