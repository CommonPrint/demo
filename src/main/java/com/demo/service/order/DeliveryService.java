package com.demo.service.order;

import com.demo.dto.createedit.order.DeliveryCreateEditDto;
import com.demo.dto.read.order.DeliveryReadDto;
import com.demo.mapper.createedit.order.DeliveryCreateEditMapper;
import com.demo.mapper.read.order.DeliveryReadMapper;
import com.demo.repository.order.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryCreateEditMapper deliveryCreateEditMapper;
    private final DeliveryReadMapper deliveryReadMapper;


    //	Получить все Доставки
    public List<DeliveryReadDto> findAll() {
        return this.deliveryRepository.findAll()
                .stream()
                .map(deliveryReadMapper::map)
                .toList();
    }//findAll()


    //  Получим доставку по id
    public Optional<DeliveryReadDto> findById(Long id) {

        return deliveryRepository.findById(id)
                .map(deliveryReadMapper::map);
    }


    public DeliveryReadDto create(DeliveryCreateEditDto deliveryDto) {

        return Optional.of(deliveryDto)
                .map(dto -> {

                    // если дата пустая
                    if(dto.getDeliveryDate() == null) {
                        // Прибавим к текущей дате плюс 2 дня
                        dto.setDeliveryDate(LocalDate.now().plusDays(2));
                    }

                    return deliveryCreateEditMapper.map(dto);
                })
                .map(deliveryRepository::save)
                .map(deliveryReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)


    // Обновление Доставки
    public Optional<DeliveryReadDto> update(Long id, DeliveryCreateEditDto deliveryDto) {
        return deliveryRepository.findById(id)
                .map(entity -> {

                    // Если тип доставки = "доставка", то обновим дату на 1 день позже
                    if(deliveryDto.getDeliveryType().equalsIgnoreCase("доставка")) {
                        deliveryDto.setDeliveryDate(LocalDate.now().plusDays(1));
                    }

                    return deliveryCreateEditMapper.map(deliveryDto, entity);
                })
                .map(deliveryRepository::saveAndFlush)
                .map(deliveryReadMapper::map);
    }


    // Удаление Доставки
    public boolean delete(Long id) {

        return deliveryRepository.findById(id)
                .map(entity -> {
                    deliveryRepository.delete(entity);
                    deliveryRepository.flush();

                    return true;
                })
                .orElse(false);
    }

}
