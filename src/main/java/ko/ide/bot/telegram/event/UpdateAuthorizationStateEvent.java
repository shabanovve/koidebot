package ko.ide.bot.telegram.event;

import org.drinkless.tdlib.TdApi;
import org.springframework.context.ApplicationEvent;

public class UpdateAuthorizationStateEvent extends ApplicationEvent {
    public UpdateAuthorizationStateEvent(TdApi.AuthorizationState authorizationState) {
        super(authorizationState);
    }

    public TdApi.AuthorizationState getAuthorizationState() {
        return (TdApi.AuthorizationState) getSource();
    }
}
