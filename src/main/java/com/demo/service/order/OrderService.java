package com.demo.service.order;

import com.demo.dto.createedit.order.OrderCreateEditDto;
import com.demo.dto.read.order.OrderReadDto;
import com.demo.mapper.createedit.order.OrderCreateEditMapper;
import com.demo.mapper.read.order.OrderReadMapper;
import com.demo.repository.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final OrderReadMapper orderReadMapper;


    //	Получить все Заказы
    public List<OrderReadDto> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(orderReadMapper::map)
                .toList();
    }//findAll()


    //  Получим заказ по id
    public Optional<OrderReadDto> findById(Long id) {

        return orderRepository.findById(id)
                .map(orderReadMapper::map);
    }


    public OrderReadDto create(OrderCreateEditDto orderDto) {

        return Optional.of(orderDto)
                .map(dto -> {
                    dto.setTimeCreated(System.currentTimeMillis());
                    return orderCreateEditMapper.map(dto);
                })
                .map(orderRepository::save)
                .map(orderReadMapper::map)
                .orElseThrow();

    }


    // Обновление Заказа
    public Optional<OrderReadDto> update(Long id, OrderCreateEditDto orderDto) {
        return orderRepository.findById(id)
                .map(entity -> {
                    orderDto.setTimeCreated(entity.getTimeCreated());
                    return orderCreateEditMapper.map(orderDto, entity);
                })
                .map(orderRepository::saveAndFlush)
                .map(orderReadMapper::map);
    }


    // Удаление Заказа
    public boolean delete(Long id) {

        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    orderRepository.flush();

                    return true;
                })
                .orElse(false);
    }

}
