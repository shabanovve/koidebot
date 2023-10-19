package ko.ide.bot.telegram.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
    public MessageEvent(String message) {
        super(message);
    }
}
