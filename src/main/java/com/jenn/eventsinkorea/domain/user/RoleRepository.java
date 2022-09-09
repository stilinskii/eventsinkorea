package com.jenn.eventsinkorea.domain.user;

import com.jenn.eventsinkorea.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String name);
}
