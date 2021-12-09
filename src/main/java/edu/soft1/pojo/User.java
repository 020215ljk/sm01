package edu.soft1.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    String username;
    String password;
    String age;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy/MM/dd")
    Date birthday;
    Address address;



}
