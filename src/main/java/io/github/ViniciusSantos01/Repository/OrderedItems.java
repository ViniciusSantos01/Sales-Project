package io.github.ViniciusSantos01.Repository;

import io.github.ViniciusSantos01.entity.ItemClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderedItems extends JpaRepository<ItemClientOrder, Integer> {



}
