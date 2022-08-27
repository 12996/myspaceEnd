package com.example.myspace;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myspace.mapper.ChatMapper;
import com.example.myspace.mapper.PostMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Chat;
import com.example.myspace.pojo.Post;
import com.example.myspace.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MyspaceApplicationTests {

    @Autowired
    PostMapper postMapper;
    @Autowired
    ChatMapper chatMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    public void text1(){

    }

}
