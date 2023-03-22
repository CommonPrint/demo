package com.demo.service.payment;

import com.demo.dto.createedit.payment.PaymentTypeCreateEditDto;
import com.demo.dto.read.payment.PaymentTypeReadDto;
import com.demo.mapper.createedit.payment.PaymentTypeCreateEditMapper;
import com.demo.mapper.read.payment.PaymentTypeReadMapper;
import com.demo.repository.payment.PaymentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepo;
    private final PaymentTypeReadMapper paymentTypeReadMapper;
    private final PaymentTypeCreateEditMapper paymentTypeCreateEditMapper;


    public List<PaymentTypeReadDto> findAll() {

        return paymentTypeRepo.findAll()
                .stream()
                .map(paymentTypeReadMapper::map)
                .toList();
    }


    public Optional<PaymentTypeReadDto> findById(Long id) {

        return paymentTypeRepo.findById(id)
                .map(paymentTypeReadMapper::map);
    }

    public PaymentTypeReadDto create(PaymentTypeCreateEditDto paymentTypeDto) {

        return Optional.of(paymentTypeDto)
                .map(dto -> {
                    return paymentTypeCreateEditMapper.map(dto);
                })
                .map(paymentTypeRepo::save)
                .map(paymentTypeReadMapper::map)
                .orElseThrow();
    }


    // Обновление страны
    public Optional<PaymentTypeReadDto> update(Long id, PaymentTypeCreateEditDto paymentTypeDto) {
        return paymentTypeRepo.findById(id)
                .map(entity -> {
                    return paymentTypeCreateEditMapper.map(paymentTypeDto, entity);
                })
                .map(paymentTypeRepo::saveAndFlush)
                .map(paymentTypeReadMapper::map);
    }


    // Удаление страны
    public boolean delete(Long id) {
        return paymentTypeRepo.findById(id)
                .map(entity -> {
                    paymentTypeRepo.delete(entity);
                    paymentTypeRepo.flush();

                    return true;
                })
                .orElse(false);
    }

}
