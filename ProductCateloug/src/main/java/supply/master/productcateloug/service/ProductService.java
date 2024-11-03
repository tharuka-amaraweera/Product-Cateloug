package supply.master.productcateloug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import supply.master.productcateloug.dto.PageResponse;
import supply.master.productcateloug.model.Product;
import supply.master.productcateloug.repository.ProductRepository;
import supply.master.productcateloug.util.PageResponseMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        if(product.getId()== null || product.getId().isEmpty()){
            product.setId(UUID.randomUUID().toString());
        }
        return productRepository.save(product);
    }

    public PageResponse<Product> getAllProducts(Pageable pageable) {
        return PageResponseMapper.toPageResponse(productRepository.findAll(pageable));
    }
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    public boolean deleteProduct(String id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
