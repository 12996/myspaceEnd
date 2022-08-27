package com.example.myspace.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspace.mapper.ChatMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Chat;
import com.example.myspace.pojo.User;
import com.example.myspace.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ServerEndpoint("/websocket/chat/{token}")  // 注意不要以'/'结尾
public class WebSocketServerChat {
    private static ConcurrentHashMap<Integer, WebSocketServerChat> users = new ConcurrentHashMap<>();
    private Session session = null;

    private static UserMapper userMapper;
    private static ChatMapper chatMapper;
    private User user = null;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServerChat.userMapper = userMapper;
    }

    @Autowired
    public void setChatMapper(ChatMapper chatMapper){
        WebSocketServerChat.chatMapper = chatMapper;
    }

    // 分页操作未完待续
    public void GetMessage(){
        List<Chat> chats = new ArrayList<>();
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sendtime");
        chats = chatMapper.selectList(queryWrapper);
        JSONObject resp = new JSONObject();
        int i = 0 ;
        for(Chat chat : chats){
            if(i > 10) break;
            User user = userMapper.selectById(chat.getUserId());
            resp.put("chat", chat);
           // resp.put()
        }
    }

    public void  startChat(){
        Chat chat = chatMapper.selectById(1);
        JSONObject resp = new JSONObject();
        resp.put("error_message", "start");
        resp.put("post", chat);
        this.sendMessage(resp.toJSONString());
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        System.out.println("is connection");
        Integer userId = JwtUtil.getUserId(token);
        User user = userMapper.selectById(userId);
        if(user != null){
            this.user = user;
            users.put(user.getId(), this);
           // startChat();
        }else{
           this.session.close();
        }
    }


    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("is disconnection");
        WebSocketServerChat.users.remove(user.getId());
    }

    public void chatWithAll(JSONObject data){
        JSONObject resp = new JSONObject();
        Chat chat = new Chat(null, user.getId(), data.getInteger("send_id"),
                data.getString("message"), new Date());
        int id = chatMapper.insert(chat);
        resp.put("error_message", "success");
        resp.put("id", id);
        resp.put("message", chat.getMessage());
        resp.put("sendtime", chat.getSendtime());
        resp.put("sendId", chat.getSendId());
        resp.put("userId", user.getId());
        resp.put("username", user.getUsername());
        resp.put("photo", user.getPhoto());
        for(Integer key : users.keySet()){
            users.get(key).sendMessage(resp.toJSONString());
        }
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        System.out.println("send message");
        JSONObject data = JSON.parseObject(message);
        String event = data.getString("event");
        String chatContent = data.getString("message");
        JSONObject resp = new JSONObject();
        if(chatContent == null || chatContent.length() == 0){
            resp.put("error_message", "发送数据不能为空");
            this.sendMessage(resp.toJSONString());
            return;
        }
        if(chatContent.length() >500){
            resp.put("error_message", "数据过长不应大于500");
            this.sendMessage(resp.toJSONString());
            return;
        }
        if("all".equals(event)){
            chatWithAll(data);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String Message){
        try {
            this.session.getBasicRemote().sendText(Message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
