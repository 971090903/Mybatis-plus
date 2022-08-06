package com.gdupt.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
//MybatisPlus由实体对象决定对应哪个数据表
@Data
@TableName("user")//用于设置实体类对应的数据库表名，将表和实体类进行映射
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version
    private Integer version;
}
