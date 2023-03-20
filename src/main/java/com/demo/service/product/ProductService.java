package com.demo.service.product;

import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.createedit.product.ShareCreateEditDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.mapper.createedit.product.ProductCreateEditMapper;
import com.demo.mapper.read.product.ProductReadMapper;
import com.demo.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCreateEditMapper productCreateEditMapper;
    private final ProductReadMapper productReadMapper;


    //	Получить все Продукты
    public List<ProductReadDto> findAll() {
        return this.productRepository.findAll()
                .stream()
                .map(productReadMapper::map)
                .toList();
    }//findAll()


    //  Продукты с пагинацией
    public Page<ProductReadDto> findAllPageable(Pageable page) {
        return this.productRepository.findAll(page)
                .map(productReadMapper::map);
    }//findAll()


    //  Получим продукт по id
    public Optional<ProductReadDto> findById(Long id) {

        return productRepository.findById(id)
                .map(productReadMapper::map);
    }


    public ProductReadDto create(ProductCreateEditDto productDto) {

        return Optional.of(productDto)
                .map(dto -> {
                    return productCreateEditMapper.map(dto);
                })
                .map(productRepository::save)
                .map(productReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)


    // Обновление продукта
    public Optional<ProductReadDto> update(Long id, ProductCreateEditDto productDto) {
        return productRepository.findById(id)
                .map(entity -> {
                    return productCreateEditMapper.map(productDto, entity);
                })
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::map);
    }


    // Удаление Продукта
    public boolean delete(Long id) {
        return productRepository.findById(id)
                .map(entity -> {
                    productRepository.delete(entity);
                    productRepository.flush();

                    return true;
                })
                .orElse(false);
    }

}
