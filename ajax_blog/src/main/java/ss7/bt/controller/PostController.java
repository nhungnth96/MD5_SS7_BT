package ss7.bt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ss7.bt.model.Category;
import ss7.bt.model.Post;
import ss7.bt.service.ICategoryService;
import ss7.bt.service.IPostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    IPostService postService;
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView getView(Model model) {

        model.addAttribute("categories",categoryService.findAll());
        return new ModelAndView("postList","posts",postService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("page/{size}")
    public ResponseEntity<List<Post>> getAllPage(@PathVariable int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("creationDate").descending());
        return new ResponseEntity<>(postService.findAll(pageable).getContent(), HttpStatus.OK);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Post>> search(@PathVariable String keyword) {
        return new ResponseEntity<>(postService.findByTitleContainingIgnoreCase(keyword), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Post> create(@RequestBody Post category) {
        return new ResponseEntity<>(postService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deleteById(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody  Post post){
        Optional<Post> updatePost = postService.findById(id);
        if (!updatePost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatePost.get().setTitle(post.getTitle());
        updatePost.get().setContent(post.getContent());
        updatePost.get().setCategory(post.getCategory());
        updatePost.get().setCreationDate(post.getCreationDate());
        return new ResponseEntity<>(postService.save(updatePost.get()), HttpStatus.OK);
    }
}
