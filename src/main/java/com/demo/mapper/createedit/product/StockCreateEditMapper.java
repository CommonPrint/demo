package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.StockCreateEditDto;
import com.demo.entity.product.Product;
import com.demo.entity.product.Stock;
import com.demo.mapper.Mapper;
import com.demo.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockCreateEditMapper implements Mapper<StockCreateEditDto, Stock> {

    private final ProductRepository productRepository;

    @Override
    public Stock map(StockCreateEditDto object) {
        Stock product = new Stock();

        copy(object, product);

        return product;
    }

    @Override
    public Stock map(StockCreateEditDto fromObject, Stock toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(StockCreateEditDto object, Stock stock) {
        stock.setQuantity(object.getQuantity());
        stock.setLastUpdateTime(object.getLastUpdateTime());
        stock.setProduct(getProduct(object.getProductId()));
    }

    // Получим страну города
    public Product getProduct(Long productId) {
        return Optional.ofNullable(productId)
                .flatMap(productRepository::findById)
                .orElse(null);
    }
}
