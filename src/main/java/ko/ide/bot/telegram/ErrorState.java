package ko.ide.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Getter
@Setter
@Component
public class ErrorState {
    private static final Integer MAX_COUNT_ERROR = 10;
    private static final Map<Integer, Integer> errorCounter = new HashMap<>();
    private Instant before = Instant.now();

    public void countError(TdApi.Error error) {
        Integer count = errorCounter.get(error.code);
        if (count == null) {
            errorCounter.put(error.code, 1);
        } else {
            if (Instant.now().minus(1, ChronoUnit.MINUTES).isAfter(before)) {
                errorCounter.clear();
                before = Instant.now();
                count = 0;
            }
            if (count > MAX_COUNT_ERROR) {
                throw new IllegalStateException("Too many error " + errorCounter);
            }
            pause(count);
            errorCounter.put(error.code, ++count);
        }
    }

    private void pause(Integer count) {
        try {
            Thread.sleep(count * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
