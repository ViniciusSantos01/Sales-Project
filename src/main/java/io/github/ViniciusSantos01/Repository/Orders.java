package io.github.ViniciusSantos01.Repository;

import io.github.ViniciusSantos01.entity.Client;
import io.github.ViniciusSantos01.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Orders extends JpaRepository<ClientOrder, Integer> {

    List<ClientOrder> findByClient(Client client);

}
