package oniondemo.application;

import oniondemo.domain.service.PlaceOrderService;

public class VeryFirstOrderUseCase {

    private final PlaceOrderService placeOrderService;

    public VeryFirstOrderUseCase(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
    }
}
