package oniondemo.application;

import jakarta.inject.Inject;
import oniondemo.domain.service.PlaceOrderService;

public class VeryFirstOrderUseCase {

    private final PlaceOrderService placeOrderService;

    @Inject
    public VeryFirstOrderUseCase(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
    }

    public void placeFirstOrder() {
        placeOrderService.placeOrder();
    }
}
