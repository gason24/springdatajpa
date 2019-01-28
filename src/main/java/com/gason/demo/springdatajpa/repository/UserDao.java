package com.gason.demo.springdatajpa.repository;

import com.gason.demo.springdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor {

    @Modifying
    @Transactional
    @Query(value = "update jx_user user set user.is_delete = 1 where user.id in (:ids)",nativeQuery = true)
    int deleteByIds(@Param("ids")List<Long> ids);
}
