package asw.goodmusic.recensioniseguite.listener;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.recensioniseguite.domain.ConnessioniInboundEventPort;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaConnessioniListenerInboundAdapter {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ConnessioniInboundEventPort connessioniInboundEventPort;

    @KafkaListener(topics = "${asw.kafka.channels.in.connessioni}")
    public void listen(ConsumerRecord<String, DomainEvent> record) {
        logger.info("RECEIVED EVENT: " + record.value());
        connessioniInboundEventPort.onEvent(record.value());
    }

}
