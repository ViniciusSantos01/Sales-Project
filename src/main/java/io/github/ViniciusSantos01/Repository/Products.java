package io.github.ViniciusSantos01.Repository;

import io.github.ViniciusSantos01.entity.Client;
import io.github.ViniciusSantos01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Products extends JpaRepository<Product, Integer> {



}
