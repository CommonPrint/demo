package com.demo.service.product;

import com.demo.dto.createedit.product.ShareCreateEditDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.mapper.createedit.product.ShareCreateEditMapper;
import com.demo.mapper.read.product.ShareReadMapper;
import com.demo.repository.product.ShareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShareService {

    private final ShareRepository shareRepo;
    private final ShareReadMapper shareReadMapper;
    private final ShareCreateEditMapper shareCreateEditMapper;


    public List<ShareReadDto> findAll() {

        return shareRepo.findAll()
                .stream()
                .map(shareReadMapper::map)
                .toList();
    }


    public Optional<ShareReadDto> findById(Long id) {

        return shareRepo.findById(id)
                .map(shareReadMapper::map);
    }

    public ShareReadDto create(ShareCreateEditDto shareDto) {

        return Optional.of(shareDto)
                .map(dto -> {
                    return shareCreateEditMapper.map(dto);
                })
                .map(shareRepo::save)
                .map(shareReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<ShareReadDto> update(Long id, ShareCreateEditDto shareDto) {
        return shareRepo.findById(id)
                .map(entity -> {
                    return shareCreateEditMapper.map(shareDto, entity);
                })
                .map(shareRepo::saveAndFlush)
                .map(shareReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return shareRepo.findById(id)
                .map(entity -> {
                    shareRepo.delete(entity);
                    shareRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
