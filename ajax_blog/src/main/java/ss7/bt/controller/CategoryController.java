package ss7.bt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ss7.bt.model.Category;
import ss7.bt.model.Post;
import ss7.bt.service.ICategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView getView() {
        return new ModelAndView("categoryList","categories",categoryService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody  Category category){
        Optional<Category> updateCat = categoryService.findById(id);
        if (!updateCat.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updateCat.get().setName(category.getName());
        return new ResponseEntity<>(categoryService.save(updateCat.get()), HttpStatus.OK);
    }
}
