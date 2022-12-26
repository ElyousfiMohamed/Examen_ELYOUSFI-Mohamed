package common_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateCustomerRequestDto {
    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
}
