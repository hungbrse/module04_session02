package com.ra.session03.service.product;




import com.ra.session03.model.dto.product.ProductDto;
import com.ra.session03.model.entity.Product;
import com.ra.session03.repository.ProductRepository;
import com.ra.session03.service.file.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product update(long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            String image = existingProduct.getImg();
            if (productDto.getFileImage() != null && !productDto.getFileImage().isEmpty()) {
                image = uploadService.uploadFile(productDto.getFileImage());
            }

            // Cập nhật các thuộc tính của sản phẩm
            existingProduct.setProductName(productDto.getProductName());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setQuantity(productDto.getQuantity());
            existingProduct.setCategoryId(productDto.getCategory());
            existingProduct.setImg(image);

            // Lưu sản phẩm đã cập nhật
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public Page<Product> findAll(int page, int size,String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("productName").descending());
        if (keyword != null && !keyword.isEmpty()) {
            return productRepository.findByProductNameContaining(keyword, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }



    @Override
    public Product save(ProductDto productDto) {
        String image = null;
        if(productDto.getFileImage() != null && !productDto.getFileImage().isEmpty()) {
            image = uploadService.uploadFile(productDto.getFileImage());
        }
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategoryId(productDto.getCategory());
        product.setImg(image);
        return productRepository.save(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

}
