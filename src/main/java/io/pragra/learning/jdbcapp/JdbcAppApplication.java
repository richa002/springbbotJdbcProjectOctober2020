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
            User user=new User(1,"john","hjkahsjd", Instant.now());
          //  userDao.createUser(user);
//            User user2=new User(2,"richa","hjkahsjd", Instant.now());
//            userDao.createUser(user2);
//            User user3=new User(3,"SEEMA","hjkahsjd", Instant.now());
//            userDao.createUser(user3);
//            System.out.println("no.of users in user table :"+userDao.countUser());
//          //  userDao.deleteUser(4);

//        userDao.deleteUser(2);
//           System.out.println(userDao.getAllUsers());
//   System.out.println("user with user id :10 ="+userDao.getUser(10));
//     userDao.updateUser(new User(10,"JOHN DOE","hjkahsjd", Instant.now()));
//       Blog blog=new Blog(1,1,"hjkahsjd","hgjfshdj","njs" );
//            Blog blog2=new Blog(2,2,"hjkahsjd","hgjfshdj","njs" );
//
//         blogDao.createBlog(blog);
//            blogDao.createBlog(blog2);
//            System.out.println( blogDao.getAllBlogs());
//            System.out.println(blogDao.getBlog(10));
//            blogDao.deleteBlog(10);
//            blogDao.updateBlog(new Blog(2,2,"Hello","hgjfshdj","njs" ));

//            log.info("blogDaos {}"+blogDao.getAllBlogs());
//
//

        };
    }
}
