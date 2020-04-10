package life.yurie.community.controller;

import life.yurie.community.cache.HotTagCache;
import life.yurie.community.dto.PaginationDTO;
import life.yurie.community.schedule.HotTagTask;
import life.yurie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        // 热门标签
        List<String> hots = hotTagCache.getHots();
        model.addAttribute("hotTags", hots);
        return "index";
    }
}
