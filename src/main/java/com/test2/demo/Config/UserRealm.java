package com.test2.demo.Config;

import com.test2.demo.Po.User;
import com.test2.demo.Po.UserPo;
import com.test2.demo.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject=SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();//拿到user对象
        if(("1").equals(user.getVip())){
            System.out.println("权限为管理员");
            String perms="user:manager";
            info.addStringPermission(perms);
        }else if(("2").equals(user.getVip())){
            System.out.println("权限为老师");
            String perms="user:teacher";
            info.addStringPermission(perms);
        }
        else{
            System.out.println("权限为普通人");
            String perms="user:student";
            info.addStringPermission(perms);
        }
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken usertoken = (UsernamePasswordToken)authenticationToken;

        User user = userService.checkuser(usertoken.getUsername());

        User act_user=null;
       try {
           act_user = userService.checkuser(usertoken.getUsername());
       }catch (Exception e){}

        if(user==null || "".equals(user)){
            return null;
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setId(user.getId());


//        if(!usertoken.getUsername().equals(user.getUsername())){
//            return null;
//        }
        return new SimpleAuthenticationInfo(user1,act_user.getPassword(),"");
    }
}
