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
 * Contact - creating contact(s)
 *
  **************************************************************************/


package model;

import java.util.ArrayList;

public class Contact {
	
	
	private ArrayList<Call> callHistory = new ArrayList<Call>();

	private String name;
	private int phoneNumber;
	
	public Contact (String name, int phoneNumber)
	{
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPhoneNumber(int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	
	// Call (Show Contact Detail)
	public ArrayList<Call> getCallList() 
	{
		return callHistory;
	}

	public void setContactList(ArrayList<Call> callHistory) 
	{
		this.callHistory = callHistory;
	}
	
}