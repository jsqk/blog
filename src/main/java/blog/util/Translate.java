package blog.util;

import java.util.ArrayList;
import java.util.List;

public class Translate {

    public static List<Long> getTagIds(String tagIs){
        List<Long> ids = new ArrayList<>();
        String a = "";
        for(int i=0; i< tagIs.length(); i++){
            if(tagIs.charAt(i) != ','){ // 还未碰到分隔符
                a = a + tagIs.charAt(i);
//                System.out.println(tagIs.charAt(i));
            }else{ // 碰到了分隔符，将数字添加到集合中，并将 a 重置
                int b = Integer.valueOf(a);
                ids.add((long) b);
                a = "";
            }
        }
        if(a != ""){
            int b = Integer.valueOf(a);
            ids.add((long) b);
        }
        return ids;
    }
}
