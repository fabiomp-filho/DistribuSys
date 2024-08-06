package fabio.distribusys.br.userms.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Long user;
}
