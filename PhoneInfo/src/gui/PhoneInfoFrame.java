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
 * PhoneInfoFrame - setting up the Frame
 *
  **************************************************************************/

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Call;
import model.Contact;
import controller.PhoneInfoController;


public class PhoneInfoFrame extends JFrame {

	private JPanel mainPanel;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	private JButton okButton;
	private JButton cancelButton;
	private JButton helpButton;
	//Add call button
	private JButton showContactButton;
	//Add call table button
	private JButton showTableButton;
	private JTable table;
	private ContactTableModel tableModel;
	
	
	/*******************************************************************
	* Setting up the Frame. Layout Manager 
	*******************************************************************/
	public PhoneInfoFrame (String title)
	{
		super(title);
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(this.mainPanel);
		
		this.mainPanel.add(createSideButtonPanel(), BorderLayout.EAST);
		this.mainPanel.add(createBottomPanel(),BorderLayout.SOUTH);
		this.mainPanel.add(createTable(PhoneInfoController.getInstance().getContact()), BorderLayout.CENTER);
	}		


	/*******************************************************************
	* Setting up the Table
	 * @param arrayList 
	********************************************************************/
	private JScrollPane createTable(ArrayList<Contact> contact)
	{
		table = new JTable();
		tableModel = new ContactTableModel(contact);
		table.setModel(tableModel);
		
		JScrollPane scroller = new JScrollPane(table);
		return scroller;	
	}

	/********************************************************************
    * Setting up the Bottom Buttons
    *********************************************************************/
	private JPanel createBottomPanel()
	{
		JPanel bottomPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		bottomPanel.add(okButton);
		bottomPanel.add(cancelButton);
		return bottomPanel;
	}
	
	/********************************************************************
	* Setting up the Side Buttons
	*********************************************************************/
	private JPanel createSideButtonPanel()
	{
		JPanel sideButtonPanel = new JPanel();
		BoxLayout boxL = new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS);
		sideButtonPanel.setLayout(boxL);
		
		addButton = new JButton("Add Contact");	
		
		showContactButton = new JButton("Show Call History");
		showContactButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					int selectedIndex = table.getSelectedRow();
					if(selectedIndex >=0)
					{
						Contact selectedContact = 
								PhoneInfoController.getInstance().getContact().get(selectedIndex);
								
								//Launch the Add Competitor dialog
								JFrame outerFrame = (JFrame)getRootPane().getParent();
								ShowCallsDialog showCallsDialog = 
											new ShowCallsDialog(outerFrame, "Call History", 
													selectedContact.getName(), selectedContact.getPhoneNumber());
						showCallsDialog.setSize(450, 100);
						showCallsDialog.setLocationRelativeTo(null);
						showCallsDialog.setVisible(true);
					}
					else
					{
						JFrame outerFrame = new JFrame();
						JOptionPane.showMessageDialog(outerFrame, "Please Select a Contact");
					}

				}
		});
		
		
		/*************************************************
		 * Commenting out this, as JTable not working 100% 
		**************************************************/
		showTableButton = new JButton("Show All Call Info");
		showTableButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int selectedIndex = table.getSelectedRow();
				if(selectedIndex >=0)
				{
					Contact selectedContact = 
					PhoneInfoController.getInstance().getContact().get(selectedIndex);
					
					//Launch the Add Competitor dialog
					JFrame outerFrame = (JFrame)getRootPane().getParent();
					ShowCallsTable showCallsTable = 
								new ShowCallsTable(outerFrame, "Call History", 
										selectedContact.getName(), selectedContact.getPhoneNumber());
					showCallsTable.setSize(300, 300);
					showCallsTable.setLocationRelativeTo(null);
					showCallsTable.setVisible(true);
				}
				else
				{
					JFrame outerFrame = new JFrame();
					JOptionPane.showMessageDialog(outerFrame, "Please Select a Contact");
				}

			}
		});
		
	
		editButton = new JButton("Update Contact");
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				int selectedIndex = table.getSelectedRow();
				if(selectedIndex >=0)
				{
					Contact selectedContact = 
					PhoneInfoController.getInstance().getContact().get(selectedIndex);
					
					//Launch the Add Contact dialog
					JFrame outerFrame = (JFrame)getRootPane().getParent();
					AddContactDialog addContactDialog = 
								new AddContactDialog(outerFrame, "Edit Competitor", selectedContact);
					addContactDialog.setSize(300, 150);
					addContactDialog.setLocationRelativeTo(null);
					addContactDialog.setVisible(true);
				}
				else
				{
					JFrame outerFrame = new JFrame();
					JOptionPane.showMessageDialog(outerFrame, "Please Select a Contact");
				}
				
			}
		});
		
		deleteButton = new JButton("Delete Contact");
		deleteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow >=0)
				{
					PhoneInfoController.getInstance().deleteContact(selectedRow);
				}
				else
				{
					JFrame outerFrame = new JFrame();
					JOptionPane.showMessageDialog(outerFrame, "Please Select a Contact");
				}
				
			}
		});
		

		//Create Listeners
		AddButtonListener addButtonL = new AddButtonListener(this);
		addButton.addActionListener(addButtonL);
		
		sideButtonPanel.add(addButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(deleteButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(editButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(showContactButton);	
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(showTableButton);
		return sideButtonPanel;
	}
	
	public void refreshTable()
	{
		this.tableModel.fireTableDataChanged();
	}
	
	/********************************************************************************
    * Add Contact ActionListener interface 
    ********************************************************************************/
		private class AddButtonListener implements ActionListener
		{
			private JFrame outerFrame;
			
			public AddButtonListener(JFrame outerFrame)
			{
				this.outerFrame = outerFrame;
			}
			
			public void actionPerformed(ActionEvent event)
			{
				//Launch the Add Competitor dialog
				AddContactDialog addCompetitorDialog = 
							new AddContactDialog(outerFrame, "Add Contact");
				
				addCompetitorDialog.setSize(250, 150);
				addCompetitorDialog.setLocationRelativeTo(null); 
				addCompetitorDialog.setVisible(true);
			}
		 }
			
	}//end PhoneInfoFrame class