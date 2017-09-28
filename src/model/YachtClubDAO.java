package model;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class YachtClubDAO {
	
	// Url to .bin folder
	// URL url = this.getClass().getClassLoader().getResource("yachtClub.xml");
	
	private File file = new File("src/yachtClub.xml");
			
	// Method to save the member list to XML file
			public void jaxbObjectToXML(YachtClub yachtClub) {
				
				try {
					JAXBContext context = JAXBContext.newInstance(YachtClub.class);
					Marshaller m = context.createMarshaller();
					// for pretty-print XML in JAXB
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

					// Write to System.out for debugging
					// m.marshal(dao, System.out);
					
					// Write to File
					//File file = new File(url.toURI());
					m.marshal(yachtClub, file);
					System.out.println("Saved successfully to: " + file.getPath());
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
	
	// Method to import all books from XML file
		public YachtClub jaxbXMLToObject()  {
			try {
				JAXBContext context = JAXBContext.newInstance(YachtClub.class);
				Unmarshaller un = context.createUnmarshaller();
				//File file = new File(url.toURI());
				YachtClub yachtClub = (YachtClub) un.unmarshal(file);
				System.out.println("Loaded successfully from: " + file.getPath());
				return yachtClub;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	

}
