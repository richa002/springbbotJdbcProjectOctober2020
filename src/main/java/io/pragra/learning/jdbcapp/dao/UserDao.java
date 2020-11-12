package io.pragra.learning.jdbcapp.dao;


import io.pragra.learning.jdbcapp.constants.SQLs;
import io.pragra.learning.jdbcapp.domain.User;
import io.pragra.learning.jdbcapp.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTable();
    }


    public void createTable(){
        log.info("creating table user");
        jdbcTemplate.execute(SQLs.USER_SQL);
    }


    public int  createUser(User user){

        String sql="INSERT INTO USER VALUES(?,?,?,?)";
        int n=jdbcTemplate.update(sql,new Object[]{user.getUserId(),user.getUserName(),user.getCreateDate(),user.getUserPass()});
       log.info("Successfully created user with user id :"+user.getUserId());
        return n;
    }


    public void updateUser(User user){
        String sql="UPDATE USER SET USER_NAME=?,CREATE_DATE=?, USER_PASS=? WHERE USER_ID=?";
         int n= jdbcTemplate.update(sql,user.getUserName(),user.getCreateDate(),user.getUserPass(),user.getUserId());
         if(n==0){

             try {
                 throw new UserNotFoundException("user with user id "+user.getUserId()+" not found and cant be updated");
             }catch(UserNotFoundException e){
                 log.error("{}",e.getMessage());
             }
             }
         else log.info("user with userid "+user.getUserId()+" updated successfully");
    }


    public int countUser(){
      return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER",Integer.class);
    }
    public User getUser(int userId){
        User u=null;
        try {
        String sql = "SELECT * FROM USER WHERE USER_ID=?";
         u= jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<User>(User.class));
        }catch(Exception e){
           // log.error("{}",e.getMessage());
            log.error("user  with user id "+userId+" not found");

          //  throw new UserNotFoundException("user not found");
             }
     //   log.info("returning user with user id: "+userId);
    return u;
    }
    public List<User> getAllUsers(){
        String sql="SELECT * FROM USER ";
        log.info("returning list ");
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));

    }
    public void deleteUser(int userId){
        String sql="DELETE FROM USER where USER_ID=?";
         int n=jdbcTemplate.update(sql,userId);
if (n==0) {
    //log.error("user does not exists");
    try{
    throw new UserNotFoundException("user  with userid :"+userId+" not found and cant be deleted");}
    catch(UserNotFoundException e){
      //  System.out.println(e);
        log.error("{}",e.getMessage());
    }
}
else log.info("successfully deleted user with user id :"+userId);
    }
}
