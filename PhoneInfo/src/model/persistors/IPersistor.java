 /******************************************************************************
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
 * IPersistor - interface, where any class that implements this
 * 				has to provide an implementation for each method specified here
 *
  *****************************************************************************/

package model.persistors;

import java.util.ArrayList;

import model.Call;
import model.Contact;

public interface IPersistor 
{
	public void write(ArrayList<Contact> contacts);
	
	public void writeCall(ArrayList<Call> calls);
	
	public ArrayList<Contact> read();
	
	public ArrayList<Call> readCall();
	
	public void delete(String name);
	
	public void update(String originalName, String newName, int newPhoneNumber);
	
	public ArrayList<Call> getCallForContact(String contactName);
	
	public void addCallForContact(Call newCall);
	
}
