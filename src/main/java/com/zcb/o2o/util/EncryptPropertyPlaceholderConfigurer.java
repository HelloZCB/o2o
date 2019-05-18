package com.zcb.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/***
 * 为数据库配置文件jdbc.properties的用户名和密码添加加解密
 * 原来在spring-dao.xml中配置了<context:property-placeholder location="classpath:jdbc.properties"/>
 * 它使用的是PropertyPlaceholderConfigurer类进行${..}属性的处理
 * 故需要重新写一个类，继承PropertyPlaceholderConfigurer，修改它的convertProperty方法
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    // 需要加密的字段数组
    private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            // 对已加密的字段进行解密工作
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /**
     * 该属性是否已加密
     *
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        // 若等于需要加密的field，则进行加密
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }
}
