package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.recensioni.api.messaging.RecensioneCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RecensioniBreviInboundEventPortImpl implements RecensioniBreviInboundEventPort {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RecensioniBreviService recensioniService;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof RecensioneCreatedEvent recensioneCreatedEvent) {
            this.onRecensioneCreated(recensioneCreatedEvent);
        } else {
            logger.warning("Received unknown event type: " + event);
        }
    }

    private void onRecensioneCreated(RecensioneCreatedEvent event) {
        recensioniService.createRecensioneBreve(
                event.getId(),
                event.getRecensore(),
                event.getAlbum(),
                event.getArtista(),
                event.getGenere(),
                event.getSunto()
        );
    }
}
