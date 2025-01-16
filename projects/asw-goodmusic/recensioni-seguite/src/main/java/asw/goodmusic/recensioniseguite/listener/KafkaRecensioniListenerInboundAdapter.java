package asw.goodmusic.recensioniseguite.listener;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.recensioniseguite.domain.RecensioniBreviInboundEventPort;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaRecensioniListenerInboundAdapter {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RecensioniBreviInboundEventPort recensioniBreviInboundEventPort;

    @KafkaListener(topics = "${asw.kafka.channels.in.recensioni}")
    public void listen(ConsumerRecord<String, DomainEvent> record) {
        logger.info("RECEIVED EVENT: " + record.value());
        recensioniBreviInboundEventPort.onEvent(record.value());
    }

}
