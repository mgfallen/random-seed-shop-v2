package org.solodev.seedshop.repository;

import jakarta.transaction.Transactional;
import org.solodev.seedshop.model.Cart;
import org.solodev.seedshop.model.CustomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    CustomOrder createOrderFromCart(Optional<Cart> cart);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.checkedOut = true WHERE c.id = :cartId")
    int markCartAsCheckedOut(@Param("cartId") Long cartId);

}
