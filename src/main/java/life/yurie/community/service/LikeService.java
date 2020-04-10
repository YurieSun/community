package life.yurie.community.service;


import life.yurie.community.provider.JedisAdapter;
import life.yurie.community.provider.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    JedisAdapter jedisAdapter;

    public int getLikeStatus(Long userId, Long questionId) {
        String likeKey = RedisKeyUtil.getLikeKey(questionId);
        if (jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
            return 1;
        }
        return 0;
    }

    public long like(Long userId, Long questionId) {
        // 在喜欢集合里增加
        String likeKey = RedisKeyUtil.getLikeKey(questionId);
        jedisAdapter.sadd(likeKey, String.valueOf(userId));
        return jedisAdapter.scard(likeKey);
    }

    public long dislike(Long userId, Long questionId) {
        // 在喜欢集合里删除当前user的id
        String likeKey = RedisKeyUtil.getLikeKey(questionId);
        jedisAdapter.srem(likeKey, String.valueOf(userId));
        return jedisAdapter.scard(likeKey);
    }
}
