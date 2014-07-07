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
 * Main application, creating and displaying the GUI (View). 
 *
  **************************************************************************/

package gui;

import javax.swing.JFrame;

import model.persistors.DatabaseFilePersistor;
//import controller.PersistanceMode;
import controller.PhoneInfoController;

public class MainApplicationDB {
	
	public static void main(String[] args) 
	{
		PhoneInfoController.getInstance().setPersistor(new DatabaseFilePersistor());
		
		PhoneInfoController.getInstance().init();
		
		//Creating and displaying the GUI (view)
		PhoneInfoFrame phoneFrame =
				new PhoneInfoFrame("Phone Information");
		phoneFrame.setSize(500,400);
		phoneFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		phoneFrame.setLocationRelativeTo(null);
		phoneFrame.setVisible(true);
		
		PhoneInfoController.getInstance().setView(phoneFrame);
	}
}//end Main class
 