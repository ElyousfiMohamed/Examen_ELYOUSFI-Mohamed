package inventory_service.querySide.services;

import common_api.commands.EtatProduit;
import common_api.events.CustomerCreatedEvent;
import common_api.events.ProduitCreatedEvent;
import common_api.events.ProduitUpdatedEvent;
import inventory_service.querySide.entities.Category;
import inventory_service.querySide.entities.Produit;
import inventory_service.querySide.repositories.CategoryReporsitory;
import inventory_service.querySide.repositories.ProduitReporsitory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    private ProduitReporsitory productRepository;
    private CategoryReporsitory categoryReporsitory;

    @EventHandler
    public void on(ProduitCreatedEvent event) {
        log.info("*****************************");
        log.info("ProductCreatedEvent received");

        Produit produit = new Produit();
        produit.setId(event.getId());
        produit.setNom(event.getNom());
        produit.setPrix(event.getPrix());
        produit.setQuantity(event.getQuantity());
        produit.setEtatProduit(EtatProduit.Disponible);
        Category category = categoryReporsitory.findById(event.getCategorieId()).get();
        if (category != null) {
            produit.setCategory(category);
        }
        productRepository.save(produit);
    }

    @EventHandler
    public void on(ProduitUpdatedEvent event) {
        log.info("*****************************");
        log.info("ProductUpdatedEvent received");

        Produit produit = productRepository.findById(event.getId()).get();
        produit.setNom(event.getNom());
        produit.setPrix(event.getPrix());
        produit.setQuantity(event.getQuantity());
        produit.setEtatProduit(EtatProduit.Disponible);
        Category category = categoryReporsitory.findById(event.getCategorieId()).get();
        if (category != null) {
            produit.setCategory(category);
        }
        productRepository.save(produit);
    }

}
