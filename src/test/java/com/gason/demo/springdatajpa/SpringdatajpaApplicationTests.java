package com.gason.demo.springdatajpa;

import com.gason.demo.springdatajpa.entity.RandomValue;
import com.gason.demo.springdatajpa.entity.User;
import com.gason.demo.springdatajpa.entity.cons.DataStatusConstant;
import com.gason.demo.springdatajpa.pojo.UserSearch;
import com.gason.demo.springdatajpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdatajpaApplicationTests {
    @Resource
    private UserService userService;

    @Test
    public void contextLoads() {}

    @Test
    public void testInsert(){
        for (int i = 0; i < 10 ; i++) {
            User user = new User();
            user.setId(RandomValue.getLong(1,200));
            user.setName(RandomValue.getChineseName());
            user.setPwd(RandomValue.getTel());
            user.setAccount(RandomValue.getTel());
            user.setIsDelete(DataStatusConstant.dataStatus_normal);
            userService.insert(user);
        }
    }

    @Test
    @Transactional
    public void testGet(){
        User user = userService.get(117L);
        System.out.println(user);
    }

    @Test
    public void testGetList(){
        List<Long> idLists = new ArrayList<>();
        idLists.add(1L);
        idLists.add(19L);
        idLists.add(29L);
        idLists.add(49L);
        idLists.add(50L);
        List<User> list = userService.getList(idLists);
        for (User user : list){
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    public void testUpdate(){
        // save()方法更新字段一定要通过Repository获取实体对象，在此对象上进行更新操作。
        User user = userService.get(51L);
        System.err.println(user);
        user.setPwd(RandomValue.getTel());
        user.setName(RandomValue.getChineseName());
        System.err.println(user);
        userService.update(user);
    }

    @Test
    public void testDelete(){
        List<Long> idLists = new ArrayList<>();
        idLists.add(51L);
        idLists.add(65L);
        idLists.add(81L);
        idLists.add(93L);
        idLists.add(97L);
        userService.delete(idLists);
    }

    @Test
    public void testSearch(){
        UserSearch search = new UserSearch();
        search.setKeywords("13");
        // pageable 的起始值为page-1
        int pageNum = 1;
        int pageSize = 3;
        Page<User> userPage = userService.search(search, pageNum, pageSize);
        List<User> content = userPage.getContent();
        for (User user : content){
            System.out.println(user);
        }

    }
}

