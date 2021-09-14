package blog.service;

import blog.entity.Blog;
import blog.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService {
    /**
     * 检查用户是否满足登录条件
     * @param username
     * @param password
     * @return
     */
    public Boolean loginCheck(String username, String password);

    /**
     * 返回数据库中username=username的用户数据
     * @param username
     * @return
     */
    public User getUserByUserName(String username);

}
