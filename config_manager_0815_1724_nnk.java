// 代码生成时间: 2025-08-15 17:24:03
import io.smallrye.config.ConfigSource;
import io.smallrye.config.ConfigSourceContext;
import io.smallrye.config.ConfigSourceFactory;
import io.smallrye.config.ConfigValue;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * ConfigManager class for managing configuration files using Quarkus framework.
 */
@ApplicationScoped
public class ConfigManager {

    @Inject
    @Named("configSourceFactory")
    private ConfigSourceFactory configSourceFactory;

    /**
     * Load configuration values from a specific source.
     *
     * @param sourceName The name of the configuration source.
     * @return A map of configuration values.
     */
    public Map<String, String> loadConfig(String sourceName) {
        ConfigSourceContext context = new ConfigSourceContext() {
            @Override
            public Map<String, String> getProperties() {
                Map<String, String> properties = new HashMap<>();
                properties.put("sources", sourceName);
                return properties;
            }
        };
        ConfigSource configSource = configSourceFactory.createConfigSource(context);

        if (configSource == null) {
            throw new RuntimeException("Failed to load configuration source: " + sourceName);
        }

        Map<String, String> configValues = new HashMap<>();
        for (ConfigValue configValue : configSource.getValues()) {
            configValues.put(configValue.getKey(), configValue.getValue());
        }
        return configValues;
    }
}
