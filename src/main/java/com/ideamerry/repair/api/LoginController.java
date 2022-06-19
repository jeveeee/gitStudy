package com.ideamerry.repair.api;

import com.ideamerry.repair.entity.JsonResult;
import com.ideamerry.repair.entity.SysUser;
import com.ideamerry.repair.service.SysUserService;
import com.ideamerry.repair.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
@Controller
@RequestMapping("/api")
public class LoginController {

    @Resource
    private SysUserService sysUserService;
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doLogin(HttpServletRequest request, @RequestParam(required = true) String username, @RequestParam(required = true) String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        List<SysUser> users = sysUserService.queryAll(sysUser);
        if (users != null && users.size() > 0) {
            sysUser = users.get(0);
            if (!MD5Util.md5(password).equals(sysUser.getUserPassword())) {
                return new JsonResult(500, "用户名或密码错误");
            }
            sysUser.setUserPassword("");
            return new JsonResult(200, "登录成功", sysUser);
        } else {
            return new JsonResult(500, "用户不存在");
        }
    }
}
