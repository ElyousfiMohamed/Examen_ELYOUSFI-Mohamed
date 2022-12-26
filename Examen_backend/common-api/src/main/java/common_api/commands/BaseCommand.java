package common_api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand <T> {
    @TargetAggregateIdentifier
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
