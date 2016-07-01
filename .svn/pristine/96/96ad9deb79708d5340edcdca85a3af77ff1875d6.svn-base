package com.lanen.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;



public class MyConfigurer extends PropertyPlaceholderConfigurer

{

    protected void processProperties(
            ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {

        System.out.println("MyConfigurer!");
        String UserName = props.getProperty("username");
        if (UserName != null ) {
            props.setProperty("username", DESUtil.getDesString(UserName));
        }
        String password = props.getProperty("password");
        if (password != null ) {
            props.setProperty("password", DESUtil.getDesString(password));
        }
        super.processProperties(beanFactory, props);

    }
}

