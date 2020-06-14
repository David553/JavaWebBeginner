package com.example.demo.service.impl;

import com.example.demo.data.UserPo;
import com.example.demo.service.UserService;
import com.example.demo.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserStorage userStorage;

    @Override
    public int create(UserPo po) {
        return userStorage.create(po);
    }

    @Override
    public boolean delete(int id) {
        return userStorage.delete(id);
    }

    @Override
    public Map<Integer, UserPo> getByIds(Collection<Integer> ids) {
        return userStorage.getByIds(ids);
    }

    @Override
    public boolean update(UserPo po) {
        return userStorage.update(po);
    }
}
