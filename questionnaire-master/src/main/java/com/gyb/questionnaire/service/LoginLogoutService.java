package com.gyb.questionnaire.service;

import com.gyb.questionnaire.config.constans.UserStatusEnum;
import com.gyb.questionnaire.controller.ResponseResult;
import com.gyb.questionnaire.controller.form.LoginForm;
import com.gyb.questionnaire.dao.UserDao;
import com.gyb.questionnaire.entity.User;
import com.gyb.questionnaire.util.EncryptUtil;
import com.gyb.questionnaire.util.HttpServletUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.gyb.questionnaire.config.GlobalConstant.SESSION_KEY_CURR_USER;
import static com.gyb.questionnaire.config.GlobalConstant.SESSION_KEY_LOGIN_ERR_COUNT;

/**
 * 用户登录登出Service
 *
 */

@Service
public class LoginLogoutService {
    private final UserDao userDao;


    public LoginLogoutService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponseResult login(LoginForm loginForm){
        final List<User> users = userDao.findByUsername(loginForm.getUsername());
        final HttpSession session = HttpServletUtil.getSession();
        if(users == null || users.isEmpty()){
            int count = incrementLoginErrorCount(session);
            return ResponseResult.error("Incorrect username or password",count);
        }
        User user = users.get(0);
        final String inputPass = EncryptUtil.encryptPassword(loginForm.getPassword(), user.getPasswordSalt());
        if(inputPass != null && inputPass.equals(user.getPassword())){
            //如果用户被锁定
            if(user.getStatus() == UserStatusEnum.LOCKED.getStatus())
                return ResponseResult.error("User is locked！",null);
            session.setAttribute(SESSION_KEY_CURR_USER,user);
            session.removeAttribute(SESSION_KEY_LOGIN_ERR_COUNT); //登录成功清楚登录错误计数
            return ResponseResult.ok("login success！",null);
        }
        int count = incrementLoginErrorCount(session);
        return ResponseResult.error("Incorrect username or password",count);
    }
    private int incrementLoginErrorCount(HttpSession session){
        Integer count = (Integer) (session.getAttribute(SESSION_KEY_LOGIN_ERR_COUNT)==null?0:
                session.getAttribute(SESSION_KEY_LOGIN_ERR_COUNT));
        session.setAttribute(SESSION_KEY_LOGIN_ERR_COUNT,count+1);
        return count+1;
    }
}
