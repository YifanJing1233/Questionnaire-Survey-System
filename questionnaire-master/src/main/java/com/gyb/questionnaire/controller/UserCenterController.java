package com.gyb.questionnaire.controller;

import javax.validation.constraints.NotBlank;
import com.gyb.questionnaire.config.RequiredLogin;
import com.gyb.questionnaire.controller.form.UpdateUserForm;
import com.gyb.questionnaire.entity.User;
import com.gyb.questionnaire.service.LoginUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户个人中心
 *
 */
@Validated
@Controller
public class UserCenterController {
    private final LoginUserService loginUserService;


    public UserCenterController(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;

    }

    /**
     * 获取个人信息
     */
    @GetMapping("/user/profile")
    @RequiredLogin
    public String userProfile(Model m){
        final User loginUser = LoginUserService.getLoginUser();
        m.addAttribute("u",loginUser);
        return "user/profile";
    }



    @PostMapping("/user/modifyProfile")
    @RequiredLogin
    @ResponseBody
    public ResponseResult modifyProfile(UpdateUserForm form){
        loginUserService.modifyUserInfo(form);
        return ResponseResult.ok();
    }
    @PostMapping("/user/modifyPassword")
    @RequiredLogin
    @ResponseBody
    public ResponseResult modifyPassword(@NotBlank(message = "请输入原密码") String oldPassword,
                                         @NotBlank(message = "请输入新密码") String newPassword){
        return loginUserService.modifyPassword(oldPassword,newPassword);
    }




}
