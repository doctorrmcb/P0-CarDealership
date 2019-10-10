import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
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
import com.revature.util.ConnectionFactory;

@RunWith(Suite.class)
@SuiteClasses({CarSystemImplTest.class, AccountDAOTest.class, CarDAOTest.class, OfferDAOTest.class, PaymentDAOTest.class, DisplayImplTest.class})
public class AllTests {
	//JDBCVersionTest.class,
	
	@AfterClass
    public static void tearDown() {
        System.out.println("tearing down");
        Connection conn = ConnectionFactory.getConnection();
        try {
			PreparedStatement call = conn.prepareCall("call delete_test_data();");
			call.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
