package com.demo.service.order;

import com.demo.dto.createedit.order.InsurePropertyCreateEditDto;
import com.demo.dto.read.order.InsurePropertyReadDto;
import com.demo.mapper.createedit.order.InsurePropertyCreateEditMapper;
import com.demo.mapper.read.order.InsurePropertyReadMapper;
import com.demo.repository.order.InsurePropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InsurePropertyService {

    private final InsurePropertyRepository insurePropertyRepo;
    private final InsurePropertyReadMapper insurePropertyReadMapper;
    private final InsurePropertyCreateEditMapper insurePropertyCreateEditMapper;


    public List<InsurePropertyReadDto> findAll() {

        return insurePropertyRepo.findAll()
                .stream()
                .map(insurePropertyReadMapper::map)
                .toList();
    }


    public Optional<InsurePropertyReadDto> findById(Long id) {

        return insurePropertyRepo.findById(id)
                .map(insurePropertyReadMapper::map);
    }

    public InsurePropertyReadDto create(InsurePropertyCreateEditDto insurePropertyDto) {

        return Optional.of(insurePropertyDto)
                .map(dto -> {
                    return insurePropertyCreateEditMapper.map(dto);
                })
                .map(insurePropertyRepo::save)
                .map(insurePropertyReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<InsurePropertyReadDto> update(Long id, InsurePropertyCreateEditDto insurePropertyDto) {
        return insurePropertyRepo.findById(id)
                .map(entity -> {
                    return insurePropertyCreateEditMapper.map(insurePropertyDto, entity);
                })
                .map(insurePropertyRepo::saveAndFlush)
                .map(insurePropertyReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return insurePropertyRepo.findById(id)
                .map(entity -> {
                    insurePropertyRepo.delete(entity);
                    insurePropertyRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
