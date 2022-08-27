package com.example.myspace.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer sendId;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date sendtime;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String photo;

    public Chat(Integer id, Integer userId, Integer sendId, String message, Date sendtime) {
        this.id = id;
        this.userId = userId;
        this.sendId = sendId;
        this.message = message;
        this.sendtime = sendtime;
    }
}
