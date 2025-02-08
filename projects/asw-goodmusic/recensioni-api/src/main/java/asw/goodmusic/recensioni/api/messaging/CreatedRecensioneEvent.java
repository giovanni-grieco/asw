package asw.goodmusic.recensioni.api.messaging;

import asw.goodmusic.common.api.messaging.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreatedRecensioneEvent implements DomainEvent{
        private Long id;
        private String recensore;
        private String album;
        private String artista;
        private String genere;
        private String testo;
        private String sunto;
}
