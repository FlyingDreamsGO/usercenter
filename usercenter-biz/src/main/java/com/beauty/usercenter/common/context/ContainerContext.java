package com.beauty.usercenter.common.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by Suo-Long
 * on 2017/3/19.
 */
public class ContainerContext {

    private static ContainerContext instance;
    private boolean inited = false;
    private boolean contextReady = false;

    private ContainerContext() {
    }

    /**
     * 环境初始化
     */
    private void initialize() {
        if (inited) {
            return;
        }
        inited = true;
        if (this.context instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) this.context)
                    .addApplicationListener(new ApplicationListener<ContextRefreshedEvent>() {
                        @Override
                        public void onApplicationEvent(ContextRefreshedEvent event) {
                            contextReady = true;
                        }
                    });
        }
    }

    public static ContainerContext get() {
        if (instance == null) {
            synchronized (ContainerContext.class) {
                if (instance == null) {
                    instance = new ContainerContext();
                }
            }
        }
        return instance;
    }

    private ApplicationContext context;

    public void setContext(ApplicationContext context) {
        this.context = context;
        this.initialize();
    }

    /**
     * @return 返回Spring容器的 ApplicationContext对象
     */
    public ApplicationContext getContext() {
        return this.context;
    }

    /**
     * 返回Spring环境上下文是否准备好。 准备好的判断依据是：Spring容器 {@link ContextRefreshedEvent}事件发生后
     *
     * @return
     */
    public boolean isContextReady() {
        return this.contextReady;
    }

}
