package ko.ide.bot.telegram.event;

import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CountDownLatch;

public class GetPasswordEvent extends ApplicationEvent {
    public GetPasswordEvent(CountDownLatch latch) {
        super(latch);
    }

    public CountDownLatch getLatch() {
        return (CountDownLatch) getSource();
    }
}
