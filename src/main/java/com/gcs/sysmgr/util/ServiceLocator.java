package com.gcs.sysmgr.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

@Controller
public class ServiceLocator implements ApplicationContextAware {
	
    private static ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext context) throws BeansException {
    	ServiceLocator.applicationContext = context;
    }
    public static Object lookup(String name){
        return applicationContext.getBean(name);
    }
    @SuppressWarnings("unchecked")
	public static Object lookup(Class clazz){
        return applicationContext.getBean(clazz);
    }
}