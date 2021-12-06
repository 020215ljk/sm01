package edu.soft1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String username;
    String password;
    String age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;
    Address address;
}
