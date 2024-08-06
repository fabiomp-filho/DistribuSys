package fabio.distribusys.br.taskms.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskResponseDTO {
    private String title;
    private String description;
    private Long userId;
}
