package common_api.events;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseEvent <T> {
    @TargetAggregateIdentifier
    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
