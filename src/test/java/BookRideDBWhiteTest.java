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
import testOperations.TestDataAccess;
import domain.Driver;

public class BookRideDBWhiteTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver; 

	
	@Test
	//sut.createRide:  The Driver is null. The test must return null. If  an Exception is returned the createRide method is not well implemented.
		public void test1() {
		String driverUsername = "Driver Test";
        String driverPass = "";
        String tname="Julen";
        String rideFrom = "Donostia";
        String rideTo = "Zarautz";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date rideDate = null;
		try {
			rideDate = sdf.parse("05/10/2026");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int a;
        
        testDA.open();
        Driver dri= testDA.createDriver(driverUsername, driverPass);
        Ride ride =testDA.createRide(rideFrom, rideTo, rideDate, 100, 20.5, dri);
        testDA.createTraveler("Julen", "0000");
        testDA.addDirua2("Julen",50.0);
        a=testDA.getkont();
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 3, 5.0);
			sut.close();			
			
			//verify the results
			assertEquals(a,testDA.getkont());

			
		   }  catch (Exception e) {
			fail();
			}finally{
				testDA.open();
				testDA.removeDriver(driverUsername);
				testDA.removeTraveler(tname);
				testDA.removeRide(driverUsername,rideFrom, rideTo, rideDate);

				testDA.close();
			}
	}
	@Test
	//sut.createRide:  The Driver("Driver Test") does not exist in the DB.
	public void test2() {
		String driverUsername = "Driver Test";
        String driverPass = "";
        
        String rideFrom = "Donostia";
        String rideTo = "Zarautz";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date rideDate = null;
		try {
			rideDate = sdf.parse("05/10/2026");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
        testDA.open();
        Driver dri= testDA.createDriver(driverUsername, driverPass);
        Ride ride =testDA.createRide(rideFrom, rideTo, rideDate, 100, 20.5, dri);
        
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 3, 5.0);
			sut.close();			
			
			//verify the results
			assertFalse(emaitza);

			
		   }  catch (Exception e) {
			fail();
			}finally{
				testDA.open();
				testDA.removeDriver(driverUsername);
				
				testDA.removeRide(driverUsername,rideFrom, rideTo, rideDate);

				testDA.close();
			}
		   } 
	@Test
	//User existe en DB baino ez daude leku nahikoak 
	public void test3() {
		String driverUsername = "Driver Test";
        String driverPass = "";
        String tname="Julen";
        String rideFrom = "Donostia";
        String rideTo = "Zarautz";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date rideDate = null;
		try {
			rideDate = sdf.parse("05/10/2026");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        testDA.open();
        Driver dri= testDA.createDriver(driverUsername, driverPass);
        Ride ride =testDA.createRide(rideFrom, rideTo, rideDate, 1, 20.5, dri);
        testDA.createTraveler("Julen", "0000");
        testDA.addDirua2("Julen",50.0);
        
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 3, 5.0);
			sut.close();			
			
			//verify the results
			assertFalse(emaitza);

			
		   }  catch (Exception e) {
			fail();
			}finally{
				testDA.open();
				testDA.removeDriver(driverUsername);
				testDA.removeTraveler(tname);
				testDA.removeRide(driverUsername,rideFrom, rideTo, rideDate);

				testDA.close();
			}
	} 


	@Test
	//Erabiltzaileak ez du diru nahikoa
	public void test4() {
		String driverUsername = "Driver Test";
        String driverPass = "";
        String tname="Julen";
        String rideFrom = "Donostia";
        String rideTo = "Zarautz";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date rideDate = null;
		try {
			rideDate = sdf.parse("05/10/2026");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        testDA.open();
        Driver dri= testDA.createDriver(driverUsername, driverPass);
        Ride ride =testDA.createRide(rideFrom, rideTo, rideDate, 100, 20.5, dri);
        testDA.createTraveler("Julen", "0000");
        testDA.addDirua2("Julen",1.0);
     
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 3, 0);
			sut.close();			
			
			//verify the results
			assertFalse(emaitza);

			
		   }  catch (Exception e) {
			fail();
			}finally{
				testDA.open();
				testDA.removeDriver(driverUsername);
				testDA.removeTraveler(tname);
				testDA.removeRide(driverUsername,rideFrom, rideTo, rideDate);

				testDA.close();
			}
	       } 


	@Test
	//sut.createRide:  The Driver("Driver Test") HAS  NOT one ride "from" "to" in that "date". 
	// and the Ride must be created in DB
	//The test supposes that the "Driver Test" does not exist in the DB before the test

	public void test5() {
		String driverUsername = "Driver Test";
        String driverPass = "";
        String tname="Julen";
        String rideFrom = "Donostia";
        String rideTo = "Zarautz";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date rideDate = null;
		try {
			rideDate = sdf.parse("05/10/2026");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        testDA.open();
        Driver dri= testDA.createDriver(driverUsername, driverPass);
        Ride ride =testDA.createRide(rideFrom, rideTo, rideDate, 100, 20.5, dri);
        testDA.createTraveler("Julen", "0000");
        testDA.addDirua2("Julen",100.0);
        
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 1, 5.0);
			sut.close();			
			
			//verify the results
			assertTrue(emaitza);

			
		   }  catch (Exception e) {
			fail();
			}finally{
				testDA.open();
				testDA.removeDriver(driverUsername);
				testDA.removeTraveler(tname);
				testDA.removeRide(driverUsername,rideFrom, rideTo, rideDate);

				testDA.close();
			}
	}
}
