package ko.ide.bot.util;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
public class PasswordReader {
    public static String read() {
        Console console = System.console();
        if (console == null) {
            log.info("Cannot get java.io.console use alternative reader");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new String(console.readPassword());
        }
    }
}
