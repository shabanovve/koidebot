package ko.ide.bot;

import ko.ide.bot.telegram.config.ChatConfig;
import ko.ide.bot.telegram.config.TelegramApiConfig;
import ko.ide.bot.telegram.config.TelegramLogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
@EnableConfigurationProperties({ChatConfig.class, TelegramLogConfig.class, TelegramApiConfig.class})
public class App {
    public static void main(String[] args) throws InterruptedException {
        //noinspection resource
        SpringApplication.run(App.class, args);
        //noinspection InfiniteLoopStatement
        while (true) {
            //noinspection BusyWait
            Thread.sleep(1000);
        }
    }

}
