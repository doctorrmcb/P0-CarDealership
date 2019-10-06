package com.revature.test.version;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.util.ConnectionFactory;

public class JDBCVersionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVersion() throws Exception {
	  try (Connection connection = ConnectionFactory.getConnection()) {
	    DatabaseMetaData metaData = connection.getMetaData();
	    System.out.println(metaData.getJDBCMajorVersion());
	    System.out.println(metaData.getJDBCMinorVersion());
	    assertEquals(4, metaData.getJDBCMajorVersion());
	    assertEquals(2, metaData.getJDBCMinorVersion());
	  }
	}

}
