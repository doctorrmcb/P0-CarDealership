import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.revature.test.dao.AccountDAOTest;
import com.revature.test.dao.CarDAOTest;
import com.revature.test.dao.OfferDAOTest;
import com.revature.test.dao.PaymentDAOTest;
import com.revature.test.service.CarSystemImplTest;
import com.revature.test.service.DisplayImplTest;
import com.revature.test.version.JDBCVersionTest;

@RunWith(Suite.class)
@SuiteClasses({CarSystemImplTest.class, AccountDAOTest.class, CarDAOTest.class, OfferDAOTest.class, PaymentDAOTest.class, DisplayImplTest.class})
public class AllTests {
	//JDBCVersionTest.class,
}
