package testOperations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
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
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class GauzatuEragiketaMockWhiteTest {
/*	
	static DataAccess sut;
	
	protected MockedStatic<Persistence> persistenceMock;

	@Mock
	protected  EntityManagerFactory entityManagerFactory;
	@Mock
	protected  EntityManager db;
	@Mock
    protected  EntityTransaction  et;
	@Mock
	TypedQuery<User> typedQuery;

	@Before
    public  void init() {
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
	
	
	Driver driver;
	
	@SuppressWarnings("deprecation")
	@Test
	public void test1() {
		Mockito.verify(db,Mockito.times(0)).persist(Mockito.anyObject());
	}

	@Test
	public void test2() {
	    try {
	        Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
	        Mockito.when(typedQuery.getSingleResult()).thenReturn(null);
	        
	        sut.open();
	        boolean result = sut.gauzatuEragiketa("Julen", 100.0, true);
	        sut.close();

	        assertFalse(result);
	    } catch (Exception e) {
	        fail();
	    }
	}

	@Test
	public void test3_gauzatuEragiketa_UserInDB_Deposit() {
	    try {
	        User user = new User("Julen", "","");
	        
	        
	        Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
	        Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
	        System.out.println(typedQuery.getSingleResult());
	        sut.open();
	        boolean result = sut.gauzatuEragiketa("Julen", 50.0, true);
	        sut.close();
	        
	        assertEquals(50.0, user.getMoney(), 0.0);
	        assertTrue(result);
	    } catch (Exception e) {
	        fail();
	    }
	}

	@Test
	public void test4_gauzatuEragiketa_UserInDB_Withdraw_MoneyLessThanAmount() {
	    try {
	    	User user = new User("Julen", "","");
	        
	        Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
	        Mockito.when(typedQuery.getSingleResult()).thenReturn(user);

	        sut.open();
	        boolean result = sut.gauzatuEragiketa("Julen", 50.0, false);
	        sut.close();

	        assertEquals(0.0, user.getMoney(), 0.0);
	        assertTrue(result);
	    } catch (Exception e) {
	        fail();
	    }
	}

	@Test
	public void test5_gauzatuEragiketa_UserInDB_Withdraw_MoneyMoreThanAmount() {
	    try {
	    	User user = new User("Julen", "","");
	        user.setMoney(100.0);
	        Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
	        Mockito.when(typedQuery.getSingleResult()).thenReturn(user);

	        sut.open();
	        boolean result = sut.gauzatuEragiketa("Julen", 50.0, false);
	        sut.close();

	        assertEquals(50.0, user.getMoney(), 0.0);
	        assertTrue(result);
	    } catch (Exception e) {
	        fail();
	    }
	}
*/

}