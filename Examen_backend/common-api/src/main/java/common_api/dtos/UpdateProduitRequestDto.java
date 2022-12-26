package common_api.dtos;

import common_api.commands.EtatProduit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateProduitRequestDto {
    private String id;
    private String nom;
    private double prix;
    private long quantity;
    private EtatProduit etatProduit;
    private String categorieId;
}
