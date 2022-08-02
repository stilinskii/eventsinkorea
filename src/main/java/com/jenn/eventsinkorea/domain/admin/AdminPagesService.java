package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPagesService {
    private final PageRepository pageRepository;

    public List<Page> getPages(){
        return pageRepository.findAllByOrderBySortingAsc();
    }


}
