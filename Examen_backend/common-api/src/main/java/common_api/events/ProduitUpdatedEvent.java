package common_api.events;

import common_api.commands.BaseCommand;
import common_api.commands.EtatProduit;
import lombok.Getter;

@Getter
public class ProduitUpdatedEvent extends BaseEvent<String> {
    private String nom;
    private double prix;
    private long quantity;
    private EtatProduit etatProduit;
    private String categorieId;

    public ProduitUpdatedEvent(String id, String nom, double prix, long quantity, EtatProduit etatProduit, String categorieId) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.quantity = quantity;
        this.etatProduit = etatProduit;
        this.categorieId = categorieId;
    }
}
