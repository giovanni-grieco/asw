package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;

public interface RecensioniBreviInboundEventPort {

    public void onEvent(DomainEvent event);

}
