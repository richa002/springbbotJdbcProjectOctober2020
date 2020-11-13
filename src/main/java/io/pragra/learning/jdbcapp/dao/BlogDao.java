package io.pragra.learning.jdbcapp.dao;


import io.pragra.learning.jdbcapp.constants.SQLs;
import io.pragra.learning.jdbcapp.domain.Blog;
import io.pragra.learning.jdbcapp.domain.User;
import io.pragra.learning.jdbcapp.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@Data


public class BlogDao {

    private NamedParameterJdbcTemplate template;

    public BlogDao(NamedParameterJdbcTemplate template) {
        /*
        USER_ID INT,
BLOG_NAME CHAR(235),
BLOG_CATAGORY INT,
BLOG_TEXT CHAR(5000)
         */
        this.template = template;
        String sql = "CREATE TABLE BLOG (BLOG_ID INT,USER_ID INT,BLOG_NAME CHAR(235), BLOG_CATAGORY varchar(50),BLOG_TEXT text(5000))";
        //  template.getJdbcTemplate().execute(sql);
    }

    public int createBlog(Blog blog) {
        /**
         *  private int blogId;
         *     private int userId;
         *     private String blogName;
         *     private String blogCatagory;
         *     private String blogCatagory;
         */
//    String sql1="SELECT * FROM USER WHERE USER_ID=:userId";
//    User u;
//    SqlParameterSource source1=new MapSqlParameterSource("userId",blog.getUserId());
//    try {
//         u = template.queryForObject(sql1, source1, new BeanPropertyRowMapper<User>(User.class));
//        if(u!=null){
        int n = 0;
        boolean flag = checkIfUserExists(blog.getUserId());
        if (flag == true) {
            String sql = "INSERT INTO BLOG VALUES(:blogId,:userId,:blogName,:blogCatagory,:blogCatagory)";
            SqlParameterSource source = new BeanPropertySqlParameterSource(blog);
            log.info("{}", "Blog created successfully with blog Id :" + blog.getBlogId());


            n = template.update(sql, source);
        } else{
            log.error("{}", "Blog  cant be created with blog Id :" + blog.getBlogId());

        }
            return n;
    }

    public boolean checkIfUserExists(int userId) {
        String sql1 = "SELECT * FROM USER WHERE USER_ID=:userId";
        User u = null;
        SqlParameterSource source1 = new MapSqlParameterSource("userId", userId);
        try {
            u = template.queryForObject(sql1, source1, new BeanPropertyRowMapper<User>(User.class));
        } catch (Exception e) {
            log.error("{}", "user doesnot exixts..cant perform the operation");
        }
        if (u == null) return false;
        else return true;
    }

    public int updateBlog(Blog blog) {
        int n = 0;
        boolean flag = checkIfUserExists(blog.getUserId());
        if (flag == true) {
            String sql = "update BLOG set USER_ID=:userId,BLOG_NAME=:blogName,BLOG_CATAGORY=:blogCatagory where BLOG_ID=:blogId";
            SqlParameterSource source = new BeanPropertySqlParameterSource(blog);
            n = template.update(sql, source);
            return n;
        } else return n;
    }

    public int deleteBlog(int blogId) {
        /**
         *  private int blogId;
         *     private int userId;
         *     private String blogName;
         *     private String blogCatagory;
         *     private String blogCatagory;
         */

        String sql = "delete from BLOG where BLOG_ID=:blogId";
        SqlParameterSource source = new MapSqlParameterSource("blogId", blogId);
        return template.update(sql, source);
    }

    public Blog getBlog(int blogId) {
        /**
         *  private int blogId;
         *     private int userId;
         *     private String blogName;
         *     private String blogCatagory;
         *     private String blogCatagory;
         */

        String sql = "select * from BLOG where  BLOG_ID =:blogId";
        SqlParameterSource source = new MapSqlParameterSource("blogId", blogId);

        return template.queryForObject(sql, source, new BeanPropertyRowMapper<Blog>(Blog.class));
    }

    public List<Blog> getAllBlogs() {
        /**
         *  private int blogId;
         *     private int userId;
         *     private String blogName;
         *     private String blogCatagory;
         *     private String blogCatagory;
         */

        String sql = "select * from BLOG ";
        ;

        return template.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class));
    }


}
