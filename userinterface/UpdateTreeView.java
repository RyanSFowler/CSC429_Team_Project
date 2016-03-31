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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author florianjousselin
 */
public class UpdateTreeView extends View {

    private TextField barcode;
    private TextArea notes;
    protected Button cancel;
    protected Button submit;
    
    public UpdateTreeView(IModel model) {
        super(model, "UpdateTreeView");
        displayWindow();
        
    }
    
    
    public void displayWindow()
    {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);	
        container.setPadding(new Insets(15, 5, 5, 5));
        container.getChildren().add(createTitle());
	container.getChildren().add(createFormContent());
        //container.getChildren().add(createStatusLog("                                            "));
	getChildren().add(container);
        populateFields();
        myModel.subscribe("UpdateTreeError", this);
    }
    
    public Node createTitle()
    {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        Text titleText = new Text("Update TREE'S INFORMATIONS");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setWrappingWidth(300);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);
        container.getChildren().add(titleText);
        return container;
    }
    
    private GridPane createFormContent()
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        barcode = createInput(grid, barcode, "Barcode:", 0);
        notes = new TextArea();
        notes.setDisable(false);
        notes.setWrapText(true);
        notes.setPrefSize(300, 100);
        grid.add(notes, 1, 2);
        createButton(grid, submit, "Submit", 1, 4, 1);
        createButton(grid, cancel, "Cancel", 0, 4, 2);
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
    
    private void createButton(GridPane grid, Button button, String nameButton, Integer pos1, Integer pos2, Integer id)
    {
        button = new Button(nameButton);
        button.setId(Integer.toString(id));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent e) {
                    processAction(e);
                }
            });
        HBox btnContainer = new HBox(10);
        btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
        btnContainer.getChildren().add(button);
        grid.add(btnContainer, pos1, pos2);
    }
    
    public void processAction(Event evt)
    {
        Object source = evt.getSource();
        Button clickedBtn = (Button) source;
        if (clickedBtn.getId().equals("1") == true)
        {
            if (barcode.getText().isEmpty() == true)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("You have an entry error:");
                alert.setContentText("You need to provide a barcode to update tree's information.");
                alert.showAndWait();
            }
            else if (notes.getText().isEmpty() == true)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("You have an entry error:");
                alert.setContentText("You need to provide notes about the tree to update tree's information.");
                alert.showAndWait();
            }
            else
            {
                // SUBMIT
            }
        }
        else if (clickedBtn.getId().equals("2") == true)
        {
            myModel.stateChangeRequest("Done", null);
        }
    }
    
    protected void populateFields()
    {
        barcode.setText("");
        notes.setText("");
    }
    
    @Override
    public void updateState(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
