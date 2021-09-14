package blog.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: hanzy
 * Date: 2021/9/10, 18:47
 * introduce:
 */
@Component
public class TimeLine {
    private Long id; // 唯一标识符
    private String title; // 标题
    private String content; // 内容
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime; // 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
