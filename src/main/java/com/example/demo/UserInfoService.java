package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class UserInfoService {
    @Autowired
    private RedisKvRepository<UserInfo, Long> repo;

    JpaRepository<UserInfo, Long> a;
    CrudRepository<UserInfo, Long> b;

    @PostConstruct
    public void init() {
        repo.init(UserInfo.class);
    }

    public void get(long uid) {
        UserInfo userInfo = repo.getById(uid);
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUid(1L);
            userInfo.setNickname("cat");
            repo.save(userInfo);
            log.info("create userInfo={}", userInfo);
            return;
        }

        log.info("found uid={}", userInfo.getUid());

        userInfo.setNickname("dog");
        repo.save(userInfo);
        log.info("userInfo={}", userInfo);
    }
}
