/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import impresario.IModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Scout;


public class EnterScoutView extends View {

        protected Button cancelButton;
        protected Button submitButton;
	      private Button doneButton;
        protected MessageView statusLog;
        private TextField firstNameField;
      	private TextField lastNameField;
        private Scout myScout;

        public EnterScoutView(IModel scout)
    {
        super(scout, "EnterScoutView");
        myScout=(Scout)scout;
        // create a container for showing the contents
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(15, 5, 5, 5));

        // create our GUI components, add them to this panel
	container.getChildren().add(createTitle());
	container.getChildren().add(createFormContent());

	// Error message area
	container.getChildren().add(createStatusLog("                                            "));
	getChildren().add(container);
        populateFields();
        myModel.subscribe("EnterScoutViewError", this);
    }

        protected void populateFields()
	{
            firstNameField.setText("");
            lastNameField.setText("");
	}

        private MessageView createStatusLog(String initialMessage)
	{
            statusLog = new MessageView(initialMessage);
            return statusLog;
	}

        // Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
            HBox container = new HBox();
            container.setAlignment(Pos.CENTER);
            Text titleText = new Text(" Search Scouts ");
            titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            titleText.setWrappingWidth(300);
            titleText.setTextAlignment(TextAlignment.CENTER);
            titleText.setFill(Color.DARKGREEN);
            container.getChildren().add(titleText);
            return container;
	}

        // Create the main form content
	//-------------------------------------------------------------
	private GridPane createFormContent()
        {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));
            firstNameField = createInput(grid, firstNameField, "First Name:", 0);
            lastNameField = createInput(grid, lastNameField, "Last Name:", 1);
            createButton(grid, submitButton, "Submit", 3);
            createButton(grid, doneButton, "CANCEL", 4);
            return grid;
	}

         private TextField createInput(GridPane grid, TextField textfield, String label, Integer pos)
	{
            Label Author = new Label(label);
            grid.add(Author, 0, pos);
            textfield = new TextField();
            grid.add(textfield, 1, pos);
            return textfield;
	}

          private void createButton(GridPane grid, Button button, String nameButton, Integer pos)
	{
            button = new Button(nameButton);
            button.setId(Integer.toString(pos));
            if(nameButton=="CANCEL")
            {
              button.setOnAction(new EventHandler<ActionEvent>() {
              @Override
                  public void handle(ActionEvent e) {
                      myScout.done();
                  }
              });
            }
            else
            {
                button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                  public void handle(ActionEvent e) {
                      processAction(e);
                    }
                });
            }
            HBox btnContainer = new HBox(10);
            btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
            btnContainer.getChildren().add(button);
            if (pos == 3) {
                grid.add(btnContainer, 0, 4);
            }
            else {
                grid.add(btnContainer, 1, 4);
            }
	}
          public void processAction(Event evt)
	{
            Object source = evt.getSource();
            Button clickedBtn = (Button) source;
            if (clickedBtn.getId().equals("5") == true)
            {
            	myModel.stateChangeRequest("Done", null);
            }
            clearErrorMessage();
            String firstName = firstNameField.getText();
            if ((firstName == null) || (firstName.length() == 0))
            {
                displayErrorMessage("Please enter a scout!");
                firstNameField.requestFocus();
            }
	}

          public void displayMessage(String message)
	{
            statusLog.displayMessage(message);
	}

        public void displayErrorMessage(String message)
	{
            statusLog.displayErrorMessage(message);
	}

        public void updateState(String key, Object value)
	{
            if (key.equals("EnterScoutViewError") == true)
            {
		displayErrorMessage((String)value);
            }
	}

        public void clearErrorMessage()
	{
            statusLog.clearErrorMessage();
	}
}
