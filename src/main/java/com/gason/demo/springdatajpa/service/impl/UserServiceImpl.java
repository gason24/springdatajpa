package com.gason.demo.springdatajpa.service.impl;

import com.gason.demo.springdatajpa.entity.User;
import com.gason.demo.springdatajpa.entity.cons.DataStatusConstant;
import com.gason.demo.springdatajpa.pojo.UserSearch;
import com.gason.demo.springdatajpa.repository.UserDao;
import com.gason.demo.springdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springdatajpa
 * @description: userService实现类
 * @author: Gason Lee
 * @create: 2019-01-16 17:03
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public void insert(User entity) {
        entity.setIsDelete(DataStatusConstant.dataStatus_normal);
        userDao.save(entity);
    }

    @Override
    public void delete(List<Long> ids) {
        userDao.deleteByIds(ids);
    }

    @Override
    public void update(User entity) {
        userDao.saveAndFlush(entity);
    }

    @Override
    public User get(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public List<User> getList(List<Long> ids) {
        return userDao.findAllById(ids);
    }

    @Override
    public Page<User> search(UserSearch search, Integer pageNum, Integer pageSize) {
        // 按照指定字段排序 其中第一个参数表示是降序还是升序,第二个是实体类中需要排序的字段
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        /*
         *   List<Sort.Order> orders = new ArrayList<>();
         *   orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
         *   orders.add(new Sort.Order(Sort.Direction.ASC,"firstName"));
         *   Sort orders = new Sort(orders);
         */

        Pageable pageable = new PageRequest(pageNum, pageSize,sort);
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(search.getKeywords())){
                    Predicate p1 = cb.like(root.get("name").as(String.class), "%" + search.getKeywords() + "%");
                    Predicate p2 = cb.like(root.get("pwd").as(String.class), "%" + search.getKeywords() + "%");
                    predicates.add(cb.or(p1,p2));
                }
                // 逻辑删除
                predicates.add(cb.equal(root.get("isDelete").as(Byte.class),DataStatusConstant.dataStatus_normal));
                // 其他...
                Predicate[] p = new Predicate[predicates.size()];
                return cb.and(predicates.toArray(p));
            }
        };
        return userDao.findAll(specification, pageable);
    }
}
