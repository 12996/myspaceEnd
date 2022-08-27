package com.example.myspace.service.Impl.post;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myspace.mapper.PostMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.service.post.PostSelectIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostSelectIdServiceImpl implements PostSelectIdService {
    @Autowired
    PostMapper postMapper;
    @Override
    public JSONObject SelectUserId(Integer userId, Integer page) {
        IPage<Post> Ipage = new Page<>(page, 6);
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        List<Post> posts = postMapper.selectPage(Ipage, queryWrapper.eq("user_id", userId).orderByDesc("sendtime")).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("total_count", postMapper.selectCount(queryWrapper.eq("user_id", userId)));
        resp.put("posts", posts);
        return resp;
    }
}
