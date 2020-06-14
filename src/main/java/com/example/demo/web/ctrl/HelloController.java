package com.example.demo.web.ctrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "测试swagger")
public class HelloController extends BaseController {

    @ApiOperation(value = "hello")
    @GetMapping
    public String hello() {
        return "hello";
    }
}
