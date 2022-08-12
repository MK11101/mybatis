package com.MK.Dao;

import com.MK.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
    public User findByid(int id);
    public List<User> findByids(List list);
    public User findByCondition(User user);

    public void savebirthday(User user);
    public User selectbirthday(int id);
    public List<User> findorder();
}
