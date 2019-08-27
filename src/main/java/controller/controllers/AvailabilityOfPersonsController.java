package controller.controllers;

import controller.hbox.factory.AvailabilityOfPersons.days.AvailabilityOfPersonsDaysHBoxFactory;
import controller.hbox.factory.AvailabilityOfPersons.names.AvailabilityOfPersonsNamesHBoxFactory;
import controller.hbox.factory.IHBox;
import controller.hbox.factory.IHBoxFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import model.sample.person.Person;

import java.util.Enumeration;

public class AvailabilityOfPersonsController {

    @FXML
    private SplitPane splitPane;

    @FXML
    private VBox nameVBox;

    @FXML
    private VBox daysVBox;

    @FXML
    private ScrollPane namesScrollPane;

    @FXML
    private ScrollPane daysScrollPane;

    public void initialize(){
        IHBoxFactory hBoxNamesFactory = new AvailabilityOfPersonsNamesHBoxFactory();
        IHBoxFactory hBoxDaysFactory = new AvailabilityOfPersonsDaysHBoxFactory();
        Enumeration vectorEnumeration = Person.funkcja().elements();
        while(vectorEnumeration.hasMoreElements()){
            Person personFromVector =(Person) vectorEnumeration.nextElement();
            IHBox generatedDayHBox = hBoxDaysFactory.makeHBox(personFromVector);
            daysVBox.getChildren().add(generatedDayHBox.getHBox());
            IHBox generatedNameHBox = hBoxNamesFactory.makeHBox(personFromVector);
            nameVBox.getChildren().add(generatedNameHBox.getHBox());
        }
        daysScrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                namesScrollPane.vvalueProperty().setValue(new_val.doubleValue());
            }
        });
        int numberOfDays = Person.funkcja().get(0).getAvailabilityMonth().getDaysOfTheMonth().size();
        splitPane.setDividerPositions((double) (0.295 - ((31 - numberOfDays)  * 0.0066)));
    }
}
