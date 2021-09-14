package blog.service.impl;

import blog.entity.TimeLine;
import blog.mapper.TimeLineMapper;
import blog.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: hanzy
 * Date: 2021/9/10, 18:57
 * introduce:
 */
@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired
    private TimeLineMapper timeLineMapper;

    @Override
    public List<TimeLine> getAllTimeLine() {
        return timeLineMapper.getAllTimeLine();
    }
}
