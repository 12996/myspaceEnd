package com.example.myspace.service.follower;

import java.util.Map;

public interface FollowerSelectService {
    Map<String,String> select(Integer follower_user,Boolean f);
}
