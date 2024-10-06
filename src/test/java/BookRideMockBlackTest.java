import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Traveler;
import domain.User;


public class BookRideMockBlackTest {

    

	static DataAccess sut;
    
    protected MockedStatic<Persistence> persistenceMock;
    @Mock
    protected EntityManager db;
    @Mock
    protected EntityTransaction et;
    
    @Mock
	protected  EntityManagerFactory entityManagerFactory;
    
    @Mock
	TypedQuery<Traveler> typedQuery;
    
    @Before
    public void init() {
    	MockitoAnnotations.openMocks(this);
        persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() -> Persistence.createEntityManagerFactory(Mockito.any()))
        .thenReturn(entityManagerFactory);
        
        Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccess(db);
    }
    
	@After
    public  void tearDown() {
		persistenceMock.close();
    }

	@Test
	public void test1() {
		try {
            String driverUsername = "Driver Test";
            String driverPass = "";
            Driver dri = new Driver(driverUsername, driverPass);
            String rideFrom = "Donostia";
            String rideTo = "Zarautz";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date rideDate = sdf.parse("05/10/2026");
            Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
            Traveler tra = new Traveler("Julen", "0000");
            tra.setMoney(50);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
            
            sut.open();
            boolean result = sut.bookRide("Julen", ride, 3, 5.0);
            sut.close();

            
            assertTrue(result);
        } catch (Exception e) {
            fail();
        }
	}
	@Test
	public void test2(){
		try {
            String driverUsername = "Driver Test";
            String driverPass = "";
            Driver dri = new Driver(driverUsername, driverPass);
            
            String rideFrom = "Donostia";
            String rideTo = "Zarautz";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date rideDate = sdf.parse("05/10/2026");
            Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
           
            
            
            List<Traveler> travelers = new ArrayList<>();
           
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
            
            sut.open();
            boolean result = sut.bookRide(null, ride, 3, 5.0);
            sut.close();

            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	
	@Test
	public void test3() {
		try {
            
           
            Traveler tra = new Traveler("Julen", "0000");
            tra.setMoney(50);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
    		 boolean result;
            sut.open();
            try {
            result= sut.bookRide("Julen", null, 3, 5.0);
            }catch(Exception e){
            	result=false;
            }            
            sut.close();

            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	@Test
	public void test4() {
		try {
            
			 	String driverUsername = "Driver Test";
	            String driverPass = "";
	            Driver dri = new Driver(driverUsername, driverPass);
	            
	            String rideFrom = "Donostia";
	            String rideTo = "Zarautz";
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            Date rideDate = sdf.parse("05/10/2026");
	            Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
	            
            Traveler tra = new Traveler("Julen", "0000");
            tra.setMoney(50);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
            boolean result;
            sut.open();
            try {
                result = sut.bookRide("Julen", ride, (Integer)null, 5.0);
            } catch (Exception e) {
                
                result = false;
            }
            sut.close();
            
            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	@Test
	public void test5() {
		try {
            
			 	String driverUsername = "Driver Test";
	            String driverPass = "";
	            Driver dri = new Driver(driverUsername, driverPass);
	            
	            String rideFrom = "Donostia";
	            String rideTo = "Zarautz";
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            Date rideDate = sdf.parse("05/10/2026");
	            Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
	            
            Traveler tra = new Traveler("Julen", "0000");
            tra.setMoney(50);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
            boolean result;
            sut.open();
            try {
                result = sut.bookRide("Julen", ride, 3,(Double)null);
            } catch (Exception e) {
                
                result = false;
            }
            sut.close();
            
            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	@Test
	public void test6() {
		try {
            String driverUsername = "Driver Test";
            String driverPass = "";
            Driver dri = new Driver(driverUsername, driverPass);
            String rideFrom = "Donostia";
            String rideTo = "Zarautz";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date rideDate = sdf.parse("05/10/2026");
            Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
            Traveler tra = new Traveler("Mikel", "0000");
            tra.setMoney(50);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(new ArrayList<Traveler>());
    		
            
            sut.open();
            boolean result = sut.bookRide("Julen", ride, 3, 5.0);
            sut.close();

            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	@Test
	public void test7() {
		try {
            String driverUsername = "Driver Test";
            String driverPass = "";
            Driver dri = new Driver(driverUsername, driverPass);
            String rideFrom = "Donostia";
            String rideTo = "Zarautz";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date rideDate = sdf.parse("05/10/2026");
            Ride ride = new Ride(rideFrom, rideTo, rideDate, 1, 20.5, dri);
            Traveler tra = new Traveler("Julen", "0000");
            tra.setMoney(50);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
            
            sut.open();
            boolean result = sut.bookRide("Julen", ride, 3, 5.0);
            sut.close();

            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	@Test
	public void test8() {
		try {
            String driverUsername = "Driver Test";
            String driverPass = "";
            Driver dri = new Driver(driverUsername, driverPass);
            String rideFrom = "Donostia";
            String rideTo = "Zarautz";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date rideDate = sdf.parse("05/10/2026");
            Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
            Traveler tra = new Traveler("Julen", "0000");
            tra.setMoney(10);
            
            List<Traveler> travelers = new ArrayList<>();
            travelers.add(tra);
            
            Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		Mockito.when(typedQuery.getResultList()).thenReturn(travelers);
    		
            
            sut.open();
            boolean result = sut.bookRide("Julen", ride, 1, 5.0);
            sut.close();

            
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
	}
	

}
