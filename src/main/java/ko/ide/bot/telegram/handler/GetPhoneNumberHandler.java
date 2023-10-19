package ko.ide.bot.telegram.handler;

import ko.ide.bot.telegram.event.GetPhoneNumberEvent;
import org.springframework.context.ApplicationListener;

public interface GetPhoneNumberHandler extends ApplicationListener<GetPhoneNumberEvent> {
}
