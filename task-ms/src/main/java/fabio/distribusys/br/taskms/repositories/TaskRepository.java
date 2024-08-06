package fabio.distribusys.br.taskms.repositories;

import fabio.distribusys.br.taskms.domain.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findTasksByUser(long id, Pageable pageable);
}
