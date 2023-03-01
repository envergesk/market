package ru.kildeev.market.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kildeev.market.api.ProductDto;
import ru.kildeev.market.api.ResourceNotFoundException;
import ru.kildeev.market.core.converters.ProductConverter;
import ru.kildeev.market.core.entities.Category;
import ru.kildeev.market.core.entities.Product;
import ru.kildeev.market.core.repositories.ProductRepository;
import ru.kildeev.market.core.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    public Page<Product> getAll(Specification<Product> spec, int page){
        return productRepository.findAll(spec, PageRequest.of(page, 5));
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

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }
}
