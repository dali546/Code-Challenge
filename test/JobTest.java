import com.example.Main;
import org.junit.Assert;
import org.junit.Test;

public class JobTest {

    @Test
    public void EmptyResponseTest() {
        // Arrange, Act, Assert
        String[] jobs = new String[0]; // Empty Job List

        // Run Job with Empty Job List
        String response = Main.runJobs(jobs);

        // Expect Empty String to be returned
        Assert.assertEquals(response, "");
    }
}
