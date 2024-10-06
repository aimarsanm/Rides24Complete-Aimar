package testOperations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Ride;
import domain.Driver;

public class GauzatuEragiketaDBBlackTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver; 

	@Test
	//sut.createRide:Username null denean

	public void test1() {
		String username="Bidaiari";
		double amount = 50.0;
		boolean deposit = true;
	
		try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.gauzatuEragiketa(null, amount, deposit);
			sut.close();			
			
			//verify the results
			assertFalse(emaitza);

			
		   }  catch (Exception e) {
			fail();
			}
		
		   }
	@Test
	//sut.createRide:deposit null denean

	public void test2() {
		String username="Bidaiari";
		Double amount = null;
		boolean deposit = true;
		
		testDA.open();
		
		testDA.createUser(username,"123", "bidaiaria");
	
		testDA.close();
	
		

	
		try {
			//invoke System Under Test (sut)  
			sut.open();
			sut.gauzatuEragiketa(username, amount, deposit);
			sut.close();			
			fail();
			//verify the results
			
		   }  catch (Exception e) {
			   assertTrue(true);
			}finally {   

				testDA.open();
					testDA.removeUser(username);
				testDA.close();
				
			        }
			   }
		
		
	@Test
	//sut.createRide:deposit null denean

	public void test3() {
		String username="Bidaiari";
		double amount = 50.0;
		boolean deposit = true;
		
		testDA.open();
		
		testDA.createUser(username,"123", "bidaiaria");
	
		testDA.close();
	
		

	
		try {
			//invoke System Under Test (sut)  
			sut.open();
			sut.gauzatuEragiketa(username, amount, (Boolean) null);
			sut.close();			
			fail();
			//verify the results
			
		   }  catch (Exception e) {
			   assertTrue(true);
			}finally {   

				testDA.open();
					testDA.removeUser(username);
				testDA.close();
				
			        }
			   }
		
		   
	
	
	@Test
	//sut.createRide:user null denean

	public void test4() {
		String username="Bidaiari";
		double amount = 50.0;
		boolean deposit = true;
		
		
		  testDA.open();
		  testDA.createUser(username,"123", "bidaiaria");
		    
		    if (testDA.existUser(username)) { 
		        testDA.removeUser(username); 
		    }
		    
		    testDA.close();

	
		try {
			//invoke System Under Test (sut)  
			sut.open();
			boolean ema= sut.gauzatuEragiketa(username, amount, deposit);
			sut.close();			
			
			//verify the results
			
			assertFalse(ema);
			
		   }  catch (Exception e) {
			   
			   fail();
			   }
	}
		   
	
	
	@Test
	//amount positiboa izan behar da

	public void test5() {
		String username="Bidaiari";
		double amount = 50.0;
		boolean deposit = true;
	
		
		testDA.open();
		
		testDA.createUser(username,"123", "bidaiaria");
	
		testDA.close();
	
		try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.gauzatuEragiketa(username, amount, deposit);
			sut.close();			
			
			//verify the results
			assertTrue(emaitza);
			
		   }  catch (Exception e) {
			fail();
			   }finally {   

					testDA.open();
						testDA.removeUser(username);
					testDA.close();
					
				        }
			}
	
	
}

