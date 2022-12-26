package common_api.dtos;

import common_api.commands.EtatCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateCommandeRequestDto {
    private String id;
    private Date dateCommande;
    private Date dateLivraison;
    private String adresseLivraison;
    private EtatCommand etatCommand;
    private String idCustomer;
}
