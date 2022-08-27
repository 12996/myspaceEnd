package com.example.myspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myspace.pojo.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  ChatMapper extends BaseMapper<Chat> {
}
