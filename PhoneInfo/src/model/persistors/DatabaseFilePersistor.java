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
 * DatabaseFilePersistor - organising data for the Database
 *
  ***************************************************************************/

package model.persistors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import model.Call;
import model.Contact;

public class DatabaseFilePersistor implements IPersistor{
	
	private Connection dbConnection;
	private ArrayList<AutoCloseable> dbObjects;
	
	private static final String DB_NAME_COL = "name";
	private static final String DB_PHONE_NUMBER_COL = "PhoneNumber";
	
	private static final String CALL_DATE_COL="date";
	private static final String CALL_DURATION_COL = "duration";

	
	public DatabaseFilePersistor()
	{
		dbObjects = new ArrayList<AutoCloseable>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	
			dbConnection=
					DriverManager.getConnection("jdbc:mysql://localhost:3308/brianphoneschema?"+
										"user=root&password=bossdog12");
			System.out.println("Database connection successful : "+dbConnection);
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			System.out.println(cnfe.getMessage()+" Not Connecting");
		}
		
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
			System.out.println(sqlEx.getMessage());
		}	
	}
	
	//Write Contact method
	public void write (ArrayList<Contact> contact)
	{
		try{
			for(Contact currContact : contact)
			{
				PreparedStatement prepStmt =
				dbConnection.prepareStatement("INSERT into CONTACT values (? ,?)");
				
				prepStmt.setString(1, currContact.getName());
				prepStmt.setInt(2, currContact.getPhoneNumber());
				
				prepStmt.executeUpdate();
				dbObjects.add(prepStmt);
			}
			close();
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	//Write Call method
	public void writeCall (ArrayList<Call> call)
	{
		try{
			for(Call currCall : call)
			{
				PreparedStatement prepStmt =
				dbConnection.prepareStatement("INSERT into CONTACT values (? ,?)");
					
				prepStmt.setString(1, currCall.getName());
				prepStmt.setInt(2, currCall.getDuration());	
					
				prepStmt.executeUpdate();
				dbObjects.add(prepStmt);
			}
			close();
				
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
		
	//Add Call for Contact method
	public void addCallForContact(Call newCall)
	{
		try{
			PreparedStatement prepStmt =
				dbConnection.prepareStatement("INSERT into CALLS values (?, ?, ?)" );
					
			prepStmt.setString(1, newCall.getName());
			prepStmt.setInt(2,  newCall.getDuration());
			prepStmt.setString(3, Long.toString(newCall.getDateAndTime().getTime()));
					
			prepStmt.executeUpdate();
			dbObjects.add(prepStmt);
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			close();
		}
	}	

	//Close method
	public void close()
	{
		try{
			for(AutoCloseable curr : dbObjects)
			{
				curr.close();
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
		
	//Read Contact method	
	public ArrayList<Contact> read()
	{
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try{
			Statement getAllContacts = dbConnection.createStatement();
			dbObjects.add(getAllContacts);
			ResultSet rs = getAllContacts.executeQuery("SELECT * FROM CONTACT");
			dbObjects.add(rs);
				
			//Iterate through the result set
			while(rs.next())
			{
				String currContactName = rs.getString(DB_NAME_COL);
				int currContactPhoneNumber = rs.getInt(DB_PHONE_NUMBER_COL);
					
				//Now re-create a Contact instance and add it to the ArrayList
				Contact recreateCont = new Contact(currContactName, currContactPhoneNumber);
				contacts.add(recreateCont);
			}
				
		}catch(Exception ex){
				System.out.println(ex.getMessage());
		}
		finally{
			close();
			return contacts;
		}
	}
	
	
	//Read Contact method	
	public ArrayList<Call> readCall()
	{
		ArrayList<Call> call = new ArrayList<Call>();
		try{
			Statement getAllCall = dbConnection.createStatement();
			dbObjects.add(getAllCall);
			ResultSet rs = getAllCall.executeQuery("SELECT * FROM CALLS");
			dbObjects.add(rs);
				
			//Iterate through the result set
			while(rs.next())
			{
				String currCallName = rs.getString(DB_NAME_COL);
				int currdurationSec = rs.getInt(CALL_DURATION_COL);
				String currCallDate = rs.getString(CALL_DATE_COL);				
				
				long timeMills = Long.parseLong(currCallDate);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timeMills);
				//Now re-create a Call instance and add it to the ArrayList
				Call cont = new Call(currCallName, currdurationSec, cal.getTime());
				call.add(cont);
			}
				
		}catch(Exception ex){
				System.out.println(ex.getMessage());
		}
		finally{
			close();
			return call;
		}
	}
	

	//Get Call for Contact method	
	public ArrayList<Call> getCallForContact(String contactName)
	{
		ArrayList<Call> calls = new ArrayList<Call>();
		try{
			PreparedStatement getAllCalls 
				= dbConnection.prepareStatement("SELECT * FROM CALLS WHERE NAME = ?");
			getAllCalls.setString(1, contactName);
			ResultSet rs = getAllCalls.executeQuery();
				
			while(rs.next())
			{
				String currCallContactName = rs.getString(DB_NAME_COL);
				int durationSec = rs.getInt(CALL_DURATION_COL);
				String currCallDate = rs.getString(CALL_DATE_COL);
				
				long timeMills = Long.parseLong(currCallDate);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timeMills);
				Call cont = new Call(currCallContactName, durationSec, cal.getTime());
				calls.add(cont);
			}
				getAllCalls.close();
				rs.close();
				
		}catch(Exception ex){
				System.out.println(ex.getMessage());
		}finally{
			return calls;
		}	
	}
		
	//Delete Contact method	
	public void delete(String name){
		try{
			PreparedStatement deleteContactStmt = 
					dbConnection.prepareStatement("DELETE from CONTACT where name=?");
			dbObjects.add(deleteContactStmt);
			deleteContactStmt.setString(1, name);
			deleteContactStmt.executeUpdate();
				
			PreparedStatement deleteCallStmt = 
					dbConnection.prepareStatement("DELETE from CALLS where name=?");
			dbObjects.add(deleteContactStmt);
			deleteCallStmt.setString(1, name);
			deleteCallStmt.executeUpdate();
				
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			close();	
			}
	}
	
	//Update Contact method
	public void update(String originalName, String newName, int newPhoneNumber)
	{
		try{	
			PreparedStatement prepStmt = 
				dbConnection.prepareStatement("UPDATE CONTACT SET name=?, phoneNumber=? WHERE name=?");
			dbObjects.add(prepStmt);
			prepStmt.setString(1, newName);
			prepStmt.setInt(2, newPhoneNumber);
			prepStmt.setString(3, originalName);
				
			prepStmt.executeUpdate();
				
		}catch(Exception ex){
				System.out.println(ex.getMessage());
		}
		finally{
			close();
		}
	}

}//end DatabaseFilePersistor class












