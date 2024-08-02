package fabio.distribusys.br.userms.repositories;


import fabio.distribusys.br.userms.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String Email);

}
