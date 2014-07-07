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
 * ContactTableModel - overriding methods from the superclass
 *
  **************************************************************************/

package gui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Contact;

public class ContactTableModel extends DefaultTableModel
{
	private static final int NO_OF_COLS = 2;
	
	//These are the indices of the table columns
	private static final int NAME_COL = 0;
	private static final int PHONE_NO_COL = 1;
	private ArrayList<Contact> contact;
	
	//Setting up the Constructor 
	public ContactTableModel(ArrayList<Contact> contact)
	{
		super();
		this.contact = contact;
	}
	
	//We are overriding the getColumnCount method from the superclass
	public int getColumnCount()
	{
		return NO_OF_COLS;
	}
	
	//We are overriding the getColumnName method from the superclass
	public String getColumnName(int column)
	{
		if(column == NAME_COL)
		{
			return "Name";
		}
		else if (column == PHONE_NO_COL)
		{
			return "Phone Number";
		}	
		else
		{
			return "";
		}
	}
		
	//We are overriding the getRowCount method from the superclass
	public int getRowCount()
	{
		if(this.contact != null)
		{
			return this.contact.size();
		}
		else
		{
			return 0;
		}
	}
		
	//We are overriding the getValueAt method from superclass
	public Object getValueAt(int row, int col)
	{
		Contact contactToGet = this.contact.get(row);
		if(col == NAME_COL)
		{
			return contactToGet.getName();
		}
		else if(col == PHONE_NO_COL)
		{
			return contactToGet.getPhoneNumber();
		}
		else
		{
			return "";
		}
	}
}//end ContactTableModel





























