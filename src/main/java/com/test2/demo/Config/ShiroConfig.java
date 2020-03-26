package com.test2.demo.Config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        System.out.println("获取cookie");
        cookie.setMaxAge(86400);
        return cookie;
    }

    /**
     * cookie管理对象
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
        // KeyGenerator keygen = KeyGenerator.getInstance("AES");
        // SecretKey deskey = keygen.generateKey();
        // System.out.println(Base64.encodeToString(deskey.getEncoded()));
        cookieRememberMeManager.setCipherKey(Base64.decode("vXP33AonIp9bFwGl7aT7rA=="));
        return cookieRememberMeManager;
    }
    /*
    创建一个shiroFilterFactoryBean
     */
   @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
        anon:无需认证可以访问
        authc:必须认证了才能访问
        user:必须有记住我功能才能访问
        perms:拥有对某个资源的权限才能访问
        role:拥有对某个角色权限才能访问
         */
        Map<String,String> map = new LinkedHashMap<String, String>();
        map.put("/test","anon");
        map.put("/test/login","anon");
        map.put("/test/echarts","anon");
        map.put("/test/add","perms[user:add]");
        map.put("/test/update","perms[user:update]");
        map.put("/image/**", "anon");
        //拦截
        //map.put("/test/*","authc");
        //map.put("/test/**","user");

        //修改跳转页面
        shiroFilterFactoryBean.setLoginUrl("/test");

        //权限错误跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/test/uncall");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /*
    创建一个DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
