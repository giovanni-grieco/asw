package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.connessioni.api.messaging.CreatedConnessioneEvent;
import asw.goodmusic.connessioni.api.messaging.DeletedConnessioneEvent;
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
        if(event instanceof CreatedConnessioneEvent createdConnessioneEvent){
            this.onCreatedConnessione(createdConnessioneEvent);
        }else if(event instanceof DeletedConnessioneEvent deletedConnessioneEvent){
            this.onDeletedConnessione(deletedConnessioneEvent);
        }else{
            logger.warning("Received unknown event type: " + event);
        }
    }

    private void onCreatedConnessione(CreatedConnessioneEvent event) {
        connessioniService.createConnessione(
                event.getId(),
                event.getUtente(),
                event.getSeguito(),
                event.getRuolo()
        );
    }

    private void onDeletedConnessione(DeletedConnessioneEvent event) {
        connessioniService.deleteConnessione(event.getId());
    }
}
