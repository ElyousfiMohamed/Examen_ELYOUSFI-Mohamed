package common_api.commands;

import lombok.Getter;

@Getter
public class CreateCategorieCommand extends BaseCommand<String>{
    private String nom;
    private String description;

    public CreateCategorieCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
