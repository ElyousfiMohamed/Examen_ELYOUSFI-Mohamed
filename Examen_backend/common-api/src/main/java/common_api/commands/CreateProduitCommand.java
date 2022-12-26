package common_api.commands;

import lombok.Getter;

@Getter
public class CreateProduitCommand extends BaseCommand<String>{
    private String nom;
    private double prix;
    private long quantity;
    private EtatProduit etatProduit;
    private String categorieId;

    public CreateProduitCommand(String id, String nom, double prix, long quantity, EtatProduit etatProduit, String categorieId) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.quantity = quantity;
        this.etatProduit = etatProduit;
        this.categorieId = categorieId;
    }

}
