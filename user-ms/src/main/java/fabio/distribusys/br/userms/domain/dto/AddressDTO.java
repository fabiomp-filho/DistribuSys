package fabio.distribusys.br.userms.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "Street can't be blank")
    @Size(max = 100, message = "Street should not be greater than 100 characters")
    private String street;
    @NotBlank(message = "Number can't be blank")
    @Size(max = 100, message = "Number should not be greater than 100 characters")
    private String number;
    @NotBlank(message = "District can't be blank")
    @Size(max = 100, message = "District should not be greater than 100 characters")
    private String district;
    @NotBlank(message = "City can't be blank")
    @Size(max = 100, message = "City should not be greater than 100 characters")
    private String city;
    @NotBlank(message = "State can't be blank")
    @Size(max = 100, message = "State should not be greater than 100 characters")
    private String state;
    @NotBlank(message = "Zip Code can't be blank")
    @Size(max = 9, message = "Zip Code should not be greater than 9 characters")
    private String zipCode;
    @Size(max = 255, message = "Complement should not be greater than 255 characters")
    private String complement;

}
