package ru.kildeev.market.core.converters;

import org.springframework.stereotype.Component;
import ru.kildeev.market.api.ProductDto;
import ru.kildeev.market.core.entities.Product;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        return productDto;
    }
}
