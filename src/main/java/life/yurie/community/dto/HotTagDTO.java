package life.yurie.community.dto;

import lombok.Data;

@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.priority - ((HotTagDTO) o).getPriority();
    }
}
