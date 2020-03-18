package life.yurie.community.mapper;

import life.yurie.community.model.Comment;
import life.yurie.community.model.CommentExample;
import life.yurie.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}