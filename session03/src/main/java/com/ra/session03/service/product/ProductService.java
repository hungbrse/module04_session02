package com.ra.session03.service.product;



import com.ra.session03.model.dto.product.ProductDto;
import com.ra.session03.model.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(int page, int size, String keyword);
    Product findById(long id);
    Product save(ProductDto productDto);
    void delete(long id);
    Product update(long id, ProductDto productDto);


}
