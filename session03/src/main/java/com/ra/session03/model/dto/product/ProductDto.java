package com.ra.session03.model.dto.product;

import com.ra.session03.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private double price;
    private int quantity;
    private MultipartFile fileImage;
    private Category category;
}
