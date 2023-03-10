package FlaNium.WinAPI.property;

import FlaNium.WinAPI.exceptions.PropertyLoadException;

import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyLoader {

    private static final String DEFAULT_DRIVER_PROPERTY = "flanium_driver.properties";
    private static final String DEFAULT_APP_PROPERTY = "flanium_app.properties";

    private static Properties loadPropertiesFromFile(String propertyName) {

        try {
            Properties properties = new Properties();

            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyName));

            if (properties.isEmpty()) throw new Exception();

            return properties;
        } catch (Exception e) {
            throw new PropertyLoadException("Can not load properties from file: " + propertyName);
        }

    }

    private static void loadDriverProperties(String driverProperty) {
        Properties fileProp = loadPropertiesFromFile(driverProperty);

        String wrongProperties = fileProp.keySet().stream()
                .map(Object::toString)
                .filter(o -> !PropertyList.Driver.containsValue(o))
                .collect(Collectors.joining(", "));

        if (!wrongProperties.isEmpty())
            throw new PropertyLoadException(String.format("Incorrect Driver properties found in file '%s' : %s", driverProperty, wrongProperties));


        fileProp.forEach((k, v) -> {
            if(!System.getProperties().containsKey(k.toString())) System.setProperty(k.toString(), v.toString());
        });
    }

    private static void loadAppProperties(String appProperty) {
        Properties fileProp = loadPropertiesFromFile(appProperty);

        String wrongProperties = fileProp.keySet().stream()
                .map(Object::toString)
                .filter(o -> !PropertyList.App.containsValue(o))
                .collect(Collectors.joining(", "));

        if (!wrongProperties.isEmpty())
            throw new PropertyLoadException(String.format("Incorrect App properties found in file '%s' : %s", appProperty, wrongProperties));


        fileProp.forEach((k, v) -> {
            if(!System.getProperties().containsKey(k.toString())) System.setProperty(k.toString(), v.toString());
        });
    }

    public static void loadProperties(){
        loadDriverProperties(DEFAULT_DRIVER_PROPERTY);
        loadAppProperties(DEFAULT_APP_PROPERTY);
    }

    public static void loadProperties(String appProperty){
        loadDriverProperties(DEFAULT_DRIVER_PROPERTY);
        loadAppProperties(appProperty);
    }

}
