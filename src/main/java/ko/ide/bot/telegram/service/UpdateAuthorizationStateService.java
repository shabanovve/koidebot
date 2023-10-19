package ko.ide.bot.telegram.service;


import ko.ide.bot.telegram.AuthState;
import ko.ide.bot.telegram.config.TelegramApiConfig;
import ko.ide.bot.telegram.event.AuthStateReadyEvent;
import ko.ide.bot.telegram.event.GetAuthCodeEvent;
import ko.ide.bot.telegram.event.GetPasswordEvent;
import ko.ide.bot.telegram.event.GetPhoneNumberEvent;
import ko.ide.bot.telegram.handler.impl.AuthorizationRequestHandlerImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Log4j2
@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class UpdateAuthorizationStateService {
    private final ApplicationContext context;
    private final AuthorizationRequestHandlerImpl authorizationRequestHandler;
    private final TelegramApiConfig telegramApiConfig;
    private final AuthState authState;

    @SneakyThrows
    public void handle(TdApi.AuthorizationState authorizationState) {
        if (authorizationState != null) {
            authState.setState(authorizationState);
        }
        switch (authState.getState().getConstructor()) {
            case TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR -> {
                authState.setAuthorized(false);
                TdApi.SetTdlibParameters request = new TdApi.SetTdlibParameters();
                request.databaseDirectory = "tdlib";
                request.useMessageDatabase = true;
                request.useSecretChats = true;
                request.apiId = telegramApiConfig.getId();
                request.apiHash = telegramApiConfig.getHash();
                request.systemLanguageCode = "en";
                request.deviceModel = "Desktop";
                request.applicationVersion = "1.0";
                request.enableStorageOptimizer = true;
                context.getBean(Client.class).send(request, authorizationRequestHandler);
            }
            case TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR -> {
                CountDownLatch latch = new CountDownLatch(1);
                context.publishEvent(new GetPhoneNumberEvent(latch));
                latch.await();
                String phoneNumber = authState.getPhoneNumber();
                log.info("Entered phone number: " + phoneNumber);
                sendRequest(new TdApi.SetAuthenticationPhoneNumber(phoneNumber, null));
                authState.setPhoneNumber("");
            }
            case TdApi.AuthorizationStateWaitCode.CONSTRUCTOR -> {
                CountDownLatch latch = new CountDownLatch(1);
                context.publishEvent(new GetAuthCodeEvent(latch));
                latch.await();
                sendRequest(new TdApi.CheckAuthenticationCode(authState.getAuthCode()));
                authState.setAuthCode("");
            }
            case TdApi.AuthorizationStateWaitPassword.CONSTRUCTOR -> {
                CountDownLatch latch = new CountDownLatch(1);
                context.publishEvent(new GetPasswordEvent(latch));
                latch.await();
                sendRequest(new TdApi.CheckAuthenticationPassword(authState.getPassword()));
                authState.setPassword("");
            }
            case TdApi.AuthorizationStateReady.CONSTRUCTOR -> {
                if (authState.isAuthorized()) {
                    return;
                }
                authState.setAuthorized(true);
                context.publishEvent(new AuthStateReadyEvent(""));
            }
        }
    }
    private void sendRequest(TdApi.Function<TdApi.Ok> function) {
        context.getBean(Client.class).send(function, authorizationRequestHandler);
    }

}
