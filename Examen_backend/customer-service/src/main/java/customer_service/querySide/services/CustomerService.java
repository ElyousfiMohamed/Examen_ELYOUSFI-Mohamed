package customer_service.querySide.services;

import common_api.events.CustomerCreatedEvent;
import common_api.events.CustomerUpdatedEvent;
import customer_service.querySide.entities.Customer;
import customer_service.querySide.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("*****************************");
        log.info("CustomerCreatedEvent received");

        customerRepository.save(
          new Customer(
            event.getId(),
            event.getNom(),
            event.getPrenom(),
            event.getAdresse(),
            event.getEmail(),
            event.getTelephone()
          )
        );
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event) {
        log.info("*****************************");
        log.info("CustomerUpdatedEvent received");

        customerRepository.save(
          new Customer(
            event.getId(),
            event.getNom(),
            event.getPrenom(),
            event.getAdresse(),
            event.getEmail(),
            event.getTelephone()
          )
        );
    }
}
