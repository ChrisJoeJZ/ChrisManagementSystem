package com.chris.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @TableId
    private  Integer userId;
    private String  userName;
    private String  userAvatarurl;
    private String  userPhone;
    private String  userEmail;
    private String  userPassword;
    private String  userSalt;
    private Integer userSecuritylevel;
    private Integer userGender;
    private LocalDateTime userBirthday;
    private String userRegion;
    private String userMotto;
    private Byte userStatus;
    private String userToken;
    private LocalDateTime userExpiretime;
    private LocalDateTime userLoggedtime;
    private String userLoggedip;
    private Integer userCreatefrom;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
}
