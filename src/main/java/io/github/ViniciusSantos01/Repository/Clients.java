package io.github.ViniciusSantos01.Repository;

import io.github.ViniciusSantos01.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clients extends JpaRepository<Client, Integer> {

    @Query(value = " select * from client c where c.name like '%:name%'", nativeQuery = true)
    List<Client> findByName(@Param("name") String name);

    @Query(" delete from Client c where c.name =:name ")
    @Modifying
    void deleteByName (String name);

    boolean existsByName (String name);
}
