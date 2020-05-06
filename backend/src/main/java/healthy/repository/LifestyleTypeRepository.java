package healthy.repository;

import healthy.entity.LifestyleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifestyleTypeRepository extends JpaRepository<LifestyleType, Integer> {
    List<LifestyleType> findAll();
    void deleteAll();
}
