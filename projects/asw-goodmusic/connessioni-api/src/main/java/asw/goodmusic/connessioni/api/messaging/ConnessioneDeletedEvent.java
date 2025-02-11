package asw.goodmusic.connessioni.api.messaging;

import asw.goodmusic.common.api.messaging.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConnessioneDeletedEvent implements DomainEvent {
    private Long id;
    private String utente;
    private String seguito;
    private String ruolo;
}
