package com.ra.session03.model.dto.customer.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[a-z]+\\.[a-z]{2,6}$",message = "sai dinh dang email")
    private String email;
    @NotNull
    private String  fullName;
    @Length(min = 6,message = "mật khẩu ít nhất 6 ký tự ")
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthDate;
    private Boolean status;
}
