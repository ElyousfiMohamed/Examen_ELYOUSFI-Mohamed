package inventory_service.commandeSide.controllers;

import common_api.commands.CreateCategorieCommand;
import common_api.commands.CreateProduitCommand;
import common_api.commands.UpdateCategorieCommand;
import common_api.dtos.CreateCategorieRequestDto;
import common_api.dtos.CreateProduitRequestDto;
import common_api.dtos.UpdateCategorieRequestDto;
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
@RequestMapping("/category/commands")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategorieRequestDto requestDto) {
        return commandGateway.send(
                new CreateCategorieCommand(
                        UUID.randomUUID().toString(),
                        requestDto.getNom(),
                        requestDto.getDescription()
                ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateCategory(@RequestBody UpdateCategorieRequestDto requestDto) {
        return commandGateway.send(
                new UpdateCategorieCommand(
                        requestDto.getId(),
                        requestDto.getNom(),
                        requestDto.getDescription()
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
