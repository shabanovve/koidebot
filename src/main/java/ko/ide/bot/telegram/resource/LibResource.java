package ko.ide.bot.telegram.resource;


import org.springframework.core.io.Resource;

import java.util.LinkedHashMap;

public interface LibResource {
    LinkedHashMap<String, Resource> getResourceContext();
}
