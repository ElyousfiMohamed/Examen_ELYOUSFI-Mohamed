package inventory_service.querySide.services;

import common_api.events.CategoryCreatedEvent;
import common_api.events.CategoryUpdatedEvent;
import common_api.events.ProduitCreatedEvent;
import inventory_service.querySide.entities.Category;
import inventory_service.querySide.entities.Produit;
import inventory_service.querySide.repositories.CategoryReporsitory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {
    private CategoryReporsitory categoryReporsitory;

    @EventHandler
    public void on(CategoryCreatedEvent event) {
        log.info("*****************************");
        log.info("CategoryCreatedEvent received");

        categoryReporsitory.save(
          new Category(
            event.getId(),
            event.getName(),
            event.getDescription()
          )
        );
    }

    @EventHandler
    public void on(CategoryUpdatedEvent event) {
        log.info("*****************************");
        log.info("CategoryUpdatedEvent received");

        Category category = categoryReporsitory.findById(event.getId()).get();
        category.setNom(event.getName());
        category.setDescription(event.getDescription());
        categoryReporsitory.save(category);
    }
}
