package ko.ide.bot.telegram.event;

import org.drinkless.tdlib.TdApi;
import org.springframework.context.ApplicationEvent;

public class UpdateChatEvent extends ApplicationEvent {
    public UpdateChatEvent(TdApi.Chat chat) {
        super(chat);
    }

    public TdApi.Chat getChat() {
        return (TdApi.Chat) getSource();
    }
}
