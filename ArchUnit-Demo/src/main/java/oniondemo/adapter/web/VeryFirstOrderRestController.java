package oniondemo.adapter.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import oniondemo.application.VeryFirstOrderUseCase;

@Path("/api/firstOrder")
public class VeryFirstOrderRestController {

    private final VeryFirstOrderUseCase veryFirstOrderUseCase;

    @Inject
    public VeryFirstOrderRestController(VeryFirstOrderUseCase veryFirstOrderUseCase) {
        this.veryFirstOrderUseCase = veryFirstOrderUseCase;
    }

    @PUT
    public void placeFirstOrder() {
        veryFirstOrderUseCase.placeFirstOrder();
    }
}
