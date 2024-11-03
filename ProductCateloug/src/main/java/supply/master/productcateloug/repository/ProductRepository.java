package supply.master.productcateloug.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import supply.master.productcateloug.model.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryId(Long categoryId);
}
