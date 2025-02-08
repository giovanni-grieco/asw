package asw.goodmusic.connessioni.messaging;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.connessioni.domain.ConnessioniEventPublisherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ConnessioniEventPublisherKafkaAdapter implements ConnessioniEventPublisherPort {

    @Value("${asw.kafka.channel.out}")
    private String channel;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        logger.info("PUBLISHING EVENT: " + event);
        kafkaTemplate.send(channel, event);
    }
}
