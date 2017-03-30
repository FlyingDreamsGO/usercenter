package com.beauty.usercenter.common.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by Suo-Long
 * on 2017/3/19.
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener{
    // classpath：只会到你的class路径中查找找文件;
    // classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找.
    // contextConfigLocation 参数为空，环境默认加载的文件列表
    private static final String CONTEXT_XML_FILES = "classpath*:spring/*.xml";
    private static final String CONTAINER_XML_FILES = "classpath*:spring/container/*.container.xml";

    /**
     * 如果web.xml没有配置contextConfigLocation环境加载文件，系统默认classpath*:spring/*.xml
     */
    @Override
    protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext applicationContext) {
        String initParameter = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        if (StringUtils.isBlank(initParameter)) {
            applicationContext.setConfigLocation(CONTAINER_XML_FILES + "," + CONTEXT_XML_FILES);
        } else {
            String configs = StringUtils.join(applicationContext.getConfigLocations(), ",");
            if (!StringUtils.contains(configs, CONTAINER_XML_FILES)) {
                applicationContext.setConfigLocation(StringUtils.join(new String[] { CONTAINER_XML_FILES, configs },
                        ","));
            }
        }
        super.customizeContext(servletContext, applicationContext);
    }

}
