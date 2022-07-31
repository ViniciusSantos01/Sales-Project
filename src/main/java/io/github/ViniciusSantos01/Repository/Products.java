package io.github.ViniciusSantos01.Repository;

import io.github.ViniciusSantos01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Products extends JpaRepository<Product, Integer> {
}
