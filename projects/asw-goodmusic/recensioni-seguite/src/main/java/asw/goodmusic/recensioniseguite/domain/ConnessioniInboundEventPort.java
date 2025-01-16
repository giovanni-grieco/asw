package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;

public interface ConnessioniInboundEventPort {

    public void onEvent(DomainEvent event);

}
