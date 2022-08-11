package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringOption;

import java.util.List;

public interface BuddyDAO {

    List<Buddy> filteringBuddy(BuddyFilteringOption option);
}
