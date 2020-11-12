package io.pragra.learning.jdbcapp.domain;

import lombok.*;

@ToString
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private int blogId;
    private int userId;
    private String blogName;
    private String blogCatagory;
    private String blogText;


}
