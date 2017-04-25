import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestRookAndBoard.class, TestKnightAndBoard.class, TestPawnAndBoard.class })
public class IntegrationTestSuite {

}
