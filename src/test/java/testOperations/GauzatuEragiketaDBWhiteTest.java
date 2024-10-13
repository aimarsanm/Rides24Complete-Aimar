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
import domain.User;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import domain.Driver;

public class GauzatuEragiketaDBWhiteTest {

	 
	 static DataAccess sut=new DataAccess();
	 
	  
	 static TestDataAccess testDA=new TestDataAccess();

	
	 @SuppressWarnings("unused")
		private Driver driver; 
	
	@Test
	
		public void test1() {
		String username="Bidaiari";
		double amount = 50.0;
		boolean deposit = true;
		int a;
		
		testDA.open();
		
		testDA.createUser(username,"123", "bidaiaria");
		a=testDA.getkont();
		testDA.close();
	
		try {
			  
			sut.open();
			 boolean emaitza=sut.gauzatuEragiketa(username, amount, deposit);
			sut.close();			
			
			
			
			assertEquals(a,testDA.getkont());
			
		   }  catch (Exception e) {
			fail();
			   }finally {   

					testDA.open();
						testDA.removeUser(username);
					testDA.close();
					
			   }
	}
					   
	@Test
	
	public void test2() {
		
		String username = "Proba1";
        double depositAmount = 50.0;
        boolean isDeposit = true;

        
        testDA.open();
        User user = testDA.createUser(username, "password", "bidaiaria");
        testDA.removeUser(username);
        testDA.close();

        
        try {
            sut.open();
            boolean result = sut.gauzatuEragiketa(username, depositAmount, isDeposit);
            sut.close();

            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        } 
		   } 
	
	@Test
	
	public void test3() {
		 String username = "Prueba";
	        double withdrawAmount = 25.0;
	        boolean isDeposit = true; 

	       
	        testDA.open();
	        User user = testDA.createUser(username, "password", "bidaiaria");
	        testDA.close();

	        
	        try {
	            sut.open();
	            sut.gauzatuEragiketa(username, withdrawAmount, isDeposit);
	            sut.close();
	            
	            testDA.open();
	            User updatedUser = testDA.getUser(username);  // Obtén el usuario actualizado desde la BD
	            testDA.close();
	            
	            if(25.0==updatedUser.getMoney()) {assertTrue(true);}else {assertTrue(false);}

	        } catch (Exception e) {
	            fail();
	        } finally {
	            testDA.open();
	            testDA.removeUser(username);
	            testDA.close();
	        }
		   } 

	@Test
	
	public void test4() {
		 String username = "Prueba";
	        double withdrawAmount = 25.0;
	        boolean isDeposit = false; 

	       
	        testDA.open();
	        User user = testDA.createUser(username, "password", "bidaiaria");
	        testDA.close();

	        
	        try {
	            sut.open();
	            sut.gauzatuEragiketa(username, withdrawAmount, isDeposit);
	            sut.close();
	            
	            testDA.open();
	            User updatedUser = testDA.getUser(username);  // Obtén el usuario actualizado desde la BD
	            testDA.close();
	            
	            if(0.0==updatedUser.getMoney()) {assertTrue(true);}else {assertTrue(false);}

	        } catch (Exception e) {
	            fail();
	        } finally {
	            testDA.open();
	            testDA.removeUser(username);
	            testDA.close();
	        }
		   } 
	
@Test
	
	public void test5() {
		 String username = "Prueba";
	        double withdrawAmount = 25.0;
	        boolean isDeposit = false; 

	        
	        testDA.open();
	        User user = testDA.createUser(username, "password", "bidaiaria");
	        testDA.addDirua(username, 50.0);
	        testDA.close();

	       
	        try {
	            sut.open();
	            sut.gauzatuEragiketa(username, withdrawAmount, isDeposit);
	            sut.close();
	            
	            testDA.open();
	            User updatedUser = testDA.getUser(username); 
	            testDA.close();
	            
	            if(25.0==updatedUser.getMoney()) {assertTrue(true);}else {assertTrue(false);}

	        } catch (Exception e) {
	            fail();
	        } finally {
	            testDA.open();
	            testDA.removeUser(username);
	            testDA.close();
	        }
		   } 
	
	
		   
}
