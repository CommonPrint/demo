package com.demo.service.product;

import com.demo.dto.createedit.product.ProductFileCreateEditDto;
import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.entity.product.Product;
import com.demo.mapper.createedit.product.ProductFileCreateEditMapper;
import com.demo.mapper.read.product.ProductFileReadMapper;
import com.demo.repository.product.ProductFileRepository;
import com.demo.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductFileService {

    private final ProductFileRepository productFileRepository;
    private final ProductFileCreateEditMapper productFileCreateEditMapper;
    private final ProductFileReadMapper productFileReadMapper;


    //	Получить все Изображения продукта
    public List<ProductFileReadDto> findAll() {
        return this.productFileRepository.findAll()
                .stream()
                .map(productFileReadMapper::map)
                .toList();
    }//findAll()



    //  Получим изображение по id
    public Optional<ProductFileReadDto> findById(Long id) {

        return productFileRepository.findById(id)
                .map(productFileReadMapper::map);
    }


    public ProductFileReadDto create(ProductFileCreateEditDto productFileDto) {

        return Optional.of(productFileDto)
                .map(dto -> {
                    return productFileCreateEditMapper.map(dto);
                })
                .map(productFileRepository::save)
                .map(productFileReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)


    // Обновление Изображения
    public Optional<ProductFileReadDto> update(Long id, ProductFileCreateEditDto productFileDto) {
        return productFileRepository.findById(id)
                .map(entity -> {
                    return productFileCreateEditMapper.map(productFileDto, entity);
                })
                .map(productFileRepository::saveAndFlush)
                .map(productFileReadMapper::map);
    }


    // Удаление Изображения
    public boolean delete(Long id) {

        return productFileRepository.findById(id)
                .map(entity -> {
                    productFileRepository.delete(entity);
                    productFileRepository.flush();

                    return true;
                })
                .orElse(false);
    }

}
