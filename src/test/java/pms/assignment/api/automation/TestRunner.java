package pms.assignment.api.automation;

import org.junit.runner.RunWith;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;


@RunWith(Karate.class)
@KarateOptions(
		
		features = {
				".\\src\\test\\java\\pms\\assignment\\api\\automation\\GoogleDriveAPITest.feature"
				}
		
		)
public class TestRunner {

}
