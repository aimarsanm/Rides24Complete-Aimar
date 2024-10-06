package testOperations;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Traveler;

public class BookRideDBBlackTest {
	
	 static DataAccess sut=new DataAccess();
	 
	  
	 static TestDataAccess testDA=new TestDataAccess();
	 @SuppressWarnings("unused")
		private Driver driver; 
	@Test
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
        
        testDA.open();
        Driver dri= testDA.createDriver(driverUsername, driverPass);
        Ride ride =testDA.createRide(rideFrom, rideTo, rideDate, 100, 20.5, dri);
        testDA.createTraveler("Julen", "0000");
        testDA.addDirua2("Julen",50.0);
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 3, 5.0);
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
	@Test
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
				 boolean emaitza=sut.bookRide(null, ride, 3, 5.0);
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
	public void test3() {
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
		testDA.createTraveler("Julen","0000");
        testDA.close();
		 try {
				//invoke System Under Test (sut)  
				sut.open();
				 boolean emaitza=sut.bookRide("Julen", null, 3, 5.0);
				sut.close();			
				
				//verify the results
				assertFalse(emaitza);

				
			   }  catch (Exception e) {
				fail();
				}finally{
					testDA.open();
					testDA.removeTraveler("Julen");
					testDA.close();
				}
	}
	@Test
	public void test4() {
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
		Ride ride= testDA.createRide(rideFrom, rideTo, rideDate, 100, 10.5, dri);
		testDA.createTraveler("Julen","0000");
        testDA.close();
		 try {
				//invoke System Under Test (sut)  
				sut.open();
				sut.bookRide("Julen", ride, (Integer)null, 5.0);
				sut.close();			
				
				//verify the results
				
				fail();
				
			   }  catch (Exception e) {
				   assertTrue(true);
				}finally{
					testDA.open();
					testDA.removeDriver(driverUsername);
					testDA.removeRide(driverUsername, rideFrom, rideTo, rideDate);
					testDA.removeTraveler("Julen");
					testDA.close();
				}
	}
	@Test
	public void test5() {
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
		Ride ride= testDA.createRide(rideFrom, rideTo, rideDate, 100, 10.5, dri);
		testDA.createTraveler("Julen","0000");
        testDA.close();
		 try {
				//invoke System Under Test (sut)  
				sut.open();
				sut.bookRide("Julen", ride, 3, (Double)null);
				sut.close();			
				
				//verify the results
				
				fail();
				
			   }  catch (Exception e) {
				   assertTrue(true);
				}finally{
					testDA.open();
					testDA.removeDriver(driverUsername);
					testDA.removeRide(driverUsername, rideFrom, rideTo, rideDate);
					testDA.removeTraveler("Julen");
					testDA.close();
				}
	}
	
	@Test
	public void test6() {
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
		Ride ride= testDA.createRide(rideFrom, rideTo, rideDate, 100, 10.5, dri);
        testDA.close();
		 try {
				//invoke System Under Test (sut)  
				sut.open();
				boolean ema= sut.bookRide("Julen", ride, 3, 5.0);
				sut.close();			
				
				//verify the results
				
				
				assertFalse(ema);
			   }  catch (Exception e) {
				   fail();
				}finally{
					testDA.open();
					testDA.removeDriver(driverUsername);
					testDA.removeRide(driverUsername, rideFrom, rideTo, rideDate);
					testDA.close();
				}
	}
	@Test
	public void test7() {
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
	public void test8() {
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
        testDA.addDirua2("Julen",50.0);
        testDA.close();
        try {
			//invoke System Under Test (sut)  
			sut.open();
			 boolean emaitza=sut.bookRide("Julen", ride, 10, 0.0);
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

}
