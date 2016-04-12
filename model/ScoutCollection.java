/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import impresario.IModel;
import impresario.IView;
import java.util.Properties;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.View;
import userinterface.ViewFactory;


public class ScoutCollection extends EntityBase implements IView, IModel {

     protected Stage myStage;
     protected Scout myScout;
     private String returnScreen;
     protected Properties dependencies;
     private static final String myTableName = "Scout";
     private String updateStatusMessage = "";


     public ScoutCollection(Scout s, String type) throws Exception {
            super(myTableName);
            myScout = s;
            returnScreen = type;
            persistentState = new Properties();
            setDependencies();
            createScoutCollectionView();
        }
     
     public String getActionType(){
    	 return returnScreen;
     }
     
     public void setReturnView(){
    	 if(returnScreen.equals("Modify")){
    		 createModifyScoutView();
    	 }
    	 else if(returnScreen.equals("Remove")){
    		 createRemoveScoutView();
    	 }
     }

     public void createModifyScoutView() {
            Scene currentScene = (Scene)myViews.get("ModifyScoutView");

            if (currentScene == null)
            {
                View newView = ViewFactory.createView("ModifyScoutView", this);
                currentScene = new Scene(newView);
                currentScene.getStylesheets().add("styleSheet.css");
                myViews.put("ModifyScoutView", currentScene);
            }
            swapToView(currentScene);
        }

      public void createRemoveScoutView() {
             Scene currentScene = (Scene)myViews.get("RemoveScoutView");
             if (currentScene == null)
             {
                   View newView = ViewFactory.createView("RemoveScoutView", this);
                   currentScene = new Scene(newView);
                   currentScene.getStylesheets().add("styleSheet.css");
                   myViews.put("RemoveScoutView", currentScene);
             }
             swapToView(currentScene);
        }

       public void createScoutCollectionView() {
              Scene currentScene = (Scene)myViews.get("ScoutCollectionView");

              if (currentScene == null)
              {
                  View newView = ViewFactory.createView("ScoutCollectionView", this);
                  currentScene = new Scene(newView);
                  currentScene.getStylesheets().add("styleSheet.css");
                  myViews.put("ScoutCollectionView", currentScene);
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
            if (key.equals("Scout"))
		return this;
            return null;
	}

     public void stateChangeRequest(String key, Object value)
	{
            if (key.equals("Done") == true)
            {
               // myLibrarian.createAndShowLibrarianView();
            }
            else if (key.equals("updateScout") == true)
            {
                if (value != null)
                {
                    persistentState = (Properties) value;
                 //   updateStateInDatabase();
                }
            }
            //myRegistry.updateSubscribers(key, this);
	}

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

}
