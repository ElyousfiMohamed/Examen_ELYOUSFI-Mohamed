package inventory_service.commandeSide.aggregates;

import common_api.commands.CreateProduitCommand;
import common_api.commands.EtatProduit;
import common_api.commands.UpdateProduitCommand;
import common_api.events.ProduitCreatedEvent;
import common_api.events.ProduitUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private double prix;
    private long quantity;
    private EtatProduit etatProduit;
    private String categorieId;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProduitCommand command) {
        if (command.getPrix() < 0) throw new RuntimeException("Prix doit etre positif");
        if (command.getQuantity() < 0) throw new RuntimeException("Quantity doit etre positif");

        AggregateLifecycle.apply(
                new ProduitCreatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrix(),
                        command.getQuantity(),
                        command.getEtatProduit(),
                        command.getCategorieId()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProduitCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.quantity = event.getQuantity();
        this.etatProduit = event.getEtatProduit();
        this.categorieId = event.getCategorieId();
    }

    @CommandHandler
    public void on(UpdateProduitCommand command) {
        AggregateLifecycle.apply(
                new ProduitUpdatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrix(),
                        command.getQuantity(),
                        command.getEtatProduit(),
                        command.getCategorieId()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProduitUpdatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.quantity = event.getQuantity();
        this.etatProduit = event.getEtatProduit();
        this.categorieId = event.getCategorieId();
    }
}
