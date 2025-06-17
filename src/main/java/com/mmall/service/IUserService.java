package com.mmall.service;

import com.mmall.common.ServiceResponse;
import com.mmall.pojo.User;

public interface IUserService {
    ServiceResponse<User> login(String username, String password);
    ServiceResponse<String> register(User user);
    ServiceResponse<String> checkValid(String str,String type);
    ServiceResponse selectQuestion(String username);
    ServiceResponse checkAnswer(String username,String question,String answer);
    ServiceResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    ServiceResponse<String> resetPassword(User user,String passwordOld,String passwordNew);
    ServiceResponse<User> updateUserInfo(User user);
    ServiceResponse<User> getInfo(Integer userId);
    ServiceResponse checkAdminRole(User user);









}
