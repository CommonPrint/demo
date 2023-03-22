package com.demo.service.order;

import com.demo.dto.read.order.InsureReadDto;
import com.demo.dto.createedit.order.InsureCreateEditDto;
import com.demo.mapper.createedit.order.InsureCreateEditMapper;
import com.demo.mapper.read.order.InsureReadMapper;
import com.demo.repository.order.InsureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InsureService {

    private final InsureRepository insureRepository;
    private final InsureCreateEditMapper insureCreateEditMapper;
    private final InsureReadMapper insureReadMapper;


    //	Получить все Страховки
    public List<InsureReadDto> findAll() {
        return this.insureRepository.findAll()
                .stream()
                .map(insureReadMapper::map)
                .toList();
    }//findAll()


    //  Получим страховку по id
    public Optional<InsureReadDto> findById(Long id) {

        return insureRepository.findById(id)
                .map(insureReadMapper::map);
    }


    public InsureReadDto create(InsureCreateEditDto insureDto) {

        return Optional.of(insureDto)
                .map(dto -> {
                    return insureCreateEditMapper.map(dto);
                })
                .map(insureRepository::save)
                .map(insureReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)


    // Обновление Страховки
    public Optional<InsureReadDto> update(Long id, InsureCreateEditDto insureDto) {
        return insureRepository.findById(id)
                .map(entity -> {
                    return insureCreateEditMapper.map(insureDto, entity);
                })
                .map(insureRepository::saveAndFlush)
                .map(insureReadMapper::map);
    }


    // Удаление Продукта
    public boolean delete(Long id) {

        return insureRepository.findById(id)
                .map(entity -> {
                    insureRepository.delete(entity);
                    insureRepository.flush();

                    return true;
                })
                .orElse(false);
    }

}
