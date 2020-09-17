package com.evan.wj.conrtoller;

import com.evan.wj.dao.UserDAO;
import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.evan.wj.pojo.User;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @CrossOrigin
    @PostMapping( value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session){
        //对HTML标签进行转义，防止xss攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        /*设置session失效时间，单位（秒）*/
//        session.setMaxInactiveInterval(30);

        User user = userService.get(username, requestUser.getPassword());

        if (user == null){
            String message = "账号密码错误！";
            return new Result(400);
        }else {
            session.setAttribute("user",user);
            return new Result(200);
        }
    }
}
