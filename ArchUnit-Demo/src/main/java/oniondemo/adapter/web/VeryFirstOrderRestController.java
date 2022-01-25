package oniondemo.adapter.web;

import oniondemo.application.VeryFirstOrderUseCase;

public class VeryFirstOrderRestController {

    private final VeryFirstOrderUseCase veryFirstOrderUseCase;

    public VeryFirstOrderRestController(VeryFirstOrderUseCase veryFirstOrderUseCase) {
        this.veryFirstOrderUseCase = veryFirstOrderUseCase;
    }
}
