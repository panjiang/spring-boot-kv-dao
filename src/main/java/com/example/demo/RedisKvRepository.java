package com.example.demo;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class RedisKvRepository<T extends KvEntity<ID>, ID> implements KvRepository<T, ID> {
    Class<T> clazz;
    String table;
    ValueOperations redisOps;

    @Autowired
    RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public void init(Class<T> clazz) {
        this.clazz = clazz;
        this.table = this.clazz.newInstance().table();
        this.redisOps = redisTemplate.opsForValue();
    }

    private String createKey(ID id) {
        Objects.requireNonNull(id);
        return String.format("%s:{%s}", table, id);
    }

    @Override
    public <S extends T> void save(S entity) {
        String value = JSON.toJSONString(entity);
        redisOps.set(createKey(entity.partitionKey()), value);
    }

    @Override
    public T getById(ID id) {
        String value = (String) redisOps.get(createKey(id));
        return JSON.parseObject(value, clazz);
    }
}
