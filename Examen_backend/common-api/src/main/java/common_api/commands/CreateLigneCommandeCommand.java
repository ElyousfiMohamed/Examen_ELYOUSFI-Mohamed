package common_api.commands;

import lombok.Getter;

@Getter
public class CreateLigneCommandeCommand extends BaseCommand<String>{
    private long quantiteProduit;
    private double prixUnitaire;
    private double remise;
    private String idCommande;
    private String idProduit;

    public CreateLigneCommandeCommand(String id, long quantiteProduit, double prixUnitaire, double remise, String idCommande, String idProduit) {
        super(id);
        this.quantiteProduit = quantiteProduit;
        this.prixUnitaire = prixUnitaire;
        this.remise = remise;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
    }
}
