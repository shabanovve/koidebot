package ko.ide.bot.telegram;

import ko.ide.bot.telegram.resource.LibResource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
public class NativeLibsLoader {

    private final LibResource libResource;

    public void load() {
        //noinspection Java8MapForEach
        libResource.getResourceContext().entrySet()
                .forEach(entry -> {
                            String libName = entry.getKey();
                            System.out.print("Loading lib with name " + libName);
                            try {
                                File nativeLibTmpFile = createTmpFile(
                                        libName,
                                        libResource.getResourceContext().get(libName)
                                );
                                System.load(nativeLibTmpFile.getAbsolutePath());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }

    private File createTmpFile(String libName, Resource resourceFile) throws IOException {
        File tmpDir = Files.createTempDirectory("ttt").toFile();
        tmpDir.deleteOnExit();
        File nativeLibTmpFile = new File(tmpDir, libName);
        nativeLibTmpFile.deleteOnExit();
        try (InputStream in = resourceFile.getInputStream()) {
            Files.copy(in, nativeLibTmpFile.toPath());
        }
        return nativeLibTmpFile;
    }
}
