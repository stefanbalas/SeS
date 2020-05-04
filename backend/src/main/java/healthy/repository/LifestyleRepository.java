package healthy.repository;

import healthy.entity.LifestyleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LifestyleRepository extends JpaRepository<LifestyleType, Integer> {
    List<LifestyleType> findAll();
    Optional<LifestyleType> findByLifestyle(String lifestyle);
    void deleteAll();
}
