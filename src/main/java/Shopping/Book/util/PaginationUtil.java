package Shopping.Book.util;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationUtil {

    public static Map<String, Integer> Pagination(Page<?> page) {
        Map<String, Integer> paginationMap = new HashMap<>();

        int currentPage = page.getNumber() + 1;
        int totalPages = page.getTotalPages();
        int blockLimit = Math.min(5, totalPages);

        int startPage = Math.max(1, currentPage - blockLimit / 2);
        System.out.println(startPage);
        int endPage = Math.min(startPage + blockLimit - 1, totalPages);
        System.out.println(endPage);

        int prevPage = Math.max(1, currentPage - 1);
        int nextPage = Math.min(totalPages, currentPage + 1);

        int lastPage = totalPages;

        paginationMap.put("startPage", startPage);
        paginationMap.put("endPage", endPage);
        paginationMap.put("prevPage", prevPage);
        paginationMap.put("nextPage", nextPage);
        paginationMap.put("lastPage", lastPage);
        paginationMap.put("currentPage", currentPage);

        return paginationMap;
    }
}
