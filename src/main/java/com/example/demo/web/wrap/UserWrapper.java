package com.example.demo.web.wrap;

import com.example.demo.data.UserPo;
import com.example.demo.web.data.UserVO;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserWrapper {

    public static UserPo unWrap(UserVO vo) {
        UserPo po = new UserPo();
        BeanUtils.copyProperties(vo, po);
        return po;
    }

    public static UserVO wrap(UserPo po) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public static List<UserVO> wrap(Collection<UserPo> pos) {
        return pos.stream().map(UserWrapper::wrap).collect(Collectors.toList());
    }
}
