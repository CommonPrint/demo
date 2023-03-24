package com.demo.mapper.createedit.order;

import com.demo.dto.createedit.order.OrderCreateEditDto;
import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.product.ProductReviewReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.order.Delivery;
import com.demo.entity.order.Insure;
import com.demo.entity.order.Order;
import com.demo.entity.payment.PaymentData;
import com.demo.entity.product.*;
import com.demo.entity.user.User;
import com.demo.mapper.Mapper;
import com.demo.repository.order.DeliveryRepository;
import com.demo.repository.order.InsureRepository;
import com.demo.repository.payment.PaymentDataRepository;
import com.demo.repository.product.*;
import com.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order> {

    private final InsureRepository insureRepository;
    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;
    private final PaymentDataRepository paymentDataRepository;
    private final ProductRepository productRepository;


    @Override
    public Order map(OrderCreateEditDto object) {
        Order order = new Order();

        copy(object, order);

        return order;
    }

    @Override
    public Order map(OrderCreateEditDto fromObject, Order toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(OrderCreateEditDto object, Order order) {
        order.setOrderCode(object.getOrderCode());
        order.setCost(object.getCost());
        order.setTimeCreated(object.getTimeCreated());
        order.setInsure(getInsure(object.getInsureId()));
        order.setDelivery(getDelivery(object.getDeliveryId()));
        order.setUser(getUser(object.getUserId()));
        order.setPaymentData(getPaymentData(object.getPaymentDataId()));
        order.setProducts(getProducts(object.getProducts()));
    }

    // Получим Гарантию Заказа
    public Insure getInsure(Long insureId) {

        return Optional.ofNullable(insureId)
                .flatMap(insureRepository::findById)
                .orElse(null);
    }

    // Получим Доставку Заказа
    public Delivery getDelivery(Long deliveryId) {

        return Optional.ofNullable(deliveryId)
                .flatMap(deliveryRepository::findById)
                .orElse(null);
    }


    // Получим пользователя Заказа
    public User getUser(Long userId) {

        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }


    // Получим данные о Платеже Заказа
    public PaymentData getPaymentData(Long paymentDataId) {

        return Optional.ofNullable(paymentDataId)
                .flatMap(paymentDataRepository::findById)
                .orElse(null);
    }


    // Получим Продукты заказа
    public Set<Product> getProducts(Set<ProductReadDto> products) {

        Set<Product> productsSet = new HashSet<>();

        products.forEach(productReadDto -> {
            productsSet.add(
                    productRepository.findById(productReadDto.getId()).orElse(null)
            );
        });

        return productsSet;

    }

}
