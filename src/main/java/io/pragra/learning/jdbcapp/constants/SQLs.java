package io.pragra.learning.jdbcapp.constants;

public class SQLs {
    public static final String USER_SQL="CREATE TABLE USER (USER_ID INT,USER_NAME CHAR(20) , CREATE_DATE DATE ,USER_PASS CHAR(30))";
    //public static final String USER_SQL="CREATE TABLE USERS (\n" +

    public static final String BLOG_SQL="CREATE TABLE BLOG (BLOG_ID INT,USER_ID INT,BLOG_NAME CHAR(235), BLOG_CATAGORY varchar,BLOG_TEXT CHAR(5000))";

}
