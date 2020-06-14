package com.example.demo.service;

import com.example.demo.data.UserPo;

import java.util.Collection;
import java.util.Map;

public interface UserService {

    int create(UserPo po);

    boolean delete(int id);

    Map<Integer, UserPo> getByIds(Collection<Integer> ids);

    boolean update(UserPo po);
}
