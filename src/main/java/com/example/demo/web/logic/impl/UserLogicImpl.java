package com.example.demo.web.logic.impl;

import com.example.demo.data.UserPo;
import com.example.demo.service.UserService;
import com.example.demo.web.data.UserVO;
import com.example.demo.web.logic.UserLogic;
import com.example.demo.web.wrap.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserLogicImpl implements UserLogic {

    @Autowired
    private UserService userService;

    @Override
    public int create(UserVO userVO) {
        return userService.create(UserWrapper.unWrap(userVO));
    }

    @Override
    public boolean delete(int id) {
        return userService.delete(id);
    }

    @Override
    public Map<Integer, UserVO> getByIds(List<Integer> ids) {
        Map<Integer, UserPo> userPoMap = userService.getByIds(ids);
        List<UserVO> userVOS = UserWrapper.wrap(userPoMap.values());
        return userVOS.stream().collect(Collectors.toMap(UserVO::getId, Function.identity()));
    }

    @Override
    public boolean update(int id, UserVO userVO) {
        Map<Integer, UserPo> userPoMap = userService.getByIds(Collections.singletonList(id));
        if (!userPoMap.containsKey(id)) {
//            throw new NotFoundException("user不存在", null, null, Charset.forName("UTF-8"));
            return false;
        }

        return userService.update(UserWrapper.unWrap(userVO));
    }
}
