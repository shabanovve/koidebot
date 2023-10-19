package ko.ide.bot.telegram.event;

import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CountDownLatch;

public class GetPhoneNumberEvent extends ApplicationEvent {
    public GetPhoneNumberEvent(CountDownLatch latch) {
        super(latch);
    }

    public CountDownLatch getLatch() {
        return (CountDownLatch) getSource();
    }
}
