package io.pragra.learning.jdbcapp;

import io.pragra.learning.jdbcapp.dao.BlogDao;
import io.pragra.learning.jdbcapp.dao.UserDao;
import io.pragra.learning.jdbcapp.domain.Blog;
import io.pragra.learning.jdbcapp.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication(scanBasePackages = "io.pragra.learning.jdbcapp")
@Slf4j

public class JdbcAppApplication {
    private UserDao userDao;
    private BlogDao blogDao;

    public JdbcAppApplication(UserDao userDao, BlogDao blogDao) {
        this.userDao = userDao;
        this.blogDao = blogDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args->{
           User user=new User(4,"richa","hjkahsjd", new Date());
            userDao.createUser(user);
//            User user2=new User(2,"richa","hjkahsjd", new Date());
//            userDao.createUser(user2);
//            User user3=new User(3,"SEEMA","hjkahsjd", new Date());
//            userDao.createUser(user3);
//            System.out.println("no.of users in user table :"+userDao.countUser());
//          //  userDao.deleteUser(4);

//        userDao.deleteUser(2);
//           System.out.println(userDao.getAllUsers());
  System.out.println("user with user id :4 ="+userDao.getUser(4));
//     userDao.updateUser(new User(10,"JOHN DOE","hjkahsjd", Instant.now()));
            Blog blog2=new Blog(2,5,"blog50","catagory20","text20" );

        Blog blog3=new Blog(3,5,"blog50","catagory20","text20" );
//            blogDao.createBlog(blog);
//            blogDao.createBlog(blog2);

//            blogDao.createBlog(blog3);

//            System.out.println("BLog with blog Id 2 is: "+blogDao.getBlog(2));
           System.out.println("fetching all blogs: "+ blogDao.getAllBlogs());

////            System.out.println(blogDao.getBlog(10));
            int a=blogDao.deleteBlog(7);
           if(a>0) System.out.println("blog deleted successfully");
           else System.out.println("Blog cant be deleted");
//          int n= blogDao.updateBlog(blog2);
//       if(n==1) System.out.println("blog updated successfully");
//       else System.out.println("blog cant be updated");
//////            log.info("blogDaos {}"+blogDao.getAllBlogs());
//////
//////

        };
    }
}
