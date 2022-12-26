package common_api.events;

import lombok.Getter;

@Getter
public class LigneCommandeCreatedEvent extends BaseEvent<String> {
    private long quantiteProduit;
    private double prixUnitaire;
    private double remise;
    private String idCommande;
    private String idProduit;

    public LigneCommandeCreatedEvent(String id, long quantiteProduit, double prixUnitaire, double remise, String idCommande, String idProduit) {
        super(id);
        this.quantiteProduit = quantiteProduit;
        this.prixUnitaire = prixUnitaire;
        this.remise = remise;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
    }
}
