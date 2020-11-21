package live.syedriadh.Demo.REST.Service.service;

import live.syedriadh.Demo.REST.Service.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    Object getProductByName(String name);
    List<Product> getProductsByType(String type);
    Product addNewProduct(Product product);
    Optional<Product> updateProduct(int id, Product updatedProduct);
}
