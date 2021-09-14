package blog.interceptor;

import blog.util.Setting;
import org.springframework.boot.autoconfigure.web.reactive.ResourceHandlerRegistrationCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class ResourceHandler {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/");//配置浏览器直接访问static下的静态资源
        registry.addResourceHandler("/blog/**").addResourceLocations("file:" + Setting.BLOG_IMG_PATH);//配置图片物理存储路径和虚拟访问路径
    }
}
