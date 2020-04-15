package life.yurie.community.controller;

import life.yurie.community.dto.CommentDTO;
import life.yurie.community.dto.QuestionDTO;
import life.yurie.community.enums.CommentTypeEnum;
import life.yurie.community.model.User;
import life.yurie.community.service.CommentService;
import life.yurie.community.service.LikeService;
import life.yurie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           HttpServletRequest request,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("likeStatus", 0);
        } else {
            model.addAttribute("likeStatus", likeService.getLikeStatus(user.getId(), id));
        }
        return "question";
    }
}
