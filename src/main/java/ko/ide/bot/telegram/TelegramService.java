package ko.ide.bot.telegram;

import ko.ide.bot.telegram.event.MessageEvent;
import ko.ide.bot.telegram.handler.DefaultExceptionHandler;
import ko.ide.bot.telegram.handler.ResultHandler;
import ko.ide.bot.telegram.handler.UpdateExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class TelegramService {
    private final ResultHandler resultHandler;
    private final UpdateExceptionHandler updateExceptionHandler;
    private final DefaultExceptionHandler defaultExceptionHandler;
    private final ConfigurableApplicationContext context;

    public void start() {
        String message = "Connecting to telegram";
        log.info(message);
        context.getBean(NativeLibsLoader.class).load();
        context.publishEvent(new MessageEvent(message));
        setupLogging();
        Client client = Client.create(resultHandler, updateExceptionHandler, defaultExceptionHandler);
        context.getBeanFactory().registerSingleton("client", client);
    }

    private void setupLogging() {
        // set log message handler to handle only fatal errors (0) and plain log messages (-1)
        Client.setLogMessageHandler(0, (level, message) -> {

        });

        // disable TDLib log and redirect fatal errors and plain log messages to a file
        Client.execute(new TdApi.SetLogVerbosityLevel(0));
        if (Client.execute(new TdApi.SetLogStream(new TdApi.LogStreamFile("tdlib.log", 1 << 27, false))) instanceof TdApi.Error) {
            throw new IOError(new IOException("Write access to the current directory is required"));
        }
    }
}
