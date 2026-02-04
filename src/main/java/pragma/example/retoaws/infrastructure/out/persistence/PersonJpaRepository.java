package pragma.example.retoaws.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, String> {

}