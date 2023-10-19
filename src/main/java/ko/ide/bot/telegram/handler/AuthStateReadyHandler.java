package ko.ide.bot.telegram.handler;

import ko.ide.bot.telegram.event.AuthStateReadyEvent;
import org.springframework.context.ApplicationListener;

public interface AuthStateReadyHandler extends ApplicationListener<AuthStateReadyEvent> {
}
