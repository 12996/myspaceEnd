package com.example.myspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myspace.pojo.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
}
