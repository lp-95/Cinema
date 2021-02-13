package com.cinema.backend.Security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextImpl implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static Object getBean( String beanName ) {
        return CONTEXT.getBean( beanName );
    }
}