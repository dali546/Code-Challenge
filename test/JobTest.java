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

        Assert.assertEquals(response, "a");
    }

    @Test
    public void RunMultipleJobsTest() {
        String[] jobs = new String[]{"a", "b", "c"};

        String response = Main.runJobs(jobs);

        Assert.assertTrue(response.contains("a"));
        Assert.assertTrue(response.contains("b"));
        Assert.assertTrue(response.contains("c"));
        Assert.assertEquals(response.length(), jobs.length);
    }
}
