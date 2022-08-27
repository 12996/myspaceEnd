package com.example.myspace.service.chat;

import com.example.myspace.pojo.Chat;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ChatService {
    public List<Chat> getChatByPage(Integer index);
}
