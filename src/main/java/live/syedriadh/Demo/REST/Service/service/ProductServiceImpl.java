package live.syedriadh.Demo.REST.Service.service;

import live.syedriadh.Demo.REST.Service.entity.Product;
import live.syedriadh.Demo.REST.Service.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductJpaRepository productJpaRepository;

    @Autowired
    public ProductServiceImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Product::getId)).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public Object getProductByName(String name) {
        List<Product> productList = productJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Product::getProductName)).collect(Collectors.toList());

        List<Product> matchedProducts = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductName().equals(name)) {
                matchedProducts.add(product);
            }
        }

        if (matchedProducts.size() == 1)
            return matchedProducts.get(0);
        else if (matchedProducts.size() == 0)
            return null;
        else
            return matchedProducts;
    }

    @Override
    public List<Product> getProductsByType(String type) {
        List<Product> productList = productJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Product::getProductType)).collect(Collectors.toList());

        List<Product> matchedProducts = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductType().equals(type)) {
                matchedProducts.add(product);
            }
        }

        return matchedProducts;
    }

    @Override
    public Product addNewProduct(Product product) {
        if (product.getStock() > 0)
            return productJpaRepository.saveAndFlush(product);

        return new Product();
    }

    @Override
    public Optional<Product> updateProduct(int id, Product updatedProduct) {
        Product matchedProduct = new Product();

        if (productJpaRepository.existsById(id)) {
            matchedProduct = productJpaRepository.getOne(id);

            if (updatedProduct.getStock() != null) {
                matchedProduct.setStock(updatedProduct.getStock());
            }

            if (updatedProduct.getPricePerUnit() != null) {
                matchedProduct.setPricePerUnit(updatedProduct.getPricePerUnit());
            }

            productJpaRepository.saveAndFlush(matchedProduct);
        }

        return Optional.of(matchedProduct);
    }
}
