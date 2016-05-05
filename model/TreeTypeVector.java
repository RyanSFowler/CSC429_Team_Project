/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;


public class TreeTypeVector {

private final SimpleStringProperty barcodePrefix;
private final SimpleStringProperty typeDescription;
private final SimpleStringProperty cost;

 public TreeTypeVector(Vector<String> book)
    {
	barcodePrefix =  new SimpleStringProperty(book.elementAt(0));
	typeDescription =  new SimpleStringProperty(book.elementAt(1));
  cost =  new SimpleStringProperty(book.elementAt(2));
    }

    public void setBarcodePrefix(String BarcodePrefix) {
        barcodePrefix.set(BarcodePrefix);
    }

    public void setTypeDescription(String TypeDescription) {
        typeDescription.set(TypeDescription);
    }

    public void setCost(String Cost) {
        cost.set(Cost);
    }

    public String getBarcodePrefix() {
        return barcodePrefix.get();
    }

    public String getTypeDescription() {
        return typeDescription.get();
    }

    public String getCost() {
        return cost.get();
    }

}
