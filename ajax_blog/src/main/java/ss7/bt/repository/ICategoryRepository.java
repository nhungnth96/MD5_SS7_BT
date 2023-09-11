package ss7.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss7.bt.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
