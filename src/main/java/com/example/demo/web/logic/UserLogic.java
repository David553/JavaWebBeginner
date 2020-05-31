package com.example.demo.web.logic;

import com.example.demo.web.data.UserVO;

import java.util.List;
import java.util.Map;

public interface UserLogic {

    int create(UserVO userVO);

    boolean delete(int id);

    Map<Integer, UserVO> getByIds(List<Integer> ids);

    boolean update(int id, UserVO userVO);
}
