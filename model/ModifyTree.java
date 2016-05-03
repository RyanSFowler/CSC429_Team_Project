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
public class ModifyTree extends EntityBase implements IView, IModel{
   
    protected Properties dependencies;
    private static final String myTableName = "TREE";
    
    
    public  ModifyTree(Properties props) 
	{
            super(myTableName);
            setDependencies();
            setData(props);
             System.out.println("je sors constructor");
	}	
    
    public Vector<String> getVector()
	{
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("barcode"));
		v.addElement(persistentState.getProperty("notes"));
		
		return v;
	}
    
    public void setData(Properties props)
        {
                System.out.println("Je rentre setData:" + props);
                persistentState = new Properties();
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true)
		{
			String nextKey = (String)allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
                System.out.println("je sors setData");
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
