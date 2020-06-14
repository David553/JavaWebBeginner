package com.example.demo.storage.impl;

import com.example.demo.data.GenderEnum;
import com.example.demo.data.UserPo;
import com.example.demo.storage.BaseStorage;
import com.example.demo.storage.UserStorage;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class UserStorageImpl extends BaseStorage implements UserStorage {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private static final String TABLE_NAME = "user";

    private static final String COLUMNS_WITHOUT_ID = "`age`, `gender`, `name`, `isDeleted`, `createdTime`, `updatedTime`";

    private static final String ALL_COLUMNS = "`id`, " + COLUMNS_WITHOUT_ID;

    private static RowMapper<UserPo> ROW_MAPPER = (rs, rowNum) -> {
        UserPo po = new UserPo();
        po.setId(rs.getInt("id"));
        po.setAge(rs.getInt("age"));
        po.setGender(GenderEnum.findByInt(rs.getInt("gender")).toString());
        po.setDeleted(rs.getInt("isDeleted") > 0);
        po.setName(rs.getString("name"));
        po.setCreatedTime(rs.getLong("createdTime"));
        po.setUpdatedTime(rs.getLong("updatedTime"));
        return po;
    };

    @Override
    public int create(UserPo po) {
        long time = System.currentTimeMillis();
        String sql = " INSERT INTO " + TABLE_NAME + " (" + COLUMNS_WITHOUT_ID + ") VALUES (:age, :gender, :name, :isDeleted, :createdTime, :updatedTime)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("age", po.getAge());
        source.addValue("gender", GenderEnum.findByString(po.getGender()).toInt());
        source.addValue("name", po.getName());
        source.addValue("isDeleted", po.isDeleted());
        source.addValue("createdTime", time);
        source.addValue("updatedTime", time);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(sql, source, keyHolder);
        if (keyHolder.getKey() == null) {
            return 0;
        }
        return keyHolder.getKey().intValue();
    }

    @Override
    public boolean delete(int id) {
        long time = System.currentTimeMillis();
        String sql = "UPDATE " + TABLE_NAME + " SET isDeleted = 1, updatedTime = :updatedTime WHERE id = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        source.addValue("updatedTime", time);
        return jdbcOperations.update(sql, source) > 0;
    }

    @Override
    public Map<Integer, UserPo> getByIds(Collection<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Maps.newHashMap();
        }
        String sql = "SELECT " + ALL_COLUMNS + " FROM " + TABLE_NAME + " WHERE id IN (:ids) and isDeleted = 0";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("ids", ids);
        List<UserPo> userPoList = jdbcOperations.query(sql, source, ROW_MAPPER);
        if (CollectionUtils.isEmpty(userPoList)) {
            return Maps.newHashMap();
        }
        return userPoList.stream().collect(Collectors.toMap(UserPo::getId, Function.identity()));
    }

    @Override
    public boolean update(UserPo po) {
        long time = System.currentTimeMillis();
        String sql = "UPDATE " + TABLE_NAME + " SET age = :age, name = :name, gender = :gender, updatedTime = :updatedTime WHERE id = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", po.getId());
        source.addValue("age", po.getAge());
        source.addValue("gender", GenderEnum.findByString(po.getGender()).toInt());
        source.addValue("name", po.getName());
        source.addValue("updatedTime", time);
        return jdbcOperations.update(sql, source) > 0;
    }
}
