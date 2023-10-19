package ko.ide.bot.cliapp.handler;

import ko.ide.bot.telegram.AuthState;
import ko.ide.bot.telegram.event.GetAuthCodeEvent;
import ko.ide.bot.telegram.handler.GetAuthCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class GetAuthCodeHandlerImpl extends AbstractStringDialogHandler
        implements GetAuthCodeHandler {
    private final AuthState authState;

    @Override
    public void onApplicationEvent(GetAuthCodeEvent event) {
        handle(
                "Enter authorization code",
                result -> authState.setAuthCode(result.get()),
                event.getLatch()
        );
    }
}
