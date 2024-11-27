package supply.master.productcateloug.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import supply.master.productcateloug.dto.ErrorResponse;
import supply.master.productcateloug.dto.PageResponse;
import supply.master.productcateloug.exception.SPMException;
import supply.master.productcateloug.model.Product;
import supply.master.productcateloug.repository.ProductRepository;
import supply.master.productcateloug.util.ErrorConstants;
import supply.master.productcateloug.util.PageResponseMapper;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Product createProduct(Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public PageResponse<Product> getAllProducts(Pageable pageable) {
        return PageResponseMapper.toPageResponse(productRepository.findAll(pageable));
    }

    public Product getProductById(String id) {
        //findById returns entire entity if exists
        return productRepository.findById(id)
                .orElseThrow(() -> new SPMException(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ErrorConstants.ErrorMessages.PRODUCTNOTFOUND,
                        ErrorConstants.ErrorCodes.PRODUCTNOTFOUND)));
    }

    public void deleteProduct(String id) {
        //existsById returns a boolean value indicating the existence
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return;
        }
        throw new SPMException(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ErrorConstants.ErrorMessages.PRODUCTNOTFOUND,
                ErrorConstants.ErrorCodes.PRODUCTNOTFOUND));
    }

    public List<Product> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
