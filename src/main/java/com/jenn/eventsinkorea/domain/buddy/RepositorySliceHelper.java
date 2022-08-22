package com.jenn.eventsinkorea.domain.buddy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class RepositorySliceHelper {
    public static <T> Slice<T> toSlice(List<T> contents, Pageable pageable) {

        boolean hasNext = isContentSizeGreaterThanPageSize(contents, pageable);
        return new SliceImpl<>(hasNext ? subListLastContent(contents, pageable) : contents, pageable, hasNext);
    }

    // 다음 페이지 있는지 확인
    private static <T> boolean isContentSizeGreaterThanPageSize(List<T> content, Pageable pageable) {
        log.info("buddyisze={}",content.size());
        return pageable.isPaged() && content.size() > pageable.getPageSize();
    }

    // 데이터 1개 빼고 반환
    private static <T> List<T> subListLastContent(List<T> content, Pageable pageable) {
        return content.subList(0, pageable.getPageSize());
    }
}
