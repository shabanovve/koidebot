package ko.ide.bot.telegram.handler.impl;

import ko.ide.bot.telegram.ChatState;
import ko.ide.bot.telegram.config.ChatConfig;
import ko.ide.bot.telegram.event.UpdateChatEvent;
import ko.ide.bot.telegram.handler.UpdateChatHandler;
import lombok.RequiredArgsConstructor;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class UpdateChatHandlerImpl implements UpdateChatHandler {
    private final ChatState chatState;
    private final ChatConfig chatConfig;
    @Override
    public void onApplicationEvent(UpdateChatEvent event) {
        TdApi.Chat chat = event.getChat();
        if (chat.title.toLowerCase().contains(chatConfig.getTitlePart())) {
            chatState.getChatSet().add(chat);
            chatState.getChatMap().put(chat.id, chat);
        }
    }
}
