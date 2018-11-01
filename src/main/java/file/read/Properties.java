package file.read;

import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2214:28
 */
public class Properties {
    private static PropertiesConfiguration properites;

    public static PropertiesConfiguration getPropertiesConfig(){
        if(properites == null) {
            try {
                properites = new Configurations().properties("application.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
        return properites;
    }
}
