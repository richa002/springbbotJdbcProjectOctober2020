package io.pragra.learning.jdbcapp.dao;


import io.pragra.learning.jdbcapp.constants.SQLs;
import io.pragra.learning.jdbcapp.domain.Blog;
import io.pragra.learning.jdbcapp.domain.User;
import io.pragra.learning.jdbcapp.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class BlogDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
   private UserDao userDao;

    public BlogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTable();
    }


    public void createTable(){
        log.info("creating table Blog");
        jdbcTemplate.execute(SQLs.BLOG_SQL);
    }


    public int  createBlog(Blog blog){

       User u= userDao.getUser(blog.getUserId());
       if(u==null){
           try{
           throw new UserNotFoundException("User with user ID "+blog.getUserId()+" is not a registered user..cant create a blog");}catch(UserNotFoundException e){
               log.error("{}",e.getMessage());
               return 0;
           }
       }
       else{
        String sql="INSERT INTO BLOG VALUES(?,?,?,?,?)";
        int n=jdbcTemplate.update(sql,new Object[]{blog.getBlogId(),blog.getUserId(),blog.getBlogName(),blog.getBlogCatagory(),blog.getBlogText()});
        log.info("successfully inserted a blog with blog id "+blog.getBlogId());
        return n;
       }
    }


    public int updateBlog(Blog blog){
        String sql="UPDATE BLOG SET BLOG_NAME=? WHERE BLOG_ID=?";
        int n=jdbcTemplate.update(sql,blog.getBlogName(),blog.getBlogId());
        if(n==0) {
            try {
                throw new UserNotFoundException("BlogId "+blog.getBlogId()+" not found..cant be updated");
            }catch(UserNotFoundException e){
                log.error("{}",e.getMessage());
            }
        }
        else log.info("blog with blog id "+blog.getBlogId()+" updated successfully");
        return n;
    }


    public Blog getBlog(int blogId){
        Blog blog=null;
        String sql="SELECT * FROM  BLOG WHERE BLOG_ID=?";
        try {
            blog= jdbcTemplate.queryForObject(sql, new Object[]{blogId}, new BeanPropertyRowMapper<Blog>(Blog.class));

        }
    catch(Exception e){
           log.error("Blog with blogId "+ blogId+" doesnot exists");
    }
        return blog;
    }

    public List<Blog> getAllBlogs(){
        String sql="SELECT * FROM BLOG ";
        log.info("returning list of blogs");
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Blog>(Blog.class));

    }
    public void deleteBlog(int blogId){
        String sql="DELETE FROM BLOG where BLOG_ID=?";

       int n= jdbcTemplate.update(sql,blogId);
       if(n==0){
           try{
           throw  new UserNotFoundException("Blog does not exists and cant be deleted");
       }catch(UserNotFoundException e){
               log.error("{}",e.getMessage());
           }
       }
else log.info("blog  with blogid"+ blogId+" deleted successfully");
    }
}
