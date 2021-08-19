package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.aop.annotation.LogAnnotation;
import com.pw.sign.common.aop.annotation.NeedLogin;
import com.pw.sign.controller.helper.RouteControllerHelper;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SignSign;
import com.pw.sign.enums.BooleanInt;
import com.pw.sign.service.IdeaService;
import com.pw.sign.service.SignService;
import com.pw.sign.service.UserService;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 视图
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@Controller
@RequestMapping("/fast")
@Slf4j
public class RouteController {

    @Resource
    IdeaService ideaService;
    @Resource
    SignService signService;
    @Resource
    XUserService xuserService;

    @GetMapping("/route/n/{id}")
    public String routeNoLoginPage(@PathVariable(value = "id") String id) {
        return id;
    }

    @GetMapping("/route/u/{id}")
    public String routeLoginPage(@PathVariable(value = "id") String id, ModelMap model) {
        UserVo user = this.xuserService.getUserFromCookie();
        if (user == null) {
            return "redirect:/fast/index?nLogin=" + BooleanInt.YES.getEnCode();
        }
        return id;
    }

    @GetMapping("/create")
    @NeedLogin
    public String createPage(UserVo userVo, ModelMap model) {
        return "create";
    }

    @GetMapping("/detail/{id}")
    @LogAnnotation
    public String detailPage(@PathVariable(value = "id") Integer id, ModelMap model) {
        Idea idea = this.ideaService.getById(id);
        List<SignSign> signs = this.signService.listByIdeaId(id);
        UserVo author = this.xuserService.findByUserId(idea.getCreator());
        model.put("idea", idea);
        model.put("signs", signs);
        model.put("author", author);
        return "detail";
    }

    @GetMapping("/index")
    public String indexPage(ModelMap model, String nLogin) {
        List<Idea> ideas = this.ideaService.getList();
        log.info(JSONObject.toJSONString(ideas));
        nLogin = RouteControllerHelper.needLoginHandler(xuserService, nLogin);
        model.addAttribute("list", ideas);
        model.addAttribute("nLogin", nLogin);
        return "index";
    }

}
