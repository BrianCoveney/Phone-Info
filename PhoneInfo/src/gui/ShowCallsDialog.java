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
 * ShowCallsDialog - creating Dialog Box for adding and displaying call(s)
 *
  **************************************************************************/

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import controller.PhoneInfoController;
import model.Call;
import model.Contact;

public class ShowCallsDialog extends JDialog {
	
	private JComboBox<Call> callCombo;
	private JButton addCallButton;
	private JPanel mainPanel;
	private String selectedContactName;
	private int callDuration;
	
	public ShowCallsDialog(JFrame parent, String title, String selectedContactName, int callDuration)
	{
		super(parent, title);
		this.mainPanel = new JPanel();
		this.selectedContactName = selectedContactName;
		this.callDuration = callDuration;
		BoxLayout boxL = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
		this.mainPanel.setLayout(boxL);
		this.getContentPane().add(this.mainPanel); 
		this.mainPanel.add(createCallComboPanel());

	}


	private JPanel createCallComboPanel()
	{
		JPanel callComboPanel = new JPanel();
		
		callCombo = new JComboBox<Call>();
		Dimension dim = callCombo.getPreferredSize(); // adjusting comboBox size.
		callCombo.setPreferredSize(new Dimension(275, dim.height)); 
		
		populateCallCombo();
		callComboPanel.add(callCombo);
		
		addCallButton = new JButton("Add Call..");
		addCallButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{

				try{
					int callDuration = Integer.parseInt(JOptionPane.showInputDialog("Input Call Duration :"));
					
					PhoneInfoController.getInstance().
						addCallForContact(selectedContactName, callDuration, Calendar.getInstance().getTime());
					
					
					
				}catch(NumberFormatException ex){
					JFrame outerFrame = new JFrame();
					JOptionPane.showConfirmDialog(outerFrame, "Please enter a Number");			
				}
				dispose();
			}
		});
		
		callComboPanel.add(addCallButton);
		return callComboPanel;
	};
		
	
	private void populateCallCombo() 
	{
		callCombo.removeAll();
		ArrayList<Call> callsForContact =
				PhoneInfoController.getInstance().getCallsForContact(selectedContactName);
		
		for(Call currCall : callsForContact)
		{
			callCombo.addItem(currCall);
		}
	}		
}//end ShowCallsDialog class