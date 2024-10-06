import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.*;
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
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class BookRideMockWhiteTest {

    static DataAccess sut;

    protected MockedStatic<Persistence> persistenceMock;

    @Mock
    protected EntityManagerFactory entityManagerFactory;
    @Mock
    protected EntityManager db;
    @Mock
    protected EntityTransaction et;
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

        sut = new DataAccess(db);
    }

    @After
    public void tearDown() {
        persistenceMock.close();
    }

    @SuppressWarnings("deprecation")
	@Test
    public void test1() {
    	Mockito.verify(db,Mockito.times(0)).persist(Mockito.anyObject()); 
    }
    
    @Test
    public void test2() {
    	 try {
             
             String driverUsername = "Driver Test";
             String driverPass= "";
             Driver dri= new Driver(driverUsername,driverPass);
             String rideFrom = "Donostia";
             String rideTo = "Zarautz";
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
             Date rideDate = sdf.parse("05/10/2026");
             Ride ride = new Ride(rideFrom, rideTo, rideDate, 0, 0, dri);
             Traveler tra = new Traveler("Julen", "0000");
             Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		 Mockito.when(typedQuery.getSingleResult()).thenReturn(null);

             sut.open();
             boolean result = sut.bookRide("Julen", ride, 3, 5.0);
             sut.close();
             
             assertFalse(result);

         } catch (Exception e) {
         	fail();
         } 
    }
    @Test
    public void test3() {
    	 try {
             
             String driverUsername = "Driver Test";
             String driverPass= "";
             Driver dri= new Driver(driverUsername,driverPass);
             String rideFrom = "Donostia";
             String rideTo = "Zarautz";
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
             Date rideDate = sdf.parse("05/10/2026");
             Ride ride = new Ride(rideFrom, rideTo, rideDate, 0, 0, dri);
             Traveler tra = new Traveler("Julen", "0000");
             Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		 Mockito.when(typedQuery.getSingleResult()).thenReturn(tra);
             sut.open();
             boolean result = sut.bookRide("Julen", ride, 3, 5.0);
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
             String driverPass= "";
             Driver dri= new Driver(driverUsername,driverPass);
             String rideFrom = "Donostia";
             String rideTo = "Zarautz";
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
             Date rideDate = sdf.parse("05/10/2026");
             Ride ride = new Ride(rideFrom, rideTo, rideDate, 100, 20.5, dri);
             Traveler tra = new Traveler("Julen", "0000");
             tra.setMoney(0);
             Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);		
    		 Mockito.when(typedQuery.getSingleResult()).thenReturn(tra);
             sut.open();
             boolean result = sut.bookRide("Julen", ride, 3, 5.0);
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
    		
            
            sut.open();
            boolean result = sut.bookRide("Julen", ride, 3, 5.0);
            sut.close();

            
            assertTrue(result);
        } catch (Exception e) {
            fail();
        }
    }

    
}
