package honbab.board.article.service;

import honbab.board.article.entity.Article;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class PageLimitCalculator {

    public static Long calculatePageLimit(Long page, Long pageSize, Long movablePageCount) {
        return ((page - 1) / movablePageCount + 1) * pageSize * movablePageCount + 1;
    }
}
