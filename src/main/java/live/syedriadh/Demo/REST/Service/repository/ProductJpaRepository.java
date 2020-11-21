package live.syedriadh.Demo.REST.Service.repository;

import live.syedriadh.Demo.REST.Service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
}
