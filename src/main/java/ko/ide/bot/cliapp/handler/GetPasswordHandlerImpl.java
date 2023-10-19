package ko.ide.bot.cliapp.handler;

import ko.ide.bot.telegram.AuthState;
import ko.ide.bot.telegram.event.GetPasswordEvent;
import ko.ide.bot.telegram.handler.GetPasswordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class GetPasswordHandlerImpl extends AbstractStringDialogHandler
        implements GetPasswordHandler {
    private final AuthState authState;


    @Override
    public void onApplicationEvent(GetPasswordEvent event) {
        handle(
                "Enter password",
                result -> authState.setPassword(result.get()),
                event.getLatch(),
                true
        );
    }
}
