 /***************************************************************************
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
 * PhoneInfoModel - organising Contact and Call
 *
  **************************************************************************/

package model;

import java.util.ArrayList;

public class PhoneInfoModel {
	
	private ArrayList<Contact> contact;
	private ArrayList<Call> call;
	
	public PhoneInfoModel()
	{
		this.contact = new ArrayList<Contact>();
		this.call = new ArrayList<Call>();
	}
	
	public PhoneInfoModel(ArrayList<Contact> contact, ArrayList<Call> call)
	{
		this.contact = contact;
		this.call = call;
	}
	
	public PhoneInfoModel( ArrayList<Call> call)
	{
		this.contact = contact;
		this.call = call;
	}
	
	public void addContact(Contact c)
	{
		this.contact.add(c);
	}
	
	public ArrayList<Contact> getContact()
	{
		return this.contact;
	}

	public void addCall(Call c)
	{
		this.call.add(c);
	}
	
	public ArrayList<Call> getCall() {
		
		return this.call;
	}
	
}	
