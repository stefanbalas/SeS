package healthy.repository;

import healthy.entity.Lifestyle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifestyleRepository extends JpaRepository<Lifestyle, Integer> {
}
