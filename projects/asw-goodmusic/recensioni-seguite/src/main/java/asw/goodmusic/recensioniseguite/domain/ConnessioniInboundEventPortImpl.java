package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.connessioni.api.messaging.ConnessioneCreatedEvent;
import asw.goodmusic.connessioni.api.messaging.ConnessioneDeletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ConnessioniInboundEventPortImpl implements ConnessioniInboundEventPort {

    private final Logger logger = Logger.getLogger(ConnessioniInboundEventPortImpl.class.toString());

    @Autowired
    private ConnessioneService connessioniService;

    @Override
    public void onEvent(DomainEvent event) {
        if(event instanceof ConnessioneCreatedEvent connessioneCreatedEvent){
            this.onCreatedConnessione(connessioneCreatedEvent);
        }else if(event instanceof ConnessioneDeletedEvent connessioneDeletedEvent){
            this.onDeletedConnessione(connessioneDeletedEvent);
        }else{
            logger.warning("Received unknown event type: " + event);
        }
    }

    private void onCreatedConnessione(ConnessioneCreatedEvent event) {
        connessioniService.createConnessione(
                event.getId(),
                event.getUtente(),
                event.getSeguito(),
                event.getRuolo()
        );
    }

    private void onDeletedConnessione(ConnessioneDeletedEvent event) {
        connessioniService.deleteConnessione(event.getId());
    }
}
