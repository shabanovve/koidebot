package ko.ide.bot.telegram.event;

import org.drinkless.tdlib.TdApi;
import org.springframework.context.ApplicationEvent;

public class RepeatAuthRequestEvent extends ApplicationEvent {
    public RepeatAuthRequestEvent(TdApi.Error error) {
        super(error);
    }

    public TdApi.Error getError() {
        return (TdApi.Error) getSource();
    }
}
