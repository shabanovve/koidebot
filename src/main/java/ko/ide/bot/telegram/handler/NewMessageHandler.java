package ko.ide.bot.telegram.handler;

import ko.ide.bot.telegram.event.NewMessageEvent;
import org.springframework.context.ApplicationListener;

public interface NewMessageHandler extends ApplicationListener<NewMessageEvent> {
}
