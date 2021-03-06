/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import impresario.IModel;
import impresario.IView;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author berghen
 */
public class RemoveScout extends EntityBase implements IView, IModel{

    protected Properties dependencies;
    private static final String myTableName = "SCOUT";


    public  RemoveScout(Properties props)
	{
            super(myTableName);
            setDependencies();
            setData(props);
	}

    public Vector<String> getVector()
	{
    Vector<String> v = new Vector<String>();
    v.addElement(persistentState.getProperty("ScoutId"));
		v.addElement(persistentState.getProperty("FirstName"));
    v.addElement(persistentState.getProperty("MiddleInitial"));
		v.addElement(persistentState.getProperty("LastName"));
    v.addElement(persistentState.getProperty("DateOfBirth"));
    v.addElement(persistentState.getProperty("Email"));
		v.addElement(persistentState.getProperty("PhoneNumber"));
    v.addElement(persistentState.getProperty("Status"));

		return v;
	}

    public void setData(Properties props)
        {
                persistentState = new Properties();
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true)
		{
			String nextKey = (String)allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);
                        //System.out.print(nextKey + "   " + nextValue);
			if (nextValue != null)
			{
                                //System.out.print(nextKey + "   " + nextValue);
				persistentState.setProperty(nextKey, nextValue);
			}
		}
                //System.out.println("Res: " + persistentState);
        }

    private void setDependencies()
	{
		dependencies = new Properties();

		myRegistry.setDependencies(dependencies);
	}

    @Override
    public Object getState(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void initializeSchema(String tableName) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
