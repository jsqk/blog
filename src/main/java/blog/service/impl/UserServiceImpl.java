package blog.service.impl;

import blog.entity.User;
import blog.mapper.UserMapper;
import blog.service.UserService;
import blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean loginCheck(String username, String password) {
        User user = userMapper.getUserByNameAndPwd(username, MD5Util.code(password));
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public User getUserByUserName(String username) {
        User user = userMapper.getUserByName(username);
        user.setPassword("");
        return user;
    }
}
