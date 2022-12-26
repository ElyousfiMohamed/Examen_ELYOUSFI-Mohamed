package inventory_service.querySide.controllers;

import common_api.queries.GetAllCategoriesQuery;
import common_api.queries.GetAllProduitesQuery;
import common_api.queries.GetCategorieByIdQuery;
import common_api.queries.GetProduitByIdQuery;
import inventory_service.querySide.entities.Category;
import inventory_service.querySide.entities.Produit;
import inventory_service.querySide.repositories.CategoryReporsitory;
import inventory_service.querySide.repositories.ProduitReporsitory;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/queries")
@AllArgsConstructor
@CrossOrigin("*")
public class CategorieQueryController {
    private QueryGateway queryGateway;
    private CategoryReporsitory categoryReporsitory;

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return queryGateway.query(new GetAllCategoriesQuery(),
                ResponseTypes.multipleInstancesOf(Category.class)).join();
    }

    @QueryHandler
    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryReporsitory.findAll();
    }

    @GetMapping("byId/{id}")
    public Category getCategoryById(@PathVariable String id) {
        return queryGateway.query(new GetCategorieByIdQuery(id),
                ResponseTypes.instanceOf(Category.class)).join();
    }

    @QueryHandler
    public Category handle(GetCategorieByIdQuery query) {
        return categoryReporsitory.findById(query.getId()).orElse(null);
    }
}
