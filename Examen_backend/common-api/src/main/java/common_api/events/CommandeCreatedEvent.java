package common_api.events;

import common_api.commands.EtatCommand;
import lombok.Getter;

import java.util.Date;

@Getter
public class CommandeCreatedEvent extends BaseEvent<String>{
    private Date dateCommande;
    private Date dateLivraison;
    private String adresseLivraison;
    private EtatCommand etatCommand;
    private String idCustomer;

    public CommandeCreatedEvent(String id, Date dateCommande, Date dateLivraison, String adresseLivraison, EtatCommand etatCommand, String idCustomer) {
        super(id);
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.adresseLivraison = adresseLivraison;
        this.etatCommand = etatCommand;
        this.idCustomer = idCustomer;
    }
}
