package com.ra.session03.model.dto.customer.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import lombok.*;


import java.time.LocalDate;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponeseDTO {
    private long customerId;
    private String email;
    private String  fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthDate;
    private Boolean status;
}
