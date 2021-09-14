package blog.service.impl;

import blog.entity.LeaveWord;
import blog.mapper.LWMapper;
import blog.service.LWService;
import blog.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: hanzy
 * Date: 2021/9/12, 22:46
 * introduce:
 */
@Service
public class LWServiceImpl implements LWService {
    @Autowired
    private LWMapper lwMapper;

    private List<LeaveWord> child = new ArrayList<>();

    @Override
    public List<LeaveWord> getOnePage(int page, int pageSize) {
        List<LeaveWord> leaveWordList = lwMapper.getAll();
        for(LeaveWord leaveWord: leaveWordList){
            child = new ArrayList<>();;
            recursively(leaveWord.getChildLeaveWord());
            leaveWord.setChildLeaveWord(child);
        }
        return leaveWordList;
    }

    private void recursively(List<LeaveWord> leaveWord){
        if (leaveWord == null) {
            return ;
        }
        for(LeaveWord leave: leaveWord){
            child.add(leave);
            recursively(leave.getChildLeaveWord());
        }
    }

    @Override
    public Message save(LeaveWord leaveWord) {
        Message message = modify(leaveWord);
        System.out.println(message.getMessage());
        if(message.isFlag()){
            leaveWord.setStatus(false);
            leaveWord.setCreateTime(new Date());
            lwMapper.insert(leaveWord);
        }
        return message;
    }

    private Message modify(LeaveWord leaveWord){
        Message message;
        if(leaveWord.getContent() == null || "".equals(leaveWord.getContent())){
            message = new Message(false, "留言内容不能为空。");
        }else if(leaveWord.getEmail() == null || "".equals(leaveWord.getEmail())){
            message = new Message(false, "邮箱不能为空。");
        }else if(leaveWord.getUsername() == null || "".equals(leaveWord.getUsername())){
            message = new Message(false, "用户名不能为空。");
        }else if(leaveWord.getId() != 0 && lwMapper.getById(leaveWord.getId()) == null){
            message = new Message(false, "你回复的留言已经不存在。");
        }else{
            message = new Message(true, "留言成功O(∩_∩)O。");
        }
        return message;
    }

}
