package com.demo.service.product;

import com.demo.dto.createedit.product.BrandCreateEditDto;
import com.demo.dto.read.product.BrandReadDto;
import com.demo.mapper.createedit.product.BrandCreateEditMapper;
import com.demo.mapper.read.product.BrandReadMapper;
import com.demo.repository.product.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepo;
    private final BrandReadMapper brandReadMapper;
    private final BrandCreateEditMapper brandCreateEditMapper;


    public List<BrandReadDto> findAll() {

        return brandRepo.findAll()
                .stream()
                .map(brandReadMapper::map)
                .toList();
    }


    public Optional<BrandReadDto> findById(Long id) {

        return brandRepo.findById(id)
                .map(brandReadMapper::map);
    }

    public BrandReadDto create(BrandCreateEditDto brandDto) {

        return Optional.of(brandDto)
                .map(dto -> {
                    return brandCreateEditMapper.map(dto);
                })
                .map(brandRepo::save)
                .map(brandReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<BrandReadDto> update(Long id, BrandCreateEditDto brandDto) {
        return brandRepo.findById(id)
                .map(entity -> {
                    return brandCreateEditMapper.map(brandDto, entity);
                })
                .map(brandRepo::saveAndFlush)
                .map(brandReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return brandRepo.findById(id)
                .map(entity -> {
                    brandRepo.delete(entity);
                    brandRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
