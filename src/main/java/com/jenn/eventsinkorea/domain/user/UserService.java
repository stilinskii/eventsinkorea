package com.jenn.eventsinkorea.domain.user;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers(); // 실제 서스에서 사용되지는 않음. 모든 유저를 불러오는것은 비효율적. 페이지를 불러옴

}
