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


public class Scout extends EntityBase implements IView, IModel {

     protected Stage myStage;
     protected TreeLotCoordinator myTreeLotCoordinator;
     protected Properties dependencies;
     private static final String myTableName = "Scout";
     private String updateStatusMessage = "";


     public Scout(TreeLotCoordinator l, String type) throws Exception {
            super(myTableName);
            myTreeLotCoordinator = l;
            persistentState = new Properties();
            setDependencies();
            if (type == "Add") {
                createAddScoutView();
            }
            else if (type == "Modify") {
                createEnterModifyScoutView();
            }
            else if (type == "Remove") {
                createEnterRemoveScoutView();
            }
        }

     public void createAddScoutView() {
            Scene currentScene = (Scene)myViews.get("AddScoutView");

            if (currentScene == null)
            {
                View newView = ViewFactory.createView("AddScoutView", this);
                currentScene = new Scene(newView);
                currentScene.getStylesheets().add("styleSheet.css");
                myViews.put("AddScoutView", currentScene);
            }
            swapToView(currentScene);
        }

     public void createEnterModifyScoutView() {
            Scene currentScene = (Scene)myViews.get("EnterModifyScoutView");

            if (currentScene == null)
            {
                View newView = ViewFactory.createView("EnterModifyScoutView", this);
                currentScene = new Scene(newView);
                currentScene.getStylesheets().add("styleSheet.css");
                myViews.put("EnterModifyScoutView", currentScene);
            }
            swapToView(currentScene);
        }
        public void createEnterRemoveScoutView() {
               Scene currentScene = (Scene)myViews.get("EnterRemoveScoutView");

               if (currentScene == null)
               {
                   View newView = ViewFactory.createView("EnterRemoveScoutView", this);
                   currentScene = new Scene(newView);
                   currentScene.getStylesheets().add("styleSheet.css");
                   myViews.put("EnterRemoveScoutView", currentScene);
               }
               swapToView(currentScene);
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
            else if (key.equals("updateBook") == true)
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
  public void done()
  {
    myTreeLotCoordinator.transactionDone();
  }



}
