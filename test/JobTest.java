import com.example.Job;
import com.example.Main;
import org.junit.Assert;
import org.junit.Test;

public class JobTest {

    @Test
    public void EmptyResponseTest() {
        // Arrange, Act, Assert
        Job[] jobs = new Job[0]; // Empty Job Array

        // Run Job with Empty Job Array
        String response = Main.runJobs(jobs);

        // Expect Empty String to be returned
        Assert.assertEquals(response, "");
    }

    @Test
    public void SingleJobResponseTest() {
        Job[] jobs = new Job[]{new Job("a")}; // Single Item Job Array

        String response = Main.runJobs(jobs);

        Assert.assertEquals(response, "a");
    }

    @Test
    public void RunMultipleJobsTest() {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");
        Job[] jobs = new Job[]{a, b, c};

        String response = Main.runJobs(jobs);

        Assert.assertTrue(response.contains("a"));
        Assert.assertTrue(response.contains("b"));
        Assert.assertTrue(response.contains("c"));
        Assert.assertEquals(response.length(), jobs.length);
    }

    @Test
    public void RunJobsInSpecifiedOrder() {
        Job a = new Job("a");
        Job c = new Job("c");
        Job b = new Job("b", c);
        Job[] jobs = new Job[]{a, b, c}; // Add Jobs to array of jobs

        String response = Main.runJobs(jobs);

        Assert.assertTrue(response.contains("a"));
        Assert.assertTrue(response.contains("cb")); // Specified Order
    }

    @Test
    public void MoreComplicatedJobDependencyOrder() {
        Job a = new Job("a");
        Job f = new Job("f");
        Job c = new Job("c", f);
        Job b = new Job("b", c);
        Job d = new Job("d", a);
        Job e = new Job("e", b);
        Job[] jobs = new Job[]{a, b, c, d, e, f}; // Add Jobs to array of jobs

        String response = Main.runJobs(jobs);

        Assert.assertTrue(response.contains("a"));
        Assert.assertTrue(response.contains("f"));
        Assert.assertTrue(response.contains("fc")); // Specified Order
        Assert.assertTrue(response.contains("cb")); // Specified Order
        Assert.assertTrue(response.contains("be")); // Specified Order
        Assert.assertTrue(response.contains("fcbe")); // Specified Order
    }
}
