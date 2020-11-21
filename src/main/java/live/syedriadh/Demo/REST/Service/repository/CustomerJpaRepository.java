package live.syedriadh.Demo.REST.Service.repository;

import live.syedriadh.Demo.REST.Service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {
}
