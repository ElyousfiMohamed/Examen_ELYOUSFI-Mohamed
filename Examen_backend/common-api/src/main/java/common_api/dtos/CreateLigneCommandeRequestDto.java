package common_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateLigneCommandeRequestDto {
    private long quantiteProduit;
    private double prixUnitaire;
    private double remise;
    private String idCommande;
    private String idProduit;
}
