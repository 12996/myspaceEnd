package com.example.myspace.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private  Integer loginUser;
    private  Integer followerUser;
    private  Integer isFollower;
}
