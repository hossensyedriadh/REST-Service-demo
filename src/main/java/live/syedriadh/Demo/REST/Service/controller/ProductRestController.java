package live.syedriadh.Demo.REST.Service.controller;

import live.syedriadh.Demo.REST.Service.entity.Product;
import live.syedriadh.Demo.REST.Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/id")
    @ResponseBody
    public Optional<Product> getProductById(@RequestParam("id") int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/name")
    @ResponseBody
    public Object getProductByName(@RequestParam("name") String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/type")
    @ResponseBody
    public List<Product> getProductsByType(@RequestParam("type") String type) {
        return productService.getProductsByType(type);
    }

    @PostMapping("/new")
    @ResponseBody
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/update")
    @ResponseBody
    public Optional<Product> updateProduct(@RequestParam("id") int id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }
}
