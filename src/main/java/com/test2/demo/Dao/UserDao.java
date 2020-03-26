package com.test2.demo.Dao;

import com.test2.demo.Po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{
    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);
}
