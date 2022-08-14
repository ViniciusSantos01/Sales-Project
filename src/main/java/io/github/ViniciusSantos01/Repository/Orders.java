package io.github.ViniciusSantos01.Repository;

import io.github.ViniciusSantos01.entity.Client;
import io.github.ViniciusSantos01.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Orders extends JpaRepository<ClientOrder, Integer> {

    List<ClientOrder> findByClient(Client client);

    @Query(" select o from ClientOrder o left join fetch o.items where o.id =:id ")
    Optional<ClientOrder> findByIdFetchItems(Integer id);
}
