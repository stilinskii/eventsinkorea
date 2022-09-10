package com.jenn.eventsinkorea.domain.user;

import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDAO {

    List<User> findUsersBySearch(UserSearchForm searchForm);
    Page<User> findUsersBySearchPage(UserSearchForm searchForm, Pageable pageable);

    Long todayJoinedUsersCnt();
}
