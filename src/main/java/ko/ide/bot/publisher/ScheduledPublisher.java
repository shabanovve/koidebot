package ko.ide.bot.publisher;

import ko.ide.bot.telegram.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class ScheduledPublisher {
    private final TelegramService telegramService;
    @SuppressWarnings("unused")
    @Scheduled(cron = "${publisher.cron.expression}")
    private void publishMessage() {
        telegramService.sendMessage();
    }
}
