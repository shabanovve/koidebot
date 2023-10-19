package ko.ide.bot.telegram.event;

import org.drinkless.tdlib.TdApi;
import org.springframework.context.ApplicationEvent;

public class NewMessageEvent extends ApplicationEvent {
    public NewMessageEvent(TdApi.UpdateNewMessage message) {
        super(message);
    }

    public TdApi.UpdateNewMessage getUpdateNewMessage() {
        return (TdApi.UpdateNewMessage) getSource();
    }
}
