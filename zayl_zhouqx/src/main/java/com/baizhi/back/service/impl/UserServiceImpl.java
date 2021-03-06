package com.baizhi.back.service.impl;

import com.baizhi.back.service.UserService;
import com.baizhi.common.dao.UserDao;
import com.baizhi.common.entity.User;
import com.baizhi.common.util.SaltUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/28.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectAll(){
        return userDao.selectAll();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryAll() {
        List<User> users = userDao.selectAll();
        return users;
    }

    public void add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setStatus("冻结");
        String salt = SaltUtils.getSalt(4);
        user.setSalt(salt);
        String str = salt+user.getPassword();
        user.setPath("E:\\gitprojects\\zayl_zhouqx\\src\\main\\webapp\\upload\\06804dd097b24ba1badb3d98fdbf0ce1.jpg");
        user.setPassword(DigestUtils.md5DigestAsHex(str.getBytes()));
        userDao.insert(user);
    }

    public void remove(String id) {
        userDao.delete(id);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryOne(String id) {
        return userDao.selectOne(id);
    }

    public void edit(User user) {

        userDao.update(user);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryAll(Integer page, Integer rows) {
        int a = (page-1)*rows;
        System.out.println(a);
        return userDao.queryAll(a,rows);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer queryCount() {
        return userDao.queryCount();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryBySerch(String name,String value,Integer page, Integer rows) {
        int a=(page-1)*rows;

        return userDao.selectBySearch(name,value,a,rows);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer queryCountBySearch(String name,String value) {
        return userDao.queryCountBySearch(name,value);
    }
}
