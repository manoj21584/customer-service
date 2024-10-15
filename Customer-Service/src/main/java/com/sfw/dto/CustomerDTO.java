package com.sfw.dto;

import com.sfw.entities.CustomerLoyalty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long customerId;

    @NotBlank(message = "Customer name cannot be blank")
    @Pattern(regexp = "^[\\S]+$", message = "Customer name must not contain any whitespace")
    private String customerName;

    @NotBlank(message = "Customer email cannot be blank")
    @Pattern(regexp = "^[\\S]+$", message = "Customer email must not contain any whitespace")
    private String customerEmail;

    @NotBlank(message = "Customer phone cannot be blank")
    @Pattern(regexp = "^[\\S]+$", message = "Customer phone must not contain any whitespace")
    private String customerPhone;

    @NotBlank(message = "Customer address cannot be blank")
    private String customerAddress;

    @NotNull(message = "Customer loyalty points cannot be null")
    private Integer customerLoyaltyPoints = 0;

    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;

    private Set<CustomerLoyalty> customerLoyalties;
}
