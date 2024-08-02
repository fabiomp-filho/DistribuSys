package fabio.distribusys.br.userms.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "Street can't be blank")
    private String street;
    @NotBlank(message = "Number can't be blank")
    private String number;
    @NotBlank(message = "District can't be blank")
    private String district;
    @NotBlank(message = "City can't be blank")
    private String city;
    @NotBlank(message = "State can't be blank")
    private String state;
    @NotBlank(message = "Zip Code can't be blank")
    private String zipCode;
    private String complement;

}
