import com.example.Main;
import org.junit.Assert;
import org.junit.Test;

public class JobTest {

    @Test
    public void EmptyResponseTest() {
        // Arrange, Act, Assert
        String[] jobs = new String[0]; // Empty Job Array

        // Run Job with Empty Job Array
        String response = Main.runJobs(jobs);

        // Expect Empty String to be returned
        Assert.assertEquals(response, "");
    }

    @Test
    public void SingleJobResponseTest() {
        String[] jobs = new String[]{"a"}; // Single Item Job Array

        String response = Main.runJobs(jobs);
        
        Assert.assertEquals(response,"a");
    }
}
