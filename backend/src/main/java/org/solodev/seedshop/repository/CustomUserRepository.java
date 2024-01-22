package org.solodev.seedshop.repository;

import org.solodev.seedshop.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
}
