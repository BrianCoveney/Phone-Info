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
 * Call - creating call(s) to be added to contact
 *
  **************************************************************************/

package model;

import java.util.Date;

public class Call 
{
	private String name;
	private Date dateAndTime;
	private int durationSec;
	
	public Call(String name, int durationSec, Date dateAndTime)
	{
		this.name = name;
		this.dateAndTime = dateAndTime;
		this.durationSec = durationSec;
	}

	public String getName() 
	{
		return name;
	}
	
	public Date getDateAndTime()
	{
		return dateAndTime;
	}
	
	public int getDuration()
	{
		return durationSec;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setDateAndIme(Date dateAndTime)
	{
		this.dateAndTime = dateAndTime;
	}

	public void setDuration(int durationSec)
	{
		this.durationSec = durationSec;
	}
	
	public String toString()
	{
		return this.name +" |"+ this.durationSec +"| "+ this.dateAndTime;
		
 	}
}
