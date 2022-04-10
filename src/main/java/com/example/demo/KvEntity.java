package com.example.demo;

public interface KvEntity<ID> {
    String table();
    ID partitionKey();
}
