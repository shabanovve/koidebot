package ko.ide.bot.telegram.handler;

import ko.ide.bot.telegram.event.GetAuthCodeEvent;
import org.springframework.context.ApplicationListener;

public interface GetAuthCodeHandler extends ApplicationListener<GetAuthCodeEvent> {
}
