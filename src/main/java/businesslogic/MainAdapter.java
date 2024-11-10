package businesslogic;

import configuration.ConfigXML;
import domain.Driver;

public class MainAdapter {
	public static void main(String[] args) {
		ConfigXML config = ConfigXML.getInstance();
		// the BL is local
		boolean isLocal = true;
		BLFacade blFacade = new BLFactory(config).getBusinessLogicFactory(isLocal);
		Driver d= blFacade. getDriver("Urtzi");
		DriverTable dt=new DriverTable(d);
		dt.setVisible(true);
		}
}
