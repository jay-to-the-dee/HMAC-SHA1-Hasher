import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jonathan
 */
public class HasherMainTest
{

    public HasherMainTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void getHashEmptyMessageAndEmptyKeyCheck()
    {
        System.out.println("* getHashJUnit4Test: getHashEmptyMessageAndEmptyKeyCheck()");
        try
        {
            assertEquals("fbdb1d1b18aa6c08324b7d64b71fb76370690e1d", HasherMain.getHash("", ""));
        }
        catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException ex)
        {
            fail(ex.toString());
        }
    }

    @Test
    public void getHashKeyAndMessageQuickBrownFox()
    {
        System.out.println("* getHashJUnit4Test: getHashKeyAndMessageQuickBrownFox()");
        try
        {
            assertEquals("de7c9b85b8b78aa6bc8a7a36f70a90701c9db4d9", HasherMain.getHash("key", "The quick brown fox jumps over the lazy dog"));
        }
        catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException ex)
        {
            fail(ex.toString());
        }
    }

    /**
     * Test of main method, of class HasherMain.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        try
        {
            HasherMain.main(args);
        }
        catch (Exception ex)
        {
            fail("Exception: " + ex);
        }
    }

}
