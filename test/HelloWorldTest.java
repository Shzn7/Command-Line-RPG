import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    /*
        You can use the tests provided in each lab (like the one below) to check your implementation.

        We advise however, that you take the time to write additional tests to increase the confidence of your
        implementation.
     */

    @Test(timeout = 1000)
    public void helloTest() {
        assertEquals(HelloWorld.hello("Shaazaan"), "Hello Shaazaan!");
    }
}