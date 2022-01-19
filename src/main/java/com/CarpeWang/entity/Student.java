package com.CarpeWang.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangkaipeng
 * 简单来说定义一个student类，使用Java
 * 将数据存储到redis，进行操作
 * 别忘记sudo redis启动
 */
@Data
public class Student implements Serializable {
    /**
     * 这里注意一定要实现序列化接口
     * 否则无法存储到redis
     */
    private Integer id;
    private String name;
    private Double score;
    private Date birthday;


}
