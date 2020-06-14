package com.example.demo.storage.impl;

import com.example.demo.configuration.DbH2Configuration;
import com.example.demo.data.UserPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DbH2Configuration.class, UserStorageImpl.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserStorageImplTest {

    @Autowired
    private UserStorageImpl userStorage;

    @Test
    public void create() {
        UserPo po = buildPo();
        int res = userStorage.create(po);
        assertThat(res, greaterThan(2));
    }

    private UserPo buildPo() {
        UserPo po = new UserPo();
        po.setDeleted(false);
        po.setName("xc");
        po.setAge(21);
        po.setGender("male");
        return po;
    }

    @Test
    public void delete() {
        int id1 = 1;
        boolean res = userStorage.delete(id1);
        assertTrue(res);
        Map<Integer, UserPo> pos = userStorage.getByIds(Arrays.asList(id1));
        assertThat(pos.size(), equalTo(0));
    }

    @Test
    public void getByIds() {
        int id1 = 1;
        int id2 = 2;

        Map<Integer, UserPo> pos = userStorage.getByIds(Arrays.asList(id1, id2));
        assertThat(pos.size(), equalTo(2));
        UserPo po1 = pos.get(id1);
        assertThat(po1.getAge(), equalTo(20));
        assertThat(po1.getGender(), equalTo("female"));
        assertThat(po1.getName(), equalTo("叶落知秋"));
        assertThat(po1.isDeleted(), equalTo(false));
    }

    @Test
    public void update() {
        int id1 = 1;
        Map<Integer, UserPo> pos = userStorage.getByIds(Arrays.asList(id1));
        assertThat(pos.size(), equalTo(1));
        UserPo po = pos.get(id1);
        po.setName("test");
        boolean res = userStorage.update(po);
        assertTrue(res);
        pos = userStorage.getByIds(Arrays.asList(id1));
        assertThat(pos.size(), equalTo(1));
        po = pos.get(id1);
        assertThat(po.getName(), equalTo("test"));
    }
}
