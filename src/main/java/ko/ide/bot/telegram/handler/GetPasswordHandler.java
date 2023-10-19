package ko.ide.bot.telegram.handler;

import ko.ide.bot.telegram.event.GetPasswordEvent;
import org.springframework.context.ApplicationListener;

public interface GetPasswordHandler extends ApplicationListener<GetPasswordEvent> {
}
