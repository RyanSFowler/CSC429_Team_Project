package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class ScoutTableModel
{
	private final SimpleStringProperty scoutId;
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;


	//----------------------------------------------------------------------------
	public ScoutTableModel(Vector<String> bookData)
	{
		scoutId =  new SimpleStringProperty(bookData.elementAt(0));
		firstName =  new SimpleStringProperty(bookData.elementAt(1));
		lastName =  new SimpleStringProperty(bookData.elementAt(2));
	}

	//----------------------------------------------------------------------------
	public String getScoutId() {
        return scoutId.get();
    }

	//----------------------------------------------------------------------------
    public void setScoutId(String id) {
    	scoutId.set(id);
    }

    //----------------------------------------------------------------------------
    public String getFirstName() {
        return firstName.get();
    }

    //----------------------------------------------------------------------------
    public void setFirstName(String firstNameIn) {
    	firstName.set(firstNameIn);
    }

    //----------------------------------------------------------------------------
    public String getLastName() {
        return lastName.get();
    }

    //----------------------------------------------------------------------------
    public void setLastName(String lastNameIn) {
    	lastName.set(lastNameIn);
    }
}
