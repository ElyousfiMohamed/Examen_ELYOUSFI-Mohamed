package inventory_service.commandeSide.aggregates;

import common_api.commands.CreateCategorieCommand;
import common_api.commands.UpdateCategorieCommand;
import common_api.events.CategoryCreatedEvent;
import common_api.events.CategoryUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategorieAggragate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private String description;

    public CategorieAggragate() {
    }

    @CommandHandler
    public CategorieAggragate(CreateCategorieCommand command) {
        AggregateLifecycle.apply(
                new CategoryCreatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getDescription()
                )
        );
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getName();
        this.description = event.getDescription();
    }

    @CommandHandler
    public void on(UpdateCategorieCommand command) {
        AggregateLifecycle.apply(
                new CategoryUpdatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getDescription()
                )
        );
    }

    @EventSourcingHandler
    public void on(CategoryUpdatedEvent event) {
        this.id = event.getId();
        this.nom = event.getName();
        this.description = event.getDescription();
    }


}
