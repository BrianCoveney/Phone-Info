/*************************************************
 * Commenting out this, as JTable not working 100% 
**************************************************/
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Call;
import controller.PhoneInfoController;

public class ShowCallsTable extends JDialog {
	

	private JButton addCallButton;
	private JPanel mainPanel;
	private JTable callTable;
	private CallTableModel tableModel;
	private String selectedContactName;
	private int callDuration;
	
	public ShowCallsTable(JFrame parent, String title, String selectedContactName, int callDuration)
	{
		super(parent, title);
		this.mainPanel = new JPanel();
		this.selectedContactName = selectedContactName;
		this.callDuration = callDuration;
		
	
		this.getContentPane().add(this.mainPanel);
		this.mainPanel.add(createCallTable(PhoneInfoController.getInstance().getCall()));
	}

	// JTable 
	public JPanel createCallTable(ArrayList<Call> call)
	{
		JPanel callTablePanel = new JPanel();
		callTable = new JTable();
		
		tableModel = new CallTableModel(call);
		callTable.setModel(tableModel);
		
		//populateCallTable();
		callTablePanel.add(callTable);

		
//		addCallButton = new JButton("Add Call..");
//		addCallButton.addActionListener(new ActionListener(){
//	
//			public void actionPerformed(ActionEvent ev)
//			{
//				try{
//		
//					int callDuration = Integer.parseInt(JOptionPane.showInputDialog("Input Call Duration :"));
//					
//					PhoneInfoController.getInstance().
//						addCallForContact(selectedContactName, callDuration, Calendar.getInstance().getTime());
//					
//					
//				}catch(NumberFormatException ex){
//					JFrame outerFrame = new JFrame();
//					JOptionPane.showConfirmDialog(outerFrame, "Please enter a Number");			
//				}
//				dispose();
//			}
//		
//			
//			
//		});
//		callTablePanel.add(addCallButton);
		
		setLayout(new BorderLayout());
	    add(new JScrollPane(callTable), BorderLayout.CENTER);
	    
		
		return callTablePanel;
		
	};
	
	private void populateCallTable() 
	{
		callTable.removeAll();
		ArrayList<Call> callsForContact =
				PhoneInfoController.getInstance().getCallsForContact(selectedContactName);
		
		//Object[] array = callsForContact.toArray(new Object[callsForContact.size()]);
			
		for(Call currCall : callsForContact)
		{
		    
		}
		
	}	
	
	
}//end ShowCallsDialog class