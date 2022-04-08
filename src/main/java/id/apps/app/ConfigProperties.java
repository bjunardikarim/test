package id.apps.app;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ConfigProperties {
	
	public static String FILEUPLOAD;
	
	@Value("${fileupload}")
	public void setKey(String name) {
		ConfigProperties.FILEUPLOAD = name;
	}
		
}
