package fabio.distribusys.br.userms.domain.dto;

import fabio.distribusys.br.userms.domain.dto.client.TaskResponseDTO;
import fabio.distribusys.br.userms.pagination.CustomPageImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTaskDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private AddressDTO address;
    private CustomPageImpl<TaskResponseDTO> tasks;
}
