package ko.ide.bot.telegram.handler.impl;

import ko.ide.bot.telegram.config.TelegramLogConfig;
import ko.ide.bot.telegram.handler.DefaultExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component
public class DefaultExceptionHandlerImpl implements DefaultExceptionHandler {
    private final TelegramLogConfig telegramLogConfig;

    @Override
    public void onException(Throwable throwable) {
        if (telegramLogConfig.isEnableDefaultExceptionLogger()) {
            log.error(throwable);
            throwable.printStackTrace();
        }
    }
}
