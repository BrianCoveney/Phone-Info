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
 * PhoneInfoController - Singleton
 *
  **************************************************************************/

package controller;

import java.util.Date;
import java.util.ArrayList;

import model.Call;
import model.Contact;
import model.PhoneInfoModel;
import model.persistors.IPersistor;
import gui.PhoneInfoFrame;

public class PhoneInfoController {

	private static PhoneInfoController instance;
	private PhoneInfoModel dataModel;
	private PhoneInfoFrame view;

	
	private PersistanceMode persistenceMode;
	
	//The persistor
	private IPersistor persistor;

	//The singleton 
	public static PhoneInfoController getInstance()
	{
		if(instance == null)
		{
			instance = new PhoneInfoController();
		}
		return instance;
	}
	
	// init method
	public void init()
	{
		try{
			ArrayList<Contact> contacts = this.persistor.read();
			ArrayList<Call> call = this.persistor.readCall();
			
			//Check that what was read out of the file was not null
			if(contacts != null && call != null)
			{
				PhoneInfoModel dataModel = new PhoneInfoModel(contacts, call);
				this.setModel(dataModel);
			}
			
			else
			{
				this.setModel(new PhoneInfoModel());
			}
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	//Setters for Model, Persistor, PersistorMode and View
	public void setModel(PhoneInfoModel dataModel)
	{
		this.dataModel = dataModel;
	}
	
	public void setPersistor(IPersistor persistor)
	{
		this.persistor = persistor;
	}
	
	public void setView(PhoneInfoFrame view)
	{
		this.view = view;
	}
	
	public void setPersistenceMode(PersistanceMode persistanceMode)
	{
		this.persistenceMode = persistanceMode;
	}
	
	//Getters for Contact and Call
	public ArrayList<Contact> getContact()
	{
		return this.dataModel.getContact();
	}
		
	public ArrayList<Call> getCall()
	{
		return this.dataModel.getCall();
	}
		
	public ArrayList<Call> getCallsForContact(String contactName)
	{
		return this.persistor.getCallForContact(contactName);
	}
	
	
	//Create New Contact method
	public void createNewContact(String name, int phoneNumber)
	{
		Contact newContact = null;
		newContact = new Contact(name, phoneNumber);
		
		this.dataModel.addContact(newContact);
		
		ArrayList<Contact> contactWrapper = new ArrayList<Contact>();
		contactWrapper.add(newContact);
		this.persistor.write(contactWrapper);
	
		this.view.refreshTable();
	}
	
	
	//Create New Call method****************
		public void createNewCall(String name, int callDuration, Date date)
		{
			Call newCall = null;
			newCall = new Call(name, callDuration, date);
			
			this.dataModel.addCall(newCall);
			
			ArrayList<Call> contactWrapper = new ArrayList<Call>();
			contactWrapper.add(newCall);
			this.persistor.writeCall(contactWrapper);
		
			this.view.refreshTable();
		}
	

	//Add callForContact method
	public void addCallForContact(String contactName, int callDuration, Date dateAndTime)
	{
		Call addCall = 
				new Call(contactName, callDuration, dateAndTime);
		
		this.persistor.addCallForContact(addCall);
		this.view.refreshTable();	
	}
	
	//Delete Contact method
	public void deleteContact(int selectedIndex)
	{
		Contact removedContact =
				this.dataModel.getContact().remove(selectedIndex);

		this.persistor.delete(removedContact.getName());
		this.view.refreshTable();
	}
	

	//Update Contact method
	public void updateContact(String originalName, String newName, int newPhoneNumber)
	{
		//Search the model for someone who has the name - originalName
		for(Contact currCont : this.dataModel.getContact())
		{
			if(currCont.getName().equals(originalName))
			{
				currCont.setName(newName);
				currCont.setPhoneNumber(newPhoneNumber);
			}
		}
		this.persistor.update(originalName, newName, newPhoneNumber);
		this.view.refreshTable();
	}

} 
















