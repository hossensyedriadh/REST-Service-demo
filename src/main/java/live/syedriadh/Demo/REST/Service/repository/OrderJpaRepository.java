package live.syedriadh.Demo.REST.Service.repository;

import live.syedriadh.Demo.REST.Service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
}
