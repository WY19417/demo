package com.test2.demo.Service.Impl;

import com.test2.demo.Dao.UserDao;
import com.test2.demo.Dao.UserPoDao;
import com.test2.demo.Po.User;
import com.test2.demo.Po.UserPo;
import com.test2.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPoDao userPoDao;

    @Transactional
    @Override
    public User select(Long id) {
        return userDao.getOne(id);
    }

    @Transactional
    @Override
    public User checkuser(String username) {
        return userDao.findByUsername(username);
    }
    @Transactional
    @Override
    public User save(User user) {
        return  userDao.save(user);
    }
    @Transactional
    @Override
    public User checkuserandpass(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

    @Override
    public Page<UserPo> findAll(Pageable pageable) {
        return userPoDao.findAll(pageable);
    }

    @Override
    public UserPo checkuserpo(String name) {
        return userPoDao.findByName(name);
    }

    @Override
    public UserPo savepo(UserPo userPo1) {
        return userPoDao.save(userPo1);
    }

    @Override
    public UserPo update(UserPo userPo) {
        return userPoDao.saveAndFlush(userPo);
    }

    @Override
    public UserPo search(Long id) {
        return userPoDao.getOne(id);
    }


}
