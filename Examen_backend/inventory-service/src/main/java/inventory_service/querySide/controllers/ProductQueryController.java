package inventory_service.querySide.controllers;

import common_api.queries.GetAllCustomersQuery;
import common_api.queries.GetAllProduitesQuery;
import common_api.queries.GetCustomerByIdQuery;
import common_api.queries.GetProduitByIdQuery;
import inventory_service.querySide.entities.Produit;
import inventory_service.querySide.repositories.ProduitReporsitory;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit/queries")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductQueryController {
    private QueryGateway queryGateway;
    private ProduitReporsitory produitReporsitory;

    @GetMapping("/all")
    public List<Produit> getAllProduits() {
        return queryGateway.query(new GetAllProduitesQuery(),
                ResponseTypes.multipleInstancesOf(Produit.class)).join();
    }

    @QueryHandler
    public List<Produit> handle(GetAllProduitesQuery query) {
        return produitReporsitory.findAll();
    }

    @GetMapping("byId/{id}")
    public Produit getProduitById(@PathVariable String id) {
        return queryGateway.query(new GetProduitByIdQuery(id),
                ResponseTypes.instanceOf(Produit.class)).join();
    }

    @QueryHandler
    public Produit handle(GetProduitByIdQuery query) {
        return produitReporsitory.findById(query.getId()).orElse(null);
    }
}
