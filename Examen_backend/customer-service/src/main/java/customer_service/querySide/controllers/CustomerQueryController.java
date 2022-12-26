package customer_service.querySide.controllers;

import common_api.queries.GetAllCustomersQuery;
import common_api.queries.GetCustomerByIdQuery;
import customer_service.querySide.entities.Customer;
import customer_service.querySide.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/queries")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerQueryController {
    private QueryGateway queryGateway;
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return queryGateway.query(new GetAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class)).join();
    }

    @QueryHandler
    public List<Customer> handle(GetAllCustomersQuery query) {
        return customerRepository.findAll();
    }

    @GetMapping("byId/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return queryGateway.query(new GetCustomerByIdQuery(id),
                ResponseTypes.instanceOf(Customer.class)).join();
    }

    @QueryHandler
    public Customer handle(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.getId()).orElse(null);
    }

}
