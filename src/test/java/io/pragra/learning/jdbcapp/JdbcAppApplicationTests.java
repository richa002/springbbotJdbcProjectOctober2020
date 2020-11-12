package io.pragra.learning.jdbcapp;

import io.pragra.learning.jdbcapp.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;


import io.pragra.learning.jdbcapp.dao.UserDao;
import io.pragra.learning.jdbcapp.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JdbcAppApplicationTests {
    @Autowired
    UserDao userDao;

   @Test
    void contextLoads() {
    }
    @Test
    public void testCreateUser(){
        User testUser=getUserForTesting();
        System.out.println(testUser);
        userDao.createUser(testUser);
      User savedUser=  userDao.getUser(testUser.getUserId());
        System.out.println(savedUser);
        Assert.assertEquals(testUser,savedUser);
        System.out.println("no.of users :"+userDao.countUser());


    }
   @Test
    public void testUpdateUser(){
      System.out.println("in update test meythod"+userDao.countUser());
      System.out.println(userDao.getAllUsers());
    User testUser=getUserForUpdateTesting();
      System.out.println(testUser);
      userDao.createUser(new User(11,"richa luthra","pass",Instant.now()));
       System.out.println(userDao.getAllUsers());

       userDao.updateUser(testUser);
      User updatedUser=  userDao.getUser(testUser.getUserId());
      System.out.println(updatedUser);
       Assert.assertEquals(testUser,updatedUser);


    }

    @Test
    public void testDeleteUser(){
        User testUser=getUserForTesting();
        System.out.println(testUser);
        userDao.deleteUser(10);
        User u=userDao.getUser(10);
        Assert.assertEquals(null,u);


    }
    @Test
    public void testGetUser(){
       User u= userDao.getUser(11);

        Assert.assertEquals(11,u.getUserId());


    }
    @Test
    public void testGetUser2(){
        User u= userDao.getUser(12);

        Assert.assertNull(u);


    }
   // @Test
    public void testGetAllUser(){
        List<User> list=userDao.getAllUsers();




    }






    public  User getUserForTesting(){
     //   Instant date =Instant.now();
        User user =new User(10,"savita kumari","10101", Instant.now());
        return user;
    }
    public User getUserForUpdateTesting(){
        //   Instant date =Instant.now();
        User user =new User(11,"richa","10101", Instant.now());
        return user;
    }
}