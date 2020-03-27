package life.yurie.community.cache;

import life.yurie.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class HotTagCache {
    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags) {
        int max = 3;
        PriorityQueue<HotTagDTO> queue = new PriorityQueue<>();
        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (queue.size() < max) {
                queue.add(hotTagDTO);
            } else {
                if (hotTagDTO.compareTo(queue.peek()) > 0) {
                    queue.poll();
                    queue.add(hotTagDTO);
                }
            }
        });
        List<String> sortedTags = new ArrayList<>();
        while (!queue.isEmpty()) {
            sortedTags.add(0, queue.poll().getName());
        }
        hots = sortedTags;
    }
}
