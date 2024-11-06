package com.ra.session03.model.dto.cart.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.session03.model.entity.Customer;
import com.ra.session03.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {
    @NotNull(message = "hãy nhập productId")
    private long productId;
    @NotNull(message = "hãy nhập UserId")
    private long customerId;
    @NotNull(message = "không đuược để số lượng trống")
    @Size(min = 1,message = "số lượng sản phẩm phải lớn hơn 1")
    private long quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past
    private LocalDate addedDate;
    private String status;
}
