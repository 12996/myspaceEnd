package com.example.myspace.service.Impl.chat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myspace.mapper.ChatMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Chat;
import com.example.myspace.pojo.User;
import com.example.myspace.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ChatMapper chatMapper;
    @Override
    public List<Chat> getChatByPage(Integer index) {
        IPage<Chat> chatIPage = new Page<>(index, 100);
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        IPage<Chat> IPages =  chatMapper.selectPage(chatIPage, queryWrapper.orderByDesc("sendtime"));
        List<Chat> chats = IPages.getRecords();
        List<Integer> value = new  ArrayList<>();
        for(Chat chat : chats){
            value.add(chat.getUserId());
        }
        QueryWrapper<User> queryUser = new QueryWrapper<>();
        List<User> users = new ArrayList<>();
        if(value != null) {
          users  = userMapper.selectList(queryUser.in("id", value));
        }
        for(Chat chat : chats){
            for(User user : users){
                if(chat.getUserId().equals(user.getId())){
                    chat.setPhoto(user.getPhoto());
                    chat.setUsername(user.getUsername());
                    break;
                }
            }
        }
        return chats;
    }
}
