package healthy.repository;

import healthy.entity.Analize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalizeRepository extends JpaRepository<Analize, Integer> {
    List<Analize> findAll();
}
