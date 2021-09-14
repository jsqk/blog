package blog.entity;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LeaveWord {
    private int id; // 留言的唯一标识符
    private String email;  // 邮箱
    private String content; // 留言内容
    private String username; // 用户名
    private Date createTime; // 留言发布时间
    private int parentId; // 父亲留言的id
    private boolean status; // 是否已被处理
    private List<LeaveWord> childLeaveWord;
    private LeaveWord parentLW;

    public LeaveWord getParentLW() {
        return parentLW;
    }

    public void setParentLW(LeaveWord parentLW) {
        this.parentLW = parentLW;
    }

    public List<LeaveWord> getChildLeaveWord() {
        return childLeaveWord;
    }

    public void setChildLeaveWord(List<LeaveWord> childLeaveWord) {
        this.childLeaveWord = childLeaveWord;
    }

    @Override
    public String toString() {
        return "LeaveWord{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", parentId=" + parentId +
                ", status=" + status +
                ", childs = " + childLeaveWord.size() +
                '}';
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
