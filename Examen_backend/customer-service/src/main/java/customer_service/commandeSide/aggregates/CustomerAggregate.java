package customer_service.commandeSide.aggregates;

import common_api.commands.CreateCustomerCommand;
import common_api.commands.UpdateCustomerCommand;
import common_api.events.CustomerCreatedEvent;
import common_api.events.CustomerUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;

    public CustomerAggregate() {
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(
                new CustomerCreatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrenom(),
                        command.getAdresse(),
                        command.getEmail(),
                        command.getTelephone()
                )
        );
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.adresse = event.getAdresse();
        this.email = event.getEmail();
        this.telephone = event.getTelephone();
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand command) {
        AggregateLifecycle.apply(
                new CustomerUpdatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrenom(),
                        command.getAdresse(),
                        command.getEmail(),
                        command.getTelephone()
                )
        );
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.adresse = event.getAdresse();
        this.email = event.getEmail();
        this.telephone = event.getTelephone();
    }
}
