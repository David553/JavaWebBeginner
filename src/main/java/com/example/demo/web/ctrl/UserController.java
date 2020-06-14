package com.example.demo.web.ctrl;

import com.example.demo.web.data.UserVO;
import com.example.demo.web.logic.UserLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户表")
public class UserController extends BaseController {

    @Autowired
    private UserLogic userLogic;

    @ApiOperation("创建用户")
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public int create(@RequestBody UserVO userVO) {
        return userLogic.create(userVO);
    }

    @ApiOperation("查找用户")
    @GetMapping("/ids")
    public Map<Integer, UserVO> getByIds(@RequestParam List<Integer> ids) {
        return userLogic.getByIds(ids);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id:\\d+}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        return userLogic.delete(id);
    }

    @ApiOperation("更新用户")
    @PutMapping("/update/{id:\\d+}")
    public boolean update(@PathVariable int id, @RequestBody UserVO userVO) {
        return userLogic.update(id, userVO);
    }
}
