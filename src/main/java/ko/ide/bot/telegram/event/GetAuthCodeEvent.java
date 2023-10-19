package ko.ide.bot.telegram.event;

import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CountDownLatch;

public class GetAuthCodeEvent extends ApplicationEvent {
    public GetAuthCodeEvent(CountDownLatch latch) {
        super(latch);
    }

    public CountDownLatch getLatch() {
        return (CountDownLatch) getSource();
    }
}
