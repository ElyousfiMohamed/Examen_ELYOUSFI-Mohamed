package common_api.commands;

import lombok.Getter;

@Getter
public class UpdateCategorieCommand extends BaseCommand<String>{
    private String nom;
    private String description;

    public UpdateCategorieCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
