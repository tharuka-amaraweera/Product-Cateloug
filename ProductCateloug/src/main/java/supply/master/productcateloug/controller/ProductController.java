package supply.master.productcateloug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supply.master.productcateloug.dto.ErrorResponse;
import supply.master.productcateloug.dto.PageResponse;
import supply.master.productcateloug.model.Product;
import supply.master.productcateloug.service.ProductService;
import supply.master.productcateloug.util.Constants;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> productById = productService.getProductById(id);
        if (productById.isPresent()) {
            return ResponseEntity.ok(productById.get());
        } else {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    Constants.ErrorMessages.PRODUCTNOTFOUND,
                    Constants.ErrorCodes.PRODUCTNOTFOUND),
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") String id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

}
