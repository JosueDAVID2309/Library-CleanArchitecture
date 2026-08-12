package integration.configuration;

import com.mycompany.libraryproject.infrastructure.configuration.Config;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigTest {
    
    @Test
    void shouldFindApplicationProperties() {

        InputStream input = Config.class
                .getClassLoader()
                .getResourceAsStream("application.properties");

        assertNotNull(input);
    }
    
    @Test
    void shouldReturnProperties(){
        
        assertNotNull(Config.get("db.user"));
        System.out.println(Config.get("db.user"));
    }
}
