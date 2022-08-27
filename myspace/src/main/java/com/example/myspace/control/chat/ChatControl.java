package com.example.myspace.control.chat;

import com.example.myspace.pojo.Chat;
import com.example.myspace.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChatControl {
    @Autowired
    ChatService chatService;
    @GetMapping("/myspace/chat/getPost/")
    public List<Chat> getChatPost(@RequestParam Map<String, String> data) {
        Integer index = Integer.valueOf(data.get("index"));
        return chatService.getChatByPage(index);
    }
}
