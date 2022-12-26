package inventory_service.querySide.entities;

import common_api.commands.EtatProduit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    private String id;
    private String nom;
    private double prix;
    private long quantity;
    @Enumerated(EnumType.STRING)
    private EtatProduit etatProduit;
    private String categorieId;

    public Produit(String id, String nom, double prix, long quantity, EtatProduit etatProduit, String categorieId) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantity = quantity;
        this.etatProduit = etatProduit;
        this.categorieId = categorieId;
    }

    @ManyToOne
    private Category category;
}
