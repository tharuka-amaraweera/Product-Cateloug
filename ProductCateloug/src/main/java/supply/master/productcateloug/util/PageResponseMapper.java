package supply.master.productcateloug.util;

import org.springframework.data.domain.Page;
import supply.master.productcateloug.dto.PageResponse;

public class PageResponseMapper {
    public static <T> PageResponse<T> toPageResponse(Page<T> page){
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getPageable().getOffset(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getNumberOfElements()
        );
    }
}
