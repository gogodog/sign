package com.pw.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.entity.Idea;
import com.pw.sign.entity.SignSign;
import com.pw.sign.service.IdeaService;
import com.pw.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

    @GetMapping("/route/{id}")
    public String routePage(@PathVariable(value = "id")String id) {
        return id;
    }

    @GetMapping("/detail/{id}")
    public String detailPage(@PathVariable(value = "id")Integer id, ModelMap model) {
        Idea idea = this.ideaService.getById(id);
        List<SignSign> signs = this.signService.listByIdeaId(id);
        model.put("idea", idea);
        model.put("signs", signs);
        return "detail";
    }

    @GetMapping("/index")
    public String indexPage(ModelMap model) {
        List<Idea> ideas = this.ideaService.getList();
        log.info(JSONObject.toJSONString(ideas));
        model.addAttribute("list", ideas);
        return "index";
    }

}
