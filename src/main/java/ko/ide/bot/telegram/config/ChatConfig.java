package ko.ide.bot.telegram.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "telegram.chat")
public class ChatConfig {
    private long id;
    private String titlePart;
    private int limit;
}
