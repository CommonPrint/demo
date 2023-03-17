package com.demo.service.product;

import com.demo.dto.createedit.product.CategoryCreateEditDto;
import com.demo.dto.read.product.CategoryReadDto;
import com.demo.mapper.createedit.product.CategoryCreateEditMapper;
import com.demo.mapper.read.product.CategoryReadMapper;
import com.demo.repository.product.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final CategoryReadMapper categoryReadMapper;
    private final CategoryCreateEditMapper categoryCreateEditMapper;


    public List<CategoryReadDto> findAll() {

        return categoryRepo.findAll()
                .stream()
                .map(categoryReadMapper::map)
                .toList();
    }


    public Optional<CategoryReadDto> findById(Long id) {

        return categoryRepo.findById(id)
                .map(categoryReadMapper::map);
    }

    public CategoryReadDto create(CategoryCreateEditDto categoryDto) {

        return Optional.of(categoryDto)
                .map(dto -> {
                    return categoryCreateEditMapper.map(dto);
                })
                .map(categoryRepo::save)
                .map(categoryReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<CategoryReadDto> update(Long id, CategoryCreateEditDto categoryDto) {
        return categoryRepo.findById(id)
                .map(entity -> {
                    return categoryCreateEditMapper.map(categoryDto, entity);
                })
                .map(categoryRepo::saveAndFlush)
                .map(categoryReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return categoryRepo.findById(id)
                .map(entity -> {
                    categoryRepo.delete(entity);
                    categoryRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
