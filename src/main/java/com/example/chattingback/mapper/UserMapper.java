package com.example.chattingback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chattingback.eneity.dbEntities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    ArrayList<User> selectAll();
}
