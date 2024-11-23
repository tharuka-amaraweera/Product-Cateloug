package supply.master.productcateloug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supply.master.productcateloug.dto.PageResponse;
import supply.master.productcateloug.model.Product;
import supply.master.productcateloug.service.ProductService;
import supply.master.productcateloug.util.Constants;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Product cretaeProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public PageResponse<Product> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(Constants.Messages.PRODUCTDELETED);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    //Todo: add another controller to add list of products at once

}
