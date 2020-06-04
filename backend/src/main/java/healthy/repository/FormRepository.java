package healthy.repository;

import healthy.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Integer> {
    List<Form> findAll();
    Optional<Form> findByEmail(String email);
}
