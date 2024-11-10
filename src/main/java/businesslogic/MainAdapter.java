package businesslogic;

import java.util.Calendar;
import java.util.Date;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Driver;

public class MainAdapter {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2024, Calendar.MAY, 30);
		Date date2 = UtilDate.trim(cal.getTime());

		cal.set(2024, Calendar.MAY, 10);
		Date date3 = UtilDate.trim(cal.getTime());

		cal.set(2024, Calendar.APRIL, 20);
		Date date4 = UtilDate.trim(cal.getTime());
		ConfigXML config = ConfigXML.getInstance();
		// the BL is local
		boolean isLocal = true;
		BLFacade blFacade = new BLFactory(config).getBusinessLogicFactory(isLocal);
		Driver d= blFacade. getDriver("Urtzi");
		d.addRide("Donostia", "Madrid", date2, 5, 20); //ride1
		d.addRide("Irun", "Donostia", date2, 5, 2); //ride2
		d.addRide("Madrid", "Donostia", date3, 5, 5); //ride3
		d.addRide("Barcelona", "Madrid", date4, 0, 10); //ride4
		DriverTable dt=new DriverTable(d);
		dt.setVisible(true);
		}
}
