package com.example.demo.web.logic.impl;

import com.example.demo.web.data.UserVO;
import com.example.demo.web.logic.UserLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserLogicImpl implements UserLogic {

    @Override
    public int create(UserVO userVO) {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Map<Integer, UserVO> getByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public boolean update(int id, UserVO userVO) {
        return false;
    }
}
