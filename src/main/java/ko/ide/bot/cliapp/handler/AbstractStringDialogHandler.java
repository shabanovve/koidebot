package ko.ide.bot.cliapp.handler;

import ko.ide.bot.util.PasswordReader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
@Log4j2
public abstract class AbstractStringDialogHandler {
    private final ReentrantLock lock = new ReentrantLock();

    protected void handle(
            String title, Consumer<AtomicReference<String>> changeStateConsumer,
            CountDownLatch latch
    ) {
        handle(title, changeStateConsumer, latch, false);
    }
    @SneakyThrows
    protected void handle(
            String title, Consumer<AtomicReference<String>> changeStateConsumer,
            CountDownLatch latch, boolean isPassword
    ) {
        if (!lock.tryLock()) {
            log.info("Skip showing dialog " + title);
            return; //should not handle dialog event multiple times
        }

        try {
            AtomicReference<String> result = new AtomicReference<>();
            Scanner scanner = new Scanner(System.in);
            System.out.println("=== " + title);
            if (isPassword) {
                result.set(PasswordReader.read());
            } else {
                result.set(scanner.nextLine());
            }
            changeStateConsumer.accept(result);
        } finally {
            latch.countDown();
            lock.unlock();
        }
    }
}
