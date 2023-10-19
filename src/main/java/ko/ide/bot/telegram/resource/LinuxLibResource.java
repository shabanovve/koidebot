package ko.ide.bot.telegram.resource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@SuppressWarnings("unused")
@Component
@Conditional(OnLinuxCondition.class)
public class LinuxLibResource implements LibResource {
    @SuppressWarnings("SpellCheckingInspection")
    @Value("classpath:/libtdjni.so")
    private Resource libtdjniResoure;

    public LinkedHashMap<String, Resource> getResourceContext() {
        LinkedHashMap<String, Resource> map = new LinkedHashMap<>();
        map.put("libtdjni.so", libtdjniResoure);
        return map;
    }
}
