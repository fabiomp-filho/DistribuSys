package fabio.distribusys.br.userms.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Name can't be blank")
    private String name;
    @Email(message = "Invalid E-mail")
    @NotBlank(message = "E-mail can't be blank")
    private String email;
    @NotNull(message = "Age can't be blank")
    @Min(value = 18, message = "Only allowed 18+")
    @Max(value = 199, message = "Not a valid age")
    private Integer age;
    @Valid
    private AddressDTO address;
}
