package com.demo.service.product;

import com.demo.dto.createedit.product.ProductTypeCreateEditDto;
import com.demo.dto.read.product.ProductTypeReadDto;
import com.demo.mapper.createedit.product.ProductTypeCreateEditMapper;
import com.demo.mapper.read.product.ProductTypeReadMapper;
import com.demo.repository.product.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepo;
    private final ProductTypeReadMapper productTypeReadMapper;
    private final ProductTypeCreateEditMapper productTypeCreateEditMapper;


    public List<ProductTypeReadDto> findAll() {

        return productTypeRepo.findAll()
                .stream()
                .map(productTypeReadMapper::map)
                .toList();
    }


    public Optional<ProductTypeReadDto> findById(Long id) {

        return productTypeRepo.findById(id)
                .map(productTypeReadMapper::map);
    }

    public ProductTypeReadDto create(ProductTypeCreateEditDto productTypeDto) {

        return Optional.of(productTypeDto)
                .map(dto -> {
                    return productTypeCreateEditMapper.map(dto);
                })
                .map(productTypeRepo::save)
                .map(productTypeReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<ProductTypeReadDto> update(Long id, ProductTypeCreateEditDto productTypeDto) {
        return productTypeRepo.findById(id)
                .map(entity -> {
                    return productTypeCreateEditMapper.map(productTypeDto, entity);
                })
                .map(productTypeRepo::saveAndFlush)
                .map(productTypeReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return productTypeRepo.findById(id)
                .map(entity -> {
                    productTypeRepo.delete(entity);
                    productTypeRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
