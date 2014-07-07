/*************************************************
* Commenting out this, as JTable not working 100% 
**************************************************/

package gui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Call;


public class CallTableModel extends DefaultTableModel {

private static final int NO_OF_COLS = 3;
	
	//These are the indices of the table columns
	private static final int DB_NAME_COL = 0;
	private static final int CALL_DURATION_COL = 1;
	private static final int CALL_DATE_COL = 2;
	
	private ArrayList<Call> call;
	
	
	public CallTableModel(ArrayList<Call> call)
	{
		super();
		this.call = call;
	}
	
	//We are overriding the getColumnCount method from the superclass
	public int getColumnCount()
	{
		return NO_OF_COLS;
	}
	
	//We are overriding the getColumnName method from the superclass
	public String getColumnName(int column)
	{
		if(column == DB_NAME_COL)
		{
			return "Name";
		}
		else if (column == CALL_DURATION_COL)
		{
			return "Call Duration";
		}	
		else if (column == CALL_DATE_COL)
		{
			return "Call Date";
		}
		else
		{
			return "";
		}
	}
		
		//We are overriding the getRowCount method from the superclass
		public int getRowCount()
		{
			if(this.call != null)
			{
				return this.call.size();
			}
			else
			{
				return 0;
			}
		}
		
		public Object getValueAt(int row, int col)
		{
			Call callToGet = this.call.get(row);
			if(col == DB_NAME_COL)
			{
				return callToGet.getName();
			}
			else if(col == CALL_DURATION_COL)
			{
				return callToGet.getDuration();
			}
			else if(col == CALL_DATE_COL)
			{
				return callToGet.getDateAndTime();
			}
			else
			{
				return "";
			}
		}
	
	}
