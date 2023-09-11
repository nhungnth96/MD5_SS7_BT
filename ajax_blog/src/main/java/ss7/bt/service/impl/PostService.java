package ss7.bt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ss7.bt.model.Category;
import ss7.bt.model.Post;
import ss7.bt.repository.IPostRepository;
import ss7.bt.service.IPostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> findAllByCategory(Category category) {
        return postRepository.findAllByCategory(category);
    }

    @Override
    public List<Post> findByTitleContainingIgnoreCase(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}
