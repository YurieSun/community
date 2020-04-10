package life.yurie.community.controller;

import life.yurie.community.dto.QuestionDTO;
import life.yurie.community.dto.ResultDTO;
import life.yurie.community.model.User;
import life.yurie.community.service.LikeService;
import life.yurie.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/like", method = RequestMethod.GET)
    public Object like(@RequestParam(name = "id") String id, HttpServletRequest request) {
        Long questionId = Long.parseLong(id);
        User user = (User) request.getSession().getAttribute("user");
        int likeStatus = likeService.getLikeStatus(user.getId(), questionId);
        if (likeStatus == 0) {
            long likeCount = likeService.like(user.getId(), questionId);
            // 更新喜欢数
            questionService.updateLikeCount(questionId, likeCount);
        } else if (likeStatus == 1) {
            long likeCount = likeService.dislike(user.getId(), questionId);
            // 更新喜欢数
            questionService.updateLikeCount(questionId, likeCount);
        }
        return ResultDTO.okOf();
    }
}
