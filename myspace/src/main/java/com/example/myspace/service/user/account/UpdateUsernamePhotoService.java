package com.example.myspace.service.user.account;

import java.util.Map;

public interface UpdateUsernamePhotoService {
    Map<String, String> updateUsernamePhoto(Map<String, String> data);

    Map<String, String> updatePassword(Map<String, String> data);
}
