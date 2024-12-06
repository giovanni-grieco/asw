package asw.goodmusic.recensioni.messaging;

import asw.goodmusic.recensioni.domain.RecensioniEventPublisherPort;
import org.springframework.stereotype.Component;

import asw.goodmusic.common.api.messaging.DomainEvent;
@Component
public class RecensioniEventPublisherKafkaAdapter implements RecensioniEventPublisherPort {



    @Override
    public void publish(DomainEvent event) {

    }
}
