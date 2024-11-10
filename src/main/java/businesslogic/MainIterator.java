package businesslogic;

import configuration.ConfigXML;

public class MainIterator {

	public static void main(String[] args) {
		// the BL is local
		ConfigXML config = ConfigXML.getInstance();
		boolean isLocal = true;
		BLFacade blFacade = new BLFactory(config).getBusinessLogicFactory(isLocal);
		ExtendedIterator<String> i = blFacade.getDepartingCitiesIterator();
		String c;
		System.out.println("_____________________");
		System.out.println("FROM LAST TO FIRST");
		i.goLast(); // Go to last element
		while (i.hasPrevious()) {
		c = i.previous();
		System.out.println(c);
		}
		System.out.println();
		System.out.println("_____________________");
		System.out.println("FROM FIRST TO LAST");
		i.goFirst(); // Go to first element
		while (i.hasNext()) {
		c = i.next();
		System.out.println(c);
		
		}
	}

}
