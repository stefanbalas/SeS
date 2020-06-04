package healthy.repository;

import healthy.entity.Lifestyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifestyleRepository extends JpaRepository<Lifestyle, Integer> {
    List<Lifestyle> findAll();
}
