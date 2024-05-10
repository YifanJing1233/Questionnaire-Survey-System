package com.gyb.questionnaire.service;

import com.gyb.questionnaire.controller.ResponseResult;
import com.gyb.questionnaire.controller.form.UpdateUserForm;
import com.gyb.questionnaire.dao.UserDao;
import com.gyb.questionnaire.entity.User;
import com.gyb.questionnaire.util.EncryptUtil;
import com.gyb.questionnaire.util.HttpServletUtil;
import com.gyb.questionnaire.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.gyb.questionnaire.config.GlobalConstant.SESSION_KEY_CURR_USER;
import static com.gyb.questionnaire.config.GlobalConstant.SESSION_KEY_EMAIL_CODE;

/**
 * 当前登录用户Service
 *
 */
@Slf4j
@Service
public class LoginUserService {
    private final UserDao userDao;


    public LoginUserService(UserDao userDao) {
        this.userDao = userDao;

    }

    public static User getLoginUser() {
        return (User) (HttpServletUtil.getSession().getAttribute(SESSION_KEY_CURR_USER));
    }

    @Transactional
    public void modifyUserInfo(UpdateUserForm form){
        final User loginUser = getLoginUser();
        final User user = userDao.find(loginUser.getId());
        user.setAge(form.getAge());
        user.setSex(form.getSex());
        user.setRealName(form.getRealName());
        final int res = userDao.update(user);
        updateLoginUserInfo(user);
        if(res != 1)
            log.error("更新用户信息失败！");
    }

    private void updateLoginUserInfo(User user) {
        HttpServletUtil.getSession().setAttribute(SESSION_KEY_CURR_USER,user);
    }

    @Transactional
    public ResponseResult modifyPassword(String oldPassword, String newPassword) {
        final User loginUser = getLoginUser();
        final User user = userDao.find(loginUser.getId());
        final String passwordSalt = user.getPasswordSalt();
        final String oldPass = EncryptUtil.encryptPassword(oldPassword, passwordSalt);
        assert oldPass != null;
        if(!oldPass.equals(user.getPassword())){
            return ResponseResult.error("原密码错误！",null);
        }
        final String newPass = EncryptUtil.encryptPassword(newPassword, passwordSalt);
        if(newPass != null && newPass.equals(oldPass)){
            return ResponseResult.error("新密码不能与原密码相同！",null);
        }
        userDao.updatePassword(user.getId(),newPass);
        return ResponseResult.ok();
    }

}
