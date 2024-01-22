package org.solodev.seedshop.repository;

import org.solodev.seedshop.model.CustomOrder;
import org.solodev.seedshop.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOrderRepository extends JpaRepository<CustomOrder, Long> {
    List<CustomOrder> findByUser(CustomUser user);
}
