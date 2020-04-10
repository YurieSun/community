package life.yurie.community.provider;

public class RedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_EVENT = "EVENT";

    public static String getEventQueueKey() {
        return BIZ_EVENT;
    }

    public static String getLikeKey(Long questionId) {
        return BIZ_LIKE + SPLIT + String.valueOf(questionId);
    }
}
