package ko.ide.bot.telegram.handler.impl;

import ko.ide.bot.telegram.config.ChatConfig;
import ko.ide.bot.telegram.event.AuthStateReadyEvent;
import ko.ide.bot.telegram.event.MessageEvent;
import ko.ide.bot.telegram.handler.AuthStateReadyHandler;
import ko.ide.bot.telegram.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class AuthStateReadyHandlerImpl implements AuthStateReadyHandler {
    private final ApplicationContext context;
    private final ChatService fetcher;
    private final ChatConfig chatConfig;

    @Override
    public void onApplicationEvent(AuthStateReadyEvent event) {
        context.publishEvent(new MessageEvent("Telegram connection is ready"));
        fetcher.getMainChatSet(chatConfig.getLimit());
    }
}
