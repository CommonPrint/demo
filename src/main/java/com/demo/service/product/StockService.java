package com.demo.service.product;

import com.demo.dto.read.product.StockReadDto;
import com.demo.dto.createedit.product.StockCreateEditDto;
import com.demo.mapper.createedit.product.StockCreateEditMapper;
import com.demo.mapper.read.product.StockReadMapper;
import com.demo.repository.product.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockReadMapper stockReadMapper;
    private final StockCreateEditMapper stockCreateEditMapper;

    // получить все Наличия продуктов
    public List<StockReadDto> findAll() {
        return stockRepository.findAll()
                .stream()
                .map(stockReadMapper::map)
                .toList();
    }


    //  Получить Наличие через id продукта
    public Optional<StockReadDto> findByProductId(Long productId) {
        Optional<StockReadDto> stock = stockRepository.findByProductId(productId)
                                                        .map(stockReadMapper::map);

        return stock;
    }


    //    Создать наличие
    public StockReadDto create(StockCreateEditDto stockDto) {
        return Optional.of(stockDto)
                .map(dto -> {
                    dto.setLastUpdateTime(LocalDate.now());

                    return stockCreateEditMapper.map(dto);
                })
                .map(stockRepository::save)
                .map(stockReadMapper::map)
                .orElseThrow();
    }


    // Обновить Наличие продукта
    public Optional<StockReadDto> update(Long id, StockCreateEditDto stockDto) {

        return stockRepository.findById(id)
                .map(entity -> {
                    stockDto.setLastUpdateTime(LocalDate.now());

                    return stockCreateEditMapper.map(stockDto, entity);
                })
                .map(stockRepository::saveAndFlush)
                .map(stockReadMapper::map);

    }

    // Удалить Наличие продукта
    public boolean delete(Long id) {

        return stockRepository.findById(id)
                .map(entity -> {
                    stockRepository.delete(entity);
                    stockRepository.flush();

                    return true;
                })
                .orElse(false);

    }

}
