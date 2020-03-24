package life.yurie.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showFirst;
    private boolean showLast;
    private boolean showPrevious;
    private boolean showNext;
    private Integer currentPage;
    private int totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        totalPage = (int)Math.ceil(totalCount * 1.0 / size);
        currentPage = page;
        if (page < 1) {
            currentPage = 1;
        }
        if (page > totalPage) {
            currentPage = totalPage;
        }
        if (currentPage != 1) {
            showPrevious = true;
        }
        if (currentPage != totalPage)
            showNext = true;
        pages.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0)
                pages.add(0, currentPage - i);
            if (currentPage + i <= totalPage)
                pages.add(currentPage + i);
        }
        if (!pages.contains(1))
            showFirst = true;
        if (!pages.contains((totalPage)))
            showLast = true;
    }
}
