package com.jenn.eventsinkorea.domain.buddy.repository;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringSortingOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BuddyDAO {

    Slice<Buddy> filteringBuddy(BuddyFilteringSortingOption option, Pageable pageable);
    Page<Buddy> filteringAndSortingBuddy(BuddyFilteringSortingOption option);
}
