package asw.goodmusic.recensioni.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
public interface RecensioniEventPublisherPort {

    public void publish(DomainEvent message);

}
