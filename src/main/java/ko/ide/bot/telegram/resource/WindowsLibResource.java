package ko.ide.bot.telegram.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@SuppressWarnings("ALL")
@Component
@Conditional(OnWindowsCondition.class)
public class WindowsLibResource implements LibResource {

    @Value("classpath:/libcrypto-1_1-x64.dll")
    private Resource libcryptoResource;

    @Value("classpath:/libssl-1_1-x64.dll")
    private Resource libsslResource;
    @Value("classpath:/zlib1.dll")
    private Resource zlibResource;
    @Value("classpath:/tdjni.dll")
    private Resource tdjniResource;

    @Override
    public LinkedHashMap<String, Resource> getResourceContext() {
        LinkedHashMap<String, Resource> map = new LinkedHashMap<>();
        map.put("libcrypto-1_1-x64.dll", libcryptoResource);
        map.put("libssl-1_1-x64.dll", libsslResource);
        map.put("zlib1.dll", zlibResource);
        map.put("tdjni.dll", tdjniResource);
        return map;
    }
}
