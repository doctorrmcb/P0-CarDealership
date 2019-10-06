import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.revature.test.service.CarSystemImplTest;
import com.revature.test.version.JDBCVersionTest;

@RunWith(Suite.class)
@SuiteClasses({CarSystemImplTest.class, JDBCVersionTest.class})
public class AllTests {

}
