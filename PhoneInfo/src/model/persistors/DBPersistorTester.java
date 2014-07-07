 /****************************************************************************
 * Title: Phone Information Application 
 * 
 * Java Packages & Files: 
 * 
 * controller 		 - PersistanceMode & PhoneInfoController. 
 * gui   			 - AddContact, ContactTable Model, MainAppicationDB, 
 * 					   PhoneInfoFrame & ShowCallsDialog. 
 * model  			 - Call, Contact & PhoneInfoModel.
 * model.persistors  - DatabaseFilePersistor, DBPersistorTester & IPersistor.
 *
 * Date: 08/05/2014
 *
 * Author: Brian Coveney  Student Id: R00105727
 *
 * About this:
 * -----------
 * 
 * DBPersistorTester - testing
 *
  ***************************************************************************/

package model.persistors;

import java.util.ArrayList;

import model.Contact;

public class DBPersistorTester {
	
	
	public static void main(String[] args) {
	
		DatabaseFilePersistor dbp = new DatabaseFilePersistor();
				
		Contact c = new Contact("brian", 123456789);
		ArrayList<Contact> cont = new ArrayList<Contact>();
		cont.add(c);
		dbp.write(cont);
	}
}

