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
 * AddContactDialog, creating Dialog Box for adding and editing Contact
 *
  **************************************************************************/

package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Contact;
import controller.PhoneInfoController;


public class AddContactDialog extends JDialog
{
	private enum Mode {ADD, EDIT};
	
	private JPanel mainPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel phoneNumberLabel;
	private JTextField phoneNumberField;
	private JButton okButton;
	private JButton cancelButton;
	
	private Mode dialogMode;
	
	private Contact contactBeingEdited;

	
	public AddContactDialog(JFrame parent, String title, Contact cont)
	{
		this(parent, title);
		this.contactBeingEdited = cont;
		this.nameField.setText(cont.getName());
		this.phoneNumberField.setText(Integer.toString(cont.getPhoneNumber()));
		dialogMode = Mode.EDIT;
	}
	
	public AddContactDialog(JFrame parent, String title)
	{
		super(parent, title);
		this.mainPanel = new JPanel();
		BoxLayout boxL = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
		this.mainPanel.setLayout(boxL);
		this.getContentPane().add(this.mainPanel);
		this.mainPanel.add(createNamePanel());
		this.mainPanel.add(createPhoneNumberPanel());
		this.mainPanel.add(createButtonPanel());
		dialogMode = Mode.ADD;
	}
	
	private JPanel createNamePanel()
	{
		JPanel namePanel = new JPanel();
		nameLabel = new JLabel("Name : ");
		nameField = new JTextField(15);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		return namePanel;
	}
	
	private JPanel createPhoneNumberPanel()
	{
		JPanel phoneNoPanel = new JPanel();
		phoneNumberLabel = new JLabel("Phone Number : ");
		phoneNumberField = new JTextField(10);
		phoneNoPanel.add(phoneNumberLabel);
		phoneNoPanel.add(phoneNumberField);
		return phoneNoPanel;
	}
	
	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) 
			{			
				try{
					if(dialogMode == Mode.ADD)
					{
					PhoneInfoController.getInstance().
						createNewContact(nameField.getText(),
									Integer.parseInt(phoneNumberField.getText()));
					}
					else if(dialogMode == Mode.EDIT)
					{
						
						PhoneInfoController.getInstance().updateContact(
								contactBeingEdited.getName(),
								nameField.getText(),
								Integer.parseInt(phoneNumberField.getText()));		
					}
				}catch(Exception ex){
					JFrame outerFrame = new JFrame();
					JOptionPane.showConfirmDialog(outerFrame, "Please enter a valid value");
				}
					
				dispose();			
			}
			
		});

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		return buttonPanel;
	}
	
}//end AddContactDialog class
