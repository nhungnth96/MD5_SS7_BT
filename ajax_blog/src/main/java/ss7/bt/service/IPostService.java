package ss7.bt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ss7.bt.model.Category;
import ss7.bt.model.Post;

import java.util.List;

public interface IPostService extends IGenericService<Post,Long> {
    Page<Post> findAll(Pageable pageable);
    List<Post> findAllByCategory(Category category);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
