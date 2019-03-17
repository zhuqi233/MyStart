package com.gdl.schedule.controller;

import com.gdl.schedule.entity.ResponseResult;
import com.gdl.schedule.entity.User;
import com.gdl.schedule.service.UserService;
import com.gdl.schedule.service.ex.PasswordNotMatchException;
import com.gdl.schedule.service.ex.UsernameNotFoundException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
public class UserController {

	@Autowired
	private DefaultKaptcha defaultKaptcha;
	@Autowired
	private UserService userService;
	@RequestMapping("/")
	public String login() { return "login"; }
    @RequestMapping("/welcome")
    public String welcome() { return "welcome"; }
    @RequestMapping("/index")
    public String index(ModelMap modelMap, HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        if(uid == null){
            return "redirect:/";
        }
	    return "index";
	}
    //处理登录
    @RequestMapping(value="/handle_login", method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult handleLogin(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      @RequestParam("checkcode") String checkCode,
                                      HttpSession session) {
        // 声明返回值
        // 无论登录成功与否，都返回ResponseResult<Void>对象
        ResponseResult rr;
        try {
            String oldCode = (String) session.getAttribute("checkCode");
            if (!oldCode.equals(checkCode.toUpperCase())){
                rr = new ResponseResult(0, "验证码不正确");
                return rr;
            }
            // 调用userService完成登录
            User user = userService.login(username, password);
            // 如果登录成功，在返回之前，
            // 调用HttpSession对象的setAttribute(String, Object)方法封装uid及username
            session.setAttribute("uid", user.getId());
            session.setAttribute("username", user.getUserName());
            session.setAttribute("nickname", user.getNickName());
            rr = new ResponseResult(1,"登录成功");
        } catch (UsernameNotFoundException e) {
            // 用户名错误
            rr = new ResponseResult(-1, e);
        } catch (PasswordNotMatchException e) {
            // 密码错误
            rr = new ResponseResult(-2, e);
        }
        // 返回
        return rr;
    }

    //验证码验证
    @RequestMapping("/checkCode")
    @ResponseBody
    public ResponseResult checkCodeHandle(HttpServletRequest Request){
	    ResponseResult rr = null;
        String newCode = Request.getParameter("code").toUpperCase();
        String oldCode = (String) Request.getSession().getAttribute("vrifyCode");
        if (newCode.equals(oldCode.toUpperCase())){
            rr = new ResponseResult(1,true);
        }else {
            rr = new ResponseResult(0,false);
        }
        return rr;
    }
    //获取验证码
    @RequestMapping("/getCode")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,HttpSession session)
            throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute("checkCode",createText.toUpperCase());
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
