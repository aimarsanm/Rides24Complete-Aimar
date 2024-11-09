package gui;

import java.util.Locale;

import businesslogic.BLFacade;
import businesslogic.BLFactory;
import configuration.ConfigXML;

public class ApplicationLauncher {

	public static void main(String[] args) {

		ConfigXML c = ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: " + Locale.getDefault());

		try {
			BLFactory factory = new BLFactory(c);		
			BLFacade appFacadeInterface = factory.createBLFacade();
			MainGUI.setBussinessLogic(appFacadeInterface);
			MainGUI a = new MainGUI();
			a.setVisible(true);

		} catch (Exception e) {
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
		
	}

}