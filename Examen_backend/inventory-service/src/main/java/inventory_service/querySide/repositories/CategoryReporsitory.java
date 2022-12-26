package inventory_service.querySide.repositories;

import inventory_service.querySide.entities.Category;
import inventory_service.querySide.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReporsitory extends JpaRepository<Category, String> {
}
