package common_api.events;

import lombok.Getter;

@Getter
public class CustomerUpdatedEvent extends BaseEvent<String> {
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;

    public CustomerUpdatedEvent(String id, String nom, String prenom, String adresse, String email, String telephone) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
    }


}
