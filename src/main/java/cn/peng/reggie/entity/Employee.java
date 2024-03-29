package cn.peng.reggie.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;
  private String name;
  private String username;
  private String password;
  private String phone;
  private String sex;
  private String idNumber;
  private Integer status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  @TableField(fill = FieldFill.INSERT)
  private long createUser;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private long updateUser;
}
