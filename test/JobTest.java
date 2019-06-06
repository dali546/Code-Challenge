import com.example.Job;
import com.example.JobDependencyException;
import com.example.JobDependencyLoopException;
import com.example.Main;
import org.junit.Assert;
import org.junit.Test;

public class JobTest {

    @Test
    public void EmptyResponseTest() throws JobDependencyException {
        // Arrange, Act, Assert
        Job[] jobs = new Job[0]; // Empty Job Array

        // Run Job with Empty Job Array
        String response = Main.runJobs(jobs);

        // Expect Empty String to be returned
        Assert.assertEquals(response, "");
    }

    @Test
    public void SingleJobResponseTest() throws JobDependencyException {
        Job[] jobs = new Job[]{new Job("a")}; // Single Item Job Array

        String response = Main.runJobs(jobs);

        Assert.assertEquals(response, "a");
    }

    @Test
    public void RunMultipleJobsTest() throws JobDependencyException {
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
    public void RunJobsInSpecifiedOrderTest() throws JobDependencyException {
        Job a = new Job("a");
        Job c = new Job("c");
        Job b = new Job("b", c);
        Job[] jobs = new Job[]{a, b, c}; // Add Jobs to array of jobs

        String response = Main.runJobs(jobs);

        Assert.assertTrue(response.contains("a"));
        Assert.assertTrue(response.contains("cb")); // Specified Order
    }

    @Test
    public void MoreComplicatedJobDependencyOrderTest() throws JobDependencyException {
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

    @Test(expected = JobDependencyException.class)
    public void ErrorOnSelfDependentJobsTest() throws JobDependencyException {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c", new Job("c")); // Job dependent on Job "c"
        Job[] jobs = new Job[]{a, b, c}; // Add Jobs to array of jobs

        Main.runJobs(jobs); // Expected JobDependencyException

    }

    @Test(expected = JobDependencyLoopException.class)
    public void ErrorOnLoopingDependeciesTest() throws JobDependencyException {
        Job a = new Job("a");
        Job b = new Job("b", new Job("c")); // Cannot compile with variable c.
        Job f = new Job("f", b);
        Job c = new Job("c", f); // Job dependent on Job "c"
        Job d = new Job("d", a);
        Job e = new Job("e");
        Job[] jobs = new Job[]{a, b, c, d, e, f}; // Add Jobs to array of jobs

        Main.runJobs(jobs); // Expected JobDependencyLoopException
    }
}
