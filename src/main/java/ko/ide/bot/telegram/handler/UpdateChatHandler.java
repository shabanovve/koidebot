package ko.ide.bot.telegram.handler;

import ko.ide.bot.telegram.event.UpdateChatEvent;
import org.springframework.context.ApplicationListener;

public interface UpdateChatHandler extends ApplicationListener<UpdateChatEvent> {
}
