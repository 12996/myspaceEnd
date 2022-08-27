package com.example.myspace.service.Impl.post;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myspace.mapper.PostMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.pojo.User;
import com.example.myspace.service.post.PostGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PostGetListServiceImpl implements PostGetListService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public JSONObject getPostList(Integer page) {
        IPage Ipage = new Page(page, 8);
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        List<Post> posts = postMapper.selectPage(Ipage, queryWrapper.orderByDesc("sendtime")).getRecords();
        List<JSONObject> items = new LinkedList<>();
        for(Post post : posts){
            User user = userMapper.selectById(post.getUserId());
            JSONObject item = new JSONObject();
            item.put("username", user.getUsername());
            item.put("photo", user.getPhoto());
            item.put("post", post);
            items.add(item);
        }
        JSONObject resp = new JSONObject();
        resp.put("posts", items);
        resp.put("total_count", postMapper.selectCount(null));
        return resp;
    }
}
