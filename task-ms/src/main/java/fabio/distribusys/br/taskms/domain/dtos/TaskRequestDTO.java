package fabio.distribusys.br.taskms.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {

    @NotBlank(message = "Title can't be blank")
    @Size(max = 40, message = "Title should not be greater than 40 characters")
    private String title;
    @NotBlank(message = "Description can't be blank")
    @Size(max = 255, message = "Description should not be greater than 255 characters")
    private String description;
    @NotNull(message = "User cant't be null")
    private Long user;
}
