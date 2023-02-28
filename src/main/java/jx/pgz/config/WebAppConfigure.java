package jx.pgz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 *
 */
@Configuration
public class WebAppConfigure implements WebMvcConfigurer {


    @Value("${attachment.rootPath}")
    public String filePath;

    @Value("${attachment.accessPath}")
    public String accessPath;


    @Resource
    private PropertiesConfiguration propertiesConfiguration;

    /**
     * 配置静态资源访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessPath).addResourceLocations("file:" + filePath);
    }


}