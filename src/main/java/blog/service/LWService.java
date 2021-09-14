package blog.service;

import blog.entity.LeaveWord;
import blog.vo.Message;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LWService {

    public List<LeaveWord> getOnePage(int page, int pageSize);

    /**
     * 向t_lw表中插入一条留言
     * @param leaveWord
     * @return
     */
    public Message save(LeaveWord leaveWord);
}
