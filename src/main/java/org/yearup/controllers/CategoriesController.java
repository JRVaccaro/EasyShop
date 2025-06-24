package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;


@RestController
@RequestMapping("categories")
@CrossOrigin //Allows requests from different origins
public class CategoriesController {
    private CategoryDao categoryDao;
    private ProductDao productDao;

    //Injected constructor for DAOs
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        // find and return all categories
        return categoryDao.getAllCategories();
    }

    // @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id) {
        try {
            Category category = categoryDao.getById(id);
            if (category == null) {
                //If category is not found, respond with 404 Not Found status with message
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
            }
            // get the category by id
            return category;
        } catch (Exception e) {
            //Handle any unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @GetMapping("{categoryId}/products")
    @PreAuthorize("permitAll()")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        try {
            // get a list of product by categoryId
            return productDao.listByCategoryId(categoryId);
        } catch (Exception e) {

            //Handle any unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        try {

            return categoryDao.create(category);
        } catch (Exception e) {

        //Handle any unexpected errors
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad");
    }
}
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {

            categoryDao.update(id, category);
    } catch (Exception e) {
            //Handle any unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad");
        }

        }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        try{
            categoryDao.delete(id);
    } catch (Exception e) {
            //Handle any unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops.... our bad");
        }
        }
}
