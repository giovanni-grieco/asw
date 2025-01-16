package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.recensioni.api.messaging.CreatedRecensioneEvent;
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
        if (event instanceof CreatedRecensioneEvent createdRecensioneEvent) {
            recensioniService.createRecensioneBreve(
                    createdRecensioneEvent.getId(),
                    createdRecensioneEvent.getRecensore(),
                    createdRecensioneEvent.getAlbum(),
                    createdRecensioneEvent.getArtista(),
                    createdRecensioneEvent.getGenere(),
                    createdRecensioneEvent.getSunto()
            );
        } else {
            logger.warning("Received unknown event type: " + event);
        }
    }
}
