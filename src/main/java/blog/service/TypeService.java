package blog.service;

import blog.entity.Type;
import blog.vo.Message;
import blog.vo.TypeCard;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TypeService {

    public boolean saveType(Type type);

    public PageInfo<Type> listOnePageType(int page, int pageSize);

    /**
     * 根据id删除分类，并返回分类的name属性
     * @param id
     * @return
     */
    public Message deleteTypeById(Long id);

    public List<Type> listAllType();

    public Type getTypeById(Long id);

    public Message updateType(Type type);

    /**
     * 获取所有分类所对应的typecard的信息集合
     * @return
     */
    public List<TypeCard> listTypeCard();

    /**
     * 根据id获取某一个分类的名字
     * @param id
     * @return
     */
    public Message getNameById(Long id);

}
