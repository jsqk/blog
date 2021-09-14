package blog.service.impl;

import blog.entity.Type;
import blog.mapper.BlogMapper;
import blog.mapper.TypeMapper;
import blog.service.TypeService;
import blog.vo.Message;
import blog.vo.TypeCard;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public boolean saveType(Type type) {
        Type t = typeMapper.getTypeByName(type.getName());
//        System.out.println(type.toString());
        if(t == null){
            typeMapper.saveType(type);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<Type> listOnePageType(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Type> pageInfo = new PageInfo<>(typeMapper.listTypes());
        return pageInfo;
    }

    @Override
    public Message deleteTypeById(Long id) {
        Message message;
        Type type = typeMapper.getTypeById(id);
        if(type == null){
            message = new Message(false, "该分类不存在");
        }else{
            typeMapper.delete(id);
            message = new Message(true, type.getName());
        }
        return message;
    }

    @Override
    public List<Type> listAllType() {
        return typeMapper.listTypes();
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public Message updateType(Type type) {
        Message message;
        Type type1 = typeMapper.getTypeById(type.getId());
        if(type1 == null){
            message = new Message(false, "该分类不存在。");
        }else{
            typeMapper.updateType(type);
            message = new Message(true, "修改成功。");
        }
        return message;
    }

    @Override
    public List<TypeCard> listTypeCard(){
        List<Type> listType = typeMapper.listTypes();
        List<TypeCard> listTypeCard = new ArrayList<>();
        for(Type type: listType){
            TypeCard typeCard = new TypeCard();
            typeCard.setName(type.getName());
            typeCard.setIntroduce(type.getIntroduce());
            typeCard.setCover(type.getAvatar());
            typeCard.setId(type.getId());
            int counts = blogMapper.countsOfOneType(type.getId());
            typeCard.setCounts(counts);
            listTypeCard.add(typeCard);
        }
        return listTypeCard;
    }

    @Override
    public Message getNameById(Long id) {
        Type type = typeMapper.getTypeById(id);
        Message message;
        if(type == null){
            message = new Message(false, "没有该分类");
        }else {
            message = new Message(true, type.getName());
        }
        return message;
    }

    ;
}
