package com.example.demo;

import lombok.Data;

@Data
public class UserInfo implements KvEntity<Long> {
    private Long uid;

    private String nickname;

    @Override
    public String table() {
        return "user_info";
    }

    @Override
    public Long partitionKey() {
        return uid;
    }
}
