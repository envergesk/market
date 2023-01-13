package ru.kildeev.market.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.market.api.ProductDto;
import ru.kildeev.market.api.ResourceNotFoundException;
import ru.kildeev.market.core.entities.Category;
import ru.kildeev.market.core.entities.Product;
import ru.kildeev.market.core.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
