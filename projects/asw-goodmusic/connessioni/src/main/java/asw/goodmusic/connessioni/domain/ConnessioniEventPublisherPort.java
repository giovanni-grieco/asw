package asw.goodmusic.connessioni.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public interface ConnessioniEventPublisherPort {

    public void publish(DomainEvent message);
}
