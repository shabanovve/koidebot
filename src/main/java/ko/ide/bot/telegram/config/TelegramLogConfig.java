package ko.ide.bot.telegram.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram.log")
public class TelegramLogConfig {
    private boolean enableUpdateExceptionLogger;
    private boolean enableDefaultExceptionLogger;
}
