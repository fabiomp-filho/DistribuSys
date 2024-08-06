package fabio.distribusys.br.taskms.repositories;

import fabio.distribusys.br.taskms.domain.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findTasksByUser(Long id, Pageable pageable);

    List<Task> findTasksByUser(Long id);
}
