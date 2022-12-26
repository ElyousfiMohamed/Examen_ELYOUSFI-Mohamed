package inventory_service.commandeSide.controllers;

import common_api.commands.CreateCustomerCommand;
import common_api.commands.CreateProduitCommand;
import common_api.dtos.CreateCustomerRequestDto;
import common_api.dtos.CreateProduitRequestDto;
import common_api.dtos.UpdateCustomerRequestDto;
import common_api.dtos.UpdateProduitRequestDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/produit/commands")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createProduit(@RequestBody CreateProduitRequestDto requestDto) {
        return commandGateway.send(
                new CreateProduitCommand(
                        UUID.randomUUID().toString(),
                        requestDto.getNom(),
                        requestDto.getPrix(),
                        requestDto.getQuantity(),
                        requestDto.getEtatProduit(),
                        requestDto.getCategorieId()
                ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateProduit(@RequestBody UpdateProduitRequestDto requestDto) {
        return commandGateway.send(
                new CreateProduitCommand(
                        requestDto.getId(),
                        requestDto.getNom(),
                        requestDto.getPrix(),
                        requestDto.getQuantity(),
                        requestDto.getEtatProduit(),
                        requestDto.getCategorieId()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @GetMapping("/eventStore/{id}")
    public Stream getEventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }

}
