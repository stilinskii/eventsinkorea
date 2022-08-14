package com.jenn.eventsinkorea.domain.user;

import com.jenn.eventsinkorea.domain.user.User;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;

import java.util.List;

public interface UserDAO {

    List<User> findUsersBySearch(UserSearchForm searchForm);
}
