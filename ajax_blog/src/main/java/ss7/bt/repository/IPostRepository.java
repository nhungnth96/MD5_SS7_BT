package ss7.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss7.bt.model.Category;
import ss7.bt.model.Post;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByCategory(Category category);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
