package testOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Driver;
import domain.Ride;
import domain.Traveler;
import domain.User;


public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();
	

	public TestDataAccess()  {
		
		System.out.println("TestDataAccess created");

		//open();
		
	}

	
	public void open(){
		

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		System.out.println("TestDataAccess opened");

		
	}
	public void close(){
		db.close();
		System.out.println("TestDataAccess closed");
	}

	public boolean removeDriver(String name) {
		System.out.println(">> TestDataAccess: removeDriver");
		Driver d = db.find(Driver.class, name);
		if (d!=null) {
			db.getTransaction().begin();
			db.remove(d);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	public Driver createDriver(String name, String pass) {
		System.out.println(">> TestDataAccess: addDriver");
		Driver driver=null;
			db.getTransaction().begin();
			try {
			    driver=new Driver(name,pass);
				db.persist(driver);
				db.getTransaction().commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return driver;
    }
	public boolean existDriver(String email) {
		 return  db.find(Driver.class, email)!=null;
		 

	}
		
		public Driver addDriverWithRide(String name, String from, String to,  Date date, int nPlaces, float price) {
			System.out.println(">> TestDataAccess: addDriverWithRide");
				Driver driver=null;
				db.getTransaction().begin();
				try {
					 driver = db.find(Driver.class, name);
					if (driver==null) {
						System.out.println("Entra en null");
						driver=new Driver(name,null);
				    	db.persist(driver);
					}
				    driver.addRide(from, to, date, nPlaces, price);
					db.getTransaction().commit();
					System.out.println("Driver created "+driver);
					
					return driver;
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return null;
	    }
		
		
		public boolean existRide(String name, String from, String to, Date date) {
			System.out.println(">> TestDataAccess: existRide");
			Driver d = db.find(Driver.class, name);
			if (d!=null) {
				return d.doesRideExists(from, to, date);
			} else 
			return false;
		}
		public Ride removeRide(String name, String from, String to, Date date ) {
			System.out.println(">> TestDataAccess: removeRide");
			Driver d = db.find(Driver.class, name);
			if (d!=null) {
				db.getTransaction().begin();
				Ride r= d.removeRide(from, to, date);
				db.getTransaction().commit();
				System.out.println("created rides" +d.getCreatedRides());
				return r;

			} else 
			return null;

		}


		public User createUser(String username, String string, String string2) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: createUSer");
			User u=null;
				db.getTransaction().begin();
				try {
				    u=new User(username,string,string2);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return u;
		}


		public boolean removeUser(String name) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: removeUSer");
			User d = db.find(User.class, name);
			if (d!=null) {
				db.getTransaction().begin();
				db.remove(d);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}

		public boolean existUser(String username) {
		    return db.find(User.class, username) != null;
		}


		public void addDirua(String username, double d) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: addDiruaUser");
			User u=db.find(User.class, username);
				db.getTransaction().begin();
				try {
				    u.setMoney(u.getMoney()+d);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				
			
		}


		public User getUser(String username) {
			// TODO Auto-generated method stub
			return db.find(User.class, username);
		}
		public int getkont() {
		    int count = 0;
		    Connection connection = null;
		    Statement statement = null;
		    ResultSet resultSet = null;

		    try {
		        // Abre una conexión a la base de datos (ajusta según tu configuración)
		        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");

		        // Crea un statement para ejecutar la consulta
		        statement = connection.createStatement();

		        // Ejecuta una consulta SQL para contar el número de registros en la tabla 'users'
		        resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

		        // Recupera el valor del ResultSet
		        if (resultSet.next()) {
		            count = resultSet.getInt(1); // Obtiene el valor del primer (y único) resultado
		        }

		    } catch (SQLException e) {
		        e.printStackTrace(); // O maneja la excepción de otra manera
		    } finally {
		        // Cierra todos los recursos para evitar fugas de memoria
		        try {
		            if (resultSet != null) resultSet.close();
		            if (statement != null) statement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return count; // Devuelve el número de registros encontrados
		}


		public Ride createRide(String rideFrom, String rideTo, Date rideDate, int i, double d, Driver dri) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: createRide");
			Ride u=null;
				db.getTransaction().begin();
				try {
				    u=new Ride(rideFrom,rideTo, rideDate, i,d, dri);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return u;
		}


		public Traveler createTraveler(String string, String string2) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: createTraveler");
			Traveler u=null;
				db.getTransaction().begin();
				try {
				    u=new Traveler(string,string2);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return u;
		}


		public void addDirua2(String username, double d) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: addDiruaTraveler");
			Traveler u=db.find(Traveler.class, username);
				db.getTransaction().begin();
				try {
				    u.setMoney(u.getMoney()+d);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
		}


		public boolean removeTraveler(String tname) {
			// TODO Auto-generated method stub
			System.out.println(">> TestDataAccess: removeTraveler");
			Traveler d = db.find(Traveler.class, tname);
			if (d!=null) {
				db.getTransaction().begin();
				db.remove(d);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}

}