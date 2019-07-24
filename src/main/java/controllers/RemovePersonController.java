package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.People;

import java.util.Enumeration;


public class RemovePersonController {

    @FXML
    private VBox vBox;

    @FXML
    public void initialize(){
        vBox.getChildren().add(getSeparatorHeight());

        Enumeration vectorEnumeration = People.funkcja().elements();
        while(vectorEnumeration.hasMoreElements()){
            addPersonToVBox((People) vectorEnumeration.nextElement());
           // System.out.println(vectorEnumeration.nextElement());
        }

    }

    private void addPersonToVBox(People p){
        vBox.getChildren().add(getHBox(p));

    }
    private HBox getHBox(People p){
        HBox hBox = new HBox();
        hBox.setPrefHeight(30.0);
        hBox.setPrefWidth(260.0);
        hBox.getChildren().addAll(getLabel(p), getSeparatorWidth(), getButton(p));
        return hBox;
    }

    private Label getLabel(People p){
        Label label = new Label();
        label.setPrefWidth(180.0);
        label.setText(p.getImie() + " " + p.getNazwisko());
        return label;
    }

    private Separator getSeparatorWidth(){
        Separator separator = new Separator();
        separator.setOpacity(0.0);
        separator.setPrefWidth(20.0);
        return separator;
    }

    private Separator getSeparatorHeight(){
        Separator separator = new Separator();
        separator.setOpacity(0.0);
        separator.setPrefWidth(20.0);
        return separator;
    }

    private Button getButton(People p){
        Button button = new Button();
        button.setMnemonicParsing(false);
        button.setText("Usun");
        button.setId(p.getImie());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                People.funkcja().remove(p);
                vBox.getChildren().clear();
                initialize();
            }
        });
        return button;
    }

}
