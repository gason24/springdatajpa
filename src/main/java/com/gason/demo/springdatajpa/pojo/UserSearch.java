package com.gason.demo.springdatajpa.pojo;

import com.gason.demo.springdatajpa.entity.User;

import java.util.List;

/**
 * @program: springdatajpa
 * @description: user查询对象
 * @author: Gason Lee
 * @create: 2019-01-21 11:24
 **/
public class UserSearch extends User {

    private String keywords;

    private List<Long> idLists;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Long> getIdLists() {
        return idLists;
    }

    public void setIdLists(List<Long> idLists) {
        this.idLists = idLists;
    }
}
