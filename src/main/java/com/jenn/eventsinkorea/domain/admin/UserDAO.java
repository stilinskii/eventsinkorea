package com.jenn.eventsinkorea.domain.admin;

import com.jenn.eventsinkorea.domain.admin.model.User;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;

import java.util.List;

public interface UserDAO {

    List<User> findUsersBySearch(UserSearchForm searchForm);
}
