package com.example.demo.data;

import lombok.Data;

@Data
public class UserPo {

    int id;
    String gender;
    int age;
    String name;
    boolean isDeleted;
    long createdTime;
    long updatedTime;
}
