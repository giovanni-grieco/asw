package asw.goodmusic.recensioni.messaging;

import asw.goodmusic.recensioni.domain.RecensioniEventPublisherPort;
import org.springframework.stereotype.Component;

import asw.goodmusic.common.api.messaging.DomainEvent;
@Component
public class RecensioniEventKafkaPublisherAdapter implements RecensioniEventPublisherPort {



    @Override
    public void publish(DomainEvent event) {

    }
}
