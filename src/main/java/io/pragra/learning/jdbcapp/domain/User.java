package io.pragra.learning.jdbcapp.domain;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@ToString
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userPass;
    private Date createDate;
}
