package ko.ide.bot.telegram.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram.api")
public class TelegramApiConfig {
    private int id;
    private String hash;
}
