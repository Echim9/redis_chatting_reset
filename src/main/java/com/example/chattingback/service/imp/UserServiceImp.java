package com.example.chattingback.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chattingback.controller.socketIO.ServerRunner;
import com.example.chattingback.eneity.dbEntities.User;
import com.example.chattingback.eneity.response.Response;
import com.example.chattingback.enums.Rcode;
import com.example.chattingback.mapper.UserMapper;
import com.example.chattingback.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private ServerRunner serverRunner;
    @Resource
    private UserMapper usermapper;
    @Override
    public Response postUsers(String userIds) {
        ArrayList<User> users = new ArrayList<>();
        try {
            if (userIds.length() > 0) {
                String[] id = userIds.split(",");
                for (String userId : id) {
                    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                    userQueryWrapper.eq("groupId", userId);
                    User user = usermapper.selectOne(userQueryWrapper);
                    if (user == null) {
                        users.add(user);
                    }
                }
                return new Response()
                        .builder()
                        .msg("获取用户信息成功")
                        .data(users)
                        .build();
            }
            return new Response()
                    .builder()
                    .msg("获取用户信息失败")
                    .code(Rcode.FAIL)
                    .data("")
                    .build();
        }catch (Exception e) {
            return new Response()
                    .builder()
                    .msg("获取用户失败")
                    .code(Rcode.ERROR)
                    .data("")
                    .build();
        }
    }
}
