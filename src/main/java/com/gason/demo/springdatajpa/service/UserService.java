package com.gason.demo.springdatajpa.service;

import com.gason.demo.springdatajpa.entity.User;
import com.gason.demo.springdatajpa.pojo.UserSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void insert(User user);
    void delete(List<Long> ids);
    void update(User user);
    User get(Long id);
    List<User> getList(List<Long> id);
    Page<User> search(UserSearch search, Integer pageNum, Integer pageSize);
}
