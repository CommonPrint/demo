package com.demo.service.payment;


import com.demo.dto.createedit.payment.PaymentDataCreateEditDto;
import com.demo.dto.read.payment.PaymentDataReadDto;
import com.demo.mapper.createedit.payment.PaymentDataCreateEditMapper;
import com.demo.mapper.read.payment.PaymentDataReadMapper;
import com.demo.repository.payment.PaymentDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentDataService {

    private final PaymentDataRepository paymentDataRepository;
    private final PaymentDataCreateEditMapper paymentDataCreateEditMapper;
    private final PaymentDataReadMapper paymentDataReadMapper;
    


    //	Получить все Платежи
    public List<PaymentDataReadDto> findAll() {
        return this.paymentDataRepository.findAll()
                .stream()
                .map(paymentDataReadMapper::map)
                .toList();
    }//findAll()


    //  Получим платеж по id
    public Optional<PaymentDataReadDto> findById(Long id) {

        return paymentDataRepository.findById(id)
                .map(paymentDataReadMapper::map);
    }


    public PaymentDataReadDto create(PaymentDataCreateEditDto paymentDataDto) {

        return Optional.of(paymentDataDto)
                .map(dto -> {
                    return paymentDataCreateEditMapper.map(dto);
                })
                .map(paymentDataRepository::save)
                .map(paymentDataReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)


    // Обновление платежа
    public Optional<PaymentDataReadDto> update(Long id, PaymentDataCreateEditDto paymentDataDto) {
        return paymentDataRepository.findById(id)
                .map(entity -> {
                    return paymentDataCreateEditMapper.map(paymentDataDto, entity);
                })
                .map(paymentDataRepository::saveAndFlush)
                .map(paymentDataReadMapper::map);
    }


    // Удаление Платежа
    public boolean delete(Long id) {

        return paymentDataRepository.findById(id)
                .map(entity -> {
                    paymentDataRepository.delete(entity);
                    paymentDataRepository.flush();

                    return true;
                })
                .orElse(false);
    }

}
