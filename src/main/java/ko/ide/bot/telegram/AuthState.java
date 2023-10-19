package ko.ide.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Getter
@Setter
@Component
public class AuthState {
    private TdApi.AuthorizationState state;
    private String phoneNumber;
    private String authCode;
    private String password;
    private boolean isAuthorized = false;
}
