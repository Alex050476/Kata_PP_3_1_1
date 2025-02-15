package com.agor.Kata_3_1_1_S_Boot.dao;

import com.agor.Kata_3_1_1_S_Boot.models.User;
import java.util.List;

public interface UserDao {
    List<User> usersList();
    void addUser(User user);
    void refactorUser(int id, User userUpdate);
    void deleteUser(int id);
    User show(int id);
}
