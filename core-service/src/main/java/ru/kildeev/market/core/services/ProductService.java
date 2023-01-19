package ru.kildeev.market.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.market.api.ProductDto;
import ru.kildeev.market.api.ResourceNotFoundException;
import ru.kildeev.market.core.converters.ProductConverter;
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
    private final ProductConverter productConverter;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public ProductDto getById(Long id) {
        return productConverter.entityToDto(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id:" + id)));
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
