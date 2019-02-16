package com.qijianguo.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Angus
 * @version 1.0
 * @Title: JpaUser
 * @Description: TODO 持久化实体类设计参考：https://blog.csdn.net/swordcenter/article/details/84759819
 * @date 2019/2/13 14:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_user")
public class JpaUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄必须小于150")
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Past
    private LocalDateTime birthday;

    @Email
    private String emil;

    @Size(min = 0, max = 30, message = "座右铭必须大于0，最大字数为30")
    private String motto;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonProperty("name") //  当前端属性为nick后台接收对象的属性为nickName时可以用@JsonProperty来保持一致
    private String nickname;

}
