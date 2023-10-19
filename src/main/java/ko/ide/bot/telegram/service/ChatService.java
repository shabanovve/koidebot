package ko.ide.bot.telegram.service;

import ko.ide.bot.telegram.ChatState;
import ko.ide.bot.telegram.event.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class ChatService {
    private final ApplicationContext context;
    private final ChatState chatState;


    public synchronized void getMainChatSet(int limit) {
        Client client = context.getBean(Client.class);
        TdApi.LoadChats request = new TdApi.LoadChats(new TdApi.ChatListMain(), limit);
        client.send(request, new Handler(limit));
    }

    @RequiredArgsConstructor
    private class Handler implements Client.ResultHandler {
        private final int limit;

        @Override
        public void onResult(TdApi.Object object) {
            switch (object.getConstructor()) {
                case TdApi.Error.CONSTRUCTOR -> {
                    if (((TdApi.Error) object).code == 404) {
                        synchronized (chatState.getChatSet()) {
                            chatState.setHaveFullChatList(true);
                            chatState.getChatSet()
                                    .forEach(
                                            chat -> context.publishEvent(
                                                    new MessageEvent("Found channel " + chat.title + ":" + chat.id)
                                            )
                                    );
                        }
                    } else {
                        context.publishEvent(new MessageEvent(object.toString()));
                        log.error(object.toString());
                    }
                }
                case TdApi.Ok.CONSTRUCTOR -> getMainChatSet(limit);
                default -> log.error("Cannot handle status " + object);
            }
        }
    }

}
