package common_api.commands;

import lombok.Getter;

@Getter
public class CreateCustomerCommand extends BaseCommand<String>{
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;

    public CreateCustomerCommand(String id, String nom, String prenom, String adresse, String email, String telephone) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
    }

}
