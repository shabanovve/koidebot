package ko.ide.bot;

import jakarta.annotation.PostConstruct;
import ko.ide.bot.telegram.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class Starter {
    private final TelegramService telegramService;

    @PostConstruct
    public void init() {
        telegramService.start();
    }
}
