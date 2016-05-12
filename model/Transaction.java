/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Enumeration;

import impresario.IModel;
import impresario.IView;

import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.CORBA.Current;

import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.View;
import userinterface.ViewFactory;


public class Transaction extends EntityBase implements IView, IModel {

     protected Stage myStage;
     protected TreeLotCoordinator myTreeLotCoordinator;
     protected Properties dependencies;
     private static final String myTableName = "TRANSACTION";
     private String updateStatusMessage = "";
     private String treeBarcode, treeTypePrice, date, time;
     private boolean existsTree;


     public Transaction(TreeLotCoordinator l, String type) throws Exception {
            super(myTableName);
            myTreeLotCoordinator = l;
            persistentState = new Properties();
            setDependencies();

//            createTransactionView();

            if (type == "Sell") {
                createTransactionView();
            }

        }
        public Transaction(Properties props)
	      {
		        super(myTableName);

		        setDependencies();
		        setData(props);
	      }
        
        public Transaction(){
            super(myTableName);
            persistentState = new Properties();
            setDependencies();
        }
        //-----------------------------------------------------------------------------------
	      public void setData(Properties props)
	      {
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
	      }
        public void createTransactionView() {
               Scene currentScene = (Scene)myViews.get("TransactionView");

               if (currentScene == null)
               {
                   View newView = ViewFactory.createView("TransactionView", this);
                   currentScene = new Scene(newView);
                   currentScene.getStylesheets().add("styleSheet.css");
                   myViews.put("TransactionView", currentScene);
               }
               swapToView(currentScene);
           }


     public void setDependencies()
	{
            dependencies = new Properties();
            myRegistry.setDependencies(dependencies);
	}

     public Object getState(String key)
	{
            if (key.equals("Transaction"))
		          return this;
            if(key.equals("SearchBarcode")){
            	return (String)treeTypePrice;
            }
            if(key.equals("TreeAvailable")){
            	return (boolean)existsTree;
            }
            return null;
	}

     public void stateChangeRequest(String key, Object value)
	{
            if (key.equals("Done") == true)
            {
                myTreeLotCoordinator.createAndShowTreeLotCoordinatorView();

            }
            else if (key.equals("Add") == true)
            {
                if (value != null)
                {
                    persistentState = (Properties) value;
                    insert();
                }
            }
            else if (key.equals("SearchBarcode") == true)
            {
                if (value != null)
                {
                    persistentState = (Properties) value;  
                    treeBarcode = persistentState.getProperty("Barcode");
                    Tree tree = null;
					try {
						tree = new Tree();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    existsTree = tree.findAvailableTree(treeBarcode);
                    if(existsTree){
	                    TreeType treeType = null;
						try {
							treeType = new TreeType();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            		treeTypePrice = treeType.findPrice(treeBarcode.substring(0,2));
                    }
                }
            }
	}

  /*
        public Vector<String> getEntryListView()
        {
        		Vector<String> v = new Vector<String>();
            v.addElement(persistentState.getProperty("ScoutId"));
        		v.addElement(persistentState.getProperty("FirstName"));
        		v.addElement(persistentState.getProperty("MiddleInitial"));
        		v.addElement(persistentState.getProperty("LastName"));
        		v.addElement(persistentState.getProperty("DateOfBirth"));
        		v.addElement(persistentState.getProperty("PhoneNumber"));
            v.addElement(persistentState.getProperty("Email"));
            v.addElement(persistentState.getProperty("Status"));
            v.addElement(persistentState.getProperty("DateStatusUpdated"));

        		return v;
        }*/
        public void insert() {
            //System.out.print("Insert Add Tree");
            dependencies = new Properties();
            dependencies.put("SessionId", persistentState.getProperty("SessionId"));
            //dependencies.put("TransactionType", persistentState.getProperty("TransactionType"));
            dependencies.put("Barcode", persistentState.getProperty("Barcode"));
            dependencies.put("TransactionAmount", persistentState.getProperty("TransactionAmount"));
            dependencies.put("PaymentMethod", persistentState.getProperty("PaymentMethod"));
            dependencies.put("CustomerName", persistentState.getProperty("CustomerName"));
            dependencies.put("CustomerPhone", persistentState.getProperty("CustomerPhone"));
            dependencies.put("CustomerEmail", persistentState.getProperty("CustomerEmail"));
            dependencies.put("TransactionDate", persistentState.getProperty("TransactionDate"));
            dependencies.put("TransactionTime", persistentState.getProperty("TransactionTime"));
            System.out.print("dependencies:" + dependencies);
            try {
                int i = insertAutoIncrementalPersistentState(this.mySchema, dependencies);
                try {
					Tree tree = new Tree();
					Properties treeDependencies = new Properties();
					treeDependencies.put("Status", "Sold");
					treeDependencies.put("Barcode", persistentState.getProperty("Barcode"));
					tree.stateChangeRequest("Sold", treeDependencies);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } catch (SQLException ex) {
                Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        /*public int findCashTotal(){
          	String query = "SELECT SUM(TransactionAmount) AS Total FROM " + myTableName + " WHERE PaymentMethod = 'Cash';";
      		System.out.println(query);
      		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
      		System.out.println(allDataRetrieved);
      		if (allDataRetrieved != null)
      		{
      				Properties session = (Properties)allDataRetrieved.elementAt(0);
      				session.getProperty("Total");
      		}
      		else{return 0;}
          }*/
        
        
   protected void initializeSchema(String tableName)
	{
            if (mySchema == null)
            {
                mySchema = getSchemaInfo(tableName);
            }
	}

   public void updateState(String key, Object value)
	{
            stateChangeRequest(key, value);
	}
  public void done()
  {
    myTreeLotCoordinator.transactionDone();
  }



}
