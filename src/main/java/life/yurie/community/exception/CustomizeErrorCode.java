package life.yurie.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不存在"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题，要不换一个试试"),
    NOT_LOGIN(2003, "未登录不能进行评论，请先登录"),
    SYS_ERROR(2004, "稍后试试？"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了"),
    COMMENT_IS_EMPTY(2007,"回复内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"无法读取别人消息"),
    NOTIFICATION_NOT_FOUND(2009,"消息不见了");

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
