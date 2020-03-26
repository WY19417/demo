package com.test2.demo.Dao;

import com.test2.demo.Po.User;
import com.test2.demo.Po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;

public interface UserPoDao extends JpaRepository<UserPo,Long> {
        UserPo findByName(String name);
}
