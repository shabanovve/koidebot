package ko.ide.bot.telegram.handler.impl;

import ko.ide.bot.telegram.ChatState;
import ko.ide.bot.telegram.config.ChatConfig;
import ko.ide.bot.telegram.event.NewMessageEvent;
import ko.ide.bot.telegram.event.UpdateAuthorizationStateEvent;
import ko.ide.bot.telegram.event.UpdateChatEvent;
import ko.ide.bot.telegram.handler.ResultHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.drinkless.tdlib.TdApi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class ResultHandlerImpl implements ResultHandler {
    private final ConfigurableApplicationContext context;
    private final ChatState chatState;
    private final ChatConfig chatConfig;

    @Override
    public void onResult(TdApi.Object object) {
        if (object instanceof TdApi.Update) {
            switch (object.getConstructor()) {
                case TdApi.UpdateAuthorizationState.CONSTRUCTOR -> {
                    TdApi.AuthorizationState authorizationState =
                            ((TdApi.UpdateAuthorizationState) object).authorizationState;
                    context.publishEvent(new UpdateAuthorizationStateEvent(authorizationState));
                }
                case TdApi.UpdateNewChat.CONSTRUCTOR -> {
                    TdApi.Chat chat = ((TdApi.UpdateNewChat) object).chat;
                    context.publishEvent(new UpdateChatEvent(chat));
                }
                case TdApi.UpdateChatLastMessage.CONSTRUCTOR -> {
                    long chatId = ((TdApi.UpdateChatLastMessage) object).chatId;
                    TdApi.Chat chat = chatState.getChatMap().get(chatId);
                    context.publishEvent(new UpdateChatEvent(chat));
                }
                case TdApi.UpdateChatPosition.CONSTRUCTOR -> {
                    long chatId = ((TdApi.UpdateChatPosition) object).chatId;
                    TdApi.Chat chat = chatState.getChatMap().get(chatId);
                    context.publishEvent(new UpdateChatEvent(chat));
                }
                case TdApi.UpdateChatDraftMessage.CONSTRUCTOR -> {
                    long chatId = ((TdApi.UpdateChatDraftMessage) object).chatId;
                    TdApi.Chat chat = chatState.getChatMap().get(chatId);
                    context.publishEvent(new UpdateChatEvent(chat));
                }
                case TdApi.UpdateNewMessage.CONSTRUCTOR -> {
                    TdApi.UpdateNewMessage updateNewMessage = (TdApi.UpdateNewMessage) object;
                    TdApi.Message message = updateNewMessage.message;
                    long chatId = message.chatId;
                    if (chatId == chatConfig.getId()) {
                        context.publishEvent(new NewMessageEvent(updateNewMessage));
                    }
                }
                case TdApi.UpdateChatUnreadReactionCount.CONSTRUCTOR ->
                    System.out.println("Object=" + object);
                default -> log.info("Skip event " + object);
            }
        } else {
            throw new IllegalStateException("Cannot handle " + object.getClass().getCanonicalName());
        }
    }
}
