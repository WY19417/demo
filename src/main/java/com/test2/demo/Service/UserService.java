package com.test2.demo.Service;

import com.test2.demo.Po.User;
import com.test2.demo.Po.UserPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User select(Long id);

    User checkuser(String username);

    User save(User user);

    User checkuserandpass(String username, String password);

    Page<UserPo> findAll(Pageable pageable);
    UserPo checkuserpo(String name);

    UserPo savepo(UserPo userPo1);

    UserPo update(UserPo userPo);

    UserPo search(Long id);
}
