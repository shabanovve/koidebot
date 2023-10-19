package ko.ide.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
@Getter
@Setter
@Component
public class ChatState {
    private final Set<TdApi.Chat> chatSet = new HashSet<>();
    private final Map<Long, TdApi.Chat> chatMap = new ConcurrentHashMap<>();
    private boolean haveFullChatList;
}
