package com.pw.sign.common.load;

import com.pw.sign.entity.SignIdeaTag;
import com.pw.sign.service.IdeaTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 启动加载业务数据
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月18日
 */
@Component
@Slf4j
public class BusinessDataConfig {

    public static List<SignIdeaTag> ideaTags = new ArrayList<>();
    public static Map<String, List<SignIdeaTag>> ideaTagsGroup = new HashMap<>();
    public static List<List<SignIdeaTag>> ideaTagsGroupOrder = new ArrayList<>();

    @Resource
    IdeaTagService ideaTagService;

    @PostConstruct
    public void loadSignIdeaTag() {
        log.info("----------------------加载标签类 开始---------------------");
        ideaTags = this.ideaTagService.list();
        ideaTags.sort((o1, o2) -> o1.getOrders().compareTo(o2.getOrders()));
        ideaTagsGroup = ideaTags.stream().collect(Collectors.groupingBy(d -> d.getParentId()));
        log.info("----------------------加载标签类 结束---------------------");
    }
}