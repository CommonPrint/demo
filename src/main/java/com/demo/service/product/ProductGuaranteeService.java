package com.demo.service.product;

import com.demo.dto.createedit.product.ProductGuaranteeCreateEditDto;
import com.demo.dto.read.product.ProductGuaranteeReadDto;
import com.demo.mapper.createedit.product.ProductGuaranteeCreateEditMapper;
import com.demo.mapper.read.product.ProductGuaranteeReadMapper;
import com.demo.repository.product.ProductGuaranteeRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductGuaranteeService {

    private final ProductGuaranteeRepository productGuaranteeRepo;
    private final ProductGuaranteeReadMapper productGuaranteeReadMapper;
    private final ProductGuaranteeCreateEditMapper productGuaranteeCreateEditMapper;


    public List<ProductGuaranteeReadDto> findAll() {

        return productGuaranteeRepo.findAll()
                .stream()
                .map(productGuaranteeReadMapper::map)
                .toList();
    }


    public Optional<ProductGuaranteeReadDto> findById(Long id) {

        return productGuaranteeRepo.findById(id)
                .map(productGuaranteeReadMapper::map);
    }

    public ProductGuaranteeReadDto create(ProductGuaranteeCreateEditDto productGuaranteeDto) {

        return Optional.of(productGuaranteeDto)
                .map(dto -> {
                    return productGuaranteeCreateEditMapper.map(dto);
                })
                .map(productGuaranteeRepo::save)
                .map(productGuaranteeReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<ProductGuaranteeReadDto> update(Long id, ProductGuaranteeCreateEditDto productGuaranteeDto) {
        return productGuaranteeRepo.findById(id)
                .map(entity -> {
                    return productGuaranteeCreateEditMapper.map(productGuaranteeDto, entity);
                })
                .map(productGuaranteeRepo::saveAndFlush)
                .map(productGuaranteeReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return productGuaranteeRepo.findById(id)
                .map(entity -> {
                    productGuaranteeRepo.delete(entity);
                    productGuaranteeRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
