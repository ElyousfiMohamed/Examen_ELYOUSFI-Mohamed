package customer_service.commandeSide.controllers;

import com.thoughtworks.xstream.XStream;
import common_api.commands.CreateCustomerCommand;
import common_api.commands.UpdateCustomerCommand;
import common_api.dtos.CreateCustomerRequestDto;
import common_api.dtos.UpdateCustomerRequestDto;
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
@RequestMapping("/customer/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerCommandController {
    private CommandGateway commandeGateway;
    private EventStore eventStore;


    @PostMapping("/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDto requestDto) {
        return commandeGateway.send(
                new CreateCustomerCommand(
                        UUID.randomUUID().toString(),
                        requestDto.getNom(),
                        requestDto.getPrenom(),
                        requestDto.getAdresse(),
                        requestDto.getEmail(),
                        requestDto.getTelephone()
                )
        );
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCustomerRequestDto requestDto) {
        return commandeGateway.send(
                new UpdateCustomerCommand(
                        requestDto.getId(),
                        requestDto.getNom(),
                        requestDto.getPrenom(),
                        requestDto.getAdresse(),
                        requestDto.getEmail(),
                        requestDto.getTelephone()
                )
        );
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
