package com.beauty.usercenter.common.spring;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * Created by Suo-Long
 * on 2017/3/19.
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {
    private static final long serialVersionUID = 8022893821282529225L;
    private static final String CONTEXT_XML_FILES = "classpath*:spring-mvc/container/*.container.xml,classpath:spring-mvc/*.xml";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public String getContextConfigLocation() {
        String configLocation = super.getContextConfigLocation();
        if (StringUtils.isBlank(configLocation)) {
            configLocation = CONTEXT_XML_FILES;
        }
        return configLocation;
    }
}
