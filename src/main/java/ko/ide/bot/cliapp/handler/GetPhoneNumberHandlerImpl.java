package ko.ide.bot.cliapp.handler;

import ko.ide.bot.telegram.AuthState;
import ko.ide.bot.telegram.event.GetPhoneNumberEvent;
import ko.ide.bot.telegram.handler.GetPhoneNumberHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class GetPhoneNumberHandlerImpl extends AbstractStringDialogHandler
        implements GetPhoneNumberHandler {
    private final AuthState authState;

    @Override
    public void onApplicationEvent(GetPhoneNumberEvent event) {
        handle(
                "Enter phone number",
                result -> authState.setPhoneNumber(result.get()),
                event.getLatch()
                );
    }

}
