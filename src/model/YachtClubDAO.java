package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class YachtClubDAO {
	
	// Method to import all books from XML file
		public YachtClub jaxbXMLToObject() throws IOException {
			try {
				JAXBContext context = JAXBContext.newInstance(YachtClub.class);
				Unmarshaller un = context.createUnmarshaller();
				File file = new File("E:/yachtClub.xml");
				System.out.println(file.getPath());
				YachtClub yachtClub = (YachtClub) un.unmarshal(file);
				return yachtClub;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	// Method to save the member list to XML file
		public void jaxbObjectToXML() {

			try {
				JAXBContext context = JAXBContext.newInstance(YachtClub.class);
				Marshaller m = context.createMarshaller();
				// for pretty-print XML in JAXB
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				// Write to System.out for debugging
				// m.marshal(dao, System.out);
				// Write to File
				m.marshal(yachtClub.Main.yachtClub, new File("E:/yachtClub.xml"));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

}
