package com.demo.service.product;

import com.demo.dto.createedit.product.ProductReviewCreateEditDto;
import com.demo.dto.read.product.ProductReviewReadDto;
import com.demo.mapper.createedit.product.ProductReviewCreateEditMapper;
import com.demo.mapper.read.product.ProductReviewReadMapper;
import com.demo.repository.product.ProductReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepo;
    private final ProductReviewReadMapper productReviewReadMapper;
    private final ProductReviewCreateEditMapper productReviewCreateEditMapper;


    public List<ProductReviewReadDto> findAll() {

        return productReviewRepo.findAll()
                .stream()
                .map(productReviewReadMapper::map)
                .toList();
    }


    // Найдем все отзывы продукта
    public List<ProductReviewReadDto> findAllByProductId(Long productId) {

        return productReviewRepo.findAllByProductId(productId)
                .stream()
                .map(productReviewReadMapper::map)
                .toList();
    }


    // Найдем отзыв по id
    public Optional<ProductReviewReadDto> findById(Long id) {

        return productReviewRepo.findById(id)
                .map(productReviewReadMapper::map);
    }



    public ProductReviewReadDto create(ProductReviewCreateEditDto productReviewDto) {

        return Optional.of(productReviewDto)
                .map(dto -> {
                    dto.setDateReview(LocalDate.now());
                    System.out.println("Review dto: " + dto);
                    return productReviewCreateEditMapper.map(dto);
                })
                .map(productReviewRepo::save)
                .map(productReviewReadMapper::map)
                .orElseThrow();
    }



    // Обновить отзыв пользователя к продукту
    public Optional<ProductReviewReadDto> update(Long id, ProductReviewCreateEditDto productReviewDto) {

        return productReviewRepo.findById(id)
                .map(entity -> {
                    productReviewDto.setDateReview(LocalDate.now());

                    return productReviewCreateEditMapper.map(productReviewDto, entity);
                })
                .map(productReviewRepo::saveAndFlush)
                .map(productReviewReadMapper::map);

    }



    // Удалить отзыв к продукту
    public boolean delete(Long id) {

        return productReviewRepo.findById(id)
                .map(entity -> {
                    productReviewRepo.delete(entity);
                    productReviewRepo.flush();

                    return true;
                })
                .orElse(false);

    }

}
