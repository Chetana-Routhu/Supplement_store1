package Supplement_store.Suplement_store.repositories;

import Supplement_store.Suplement_store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
