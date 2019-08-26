package controller.controllers;

import controller.display.controller.AvailabilityOfPersons;
import controller.display.controller.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.sample.person.Person;
import model.sample.availability.data.month.Month;
import model.sample.availability.data.month.MonthCopier;
import model.xmldata.XMLReader;

import java.util.Enumeration;


public class NewScheduleController {

    @FXML
    private TextField year;

    @FXML
    private ChoiceBox<String> monthChoiceBox;

    @FXML
    public void initialize() {
        monthChoiceBox.getItems().addAll(
                "Styczeń", "Luty", "Marzec",
                "Kwiecień", "Maj", "Czerwiec",
                "Lipiec", "Sierpień", "Wrzesień",
                "Październik", "Listopad", "Grudzień");
        monthChoiceBox.setValue("Styczeń");
    }

    @FXML
    void handleCreate(ActionEvent buttonClicked) {
        createSchedule();
    }

    @FXML
    void handleEnterTextField(KeyEvent buttonEntered) {
        if(buttonEntered.getCode() == KeyCode.ENTER){
            createSchedule();
        }
    }

    private void createSchedule(){
        //year.getText(); monthChoiceBox.getValue()
        //sprawdz czy istnieje
        //sprawdza poprawnosc roku/miesiaca
        //if plik istnieje
        //T: wczytuje go
        //N:tworzy go, wczytuje go
        //pobiera odpowiedni miesiac
        //XMLReader wczytuje odpowiednie dane do zmiennej typu month
        try {
            checkYear(year.getText());
            if (!XMLReader.checkIfFileExist("./XMLyears/" + year.getText())) {
                createFile();
            }
            Month month = new XMLReader(monthChoiceBox.getValue(), year.getText()).getMonth();
            MessageBox.display("Grafik stworzony prawidłowo", (Stage) this.year.getScene().getWindow());
            MonthCopier monthCopier = new MonthCopier(month);
            Enumeration vectorEnumeration = Person.funkcja().elements();
            while(vectorEnumeration.hasMoreElements()) {
                Person personFromVector =(Person) vectorEnumeration.nextElement();
                personFromVector.setMonth(monthCopier.copyMonthTest());
            }

            AvailabilityOfPersons.display();
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.display(e.getMessage(), null);
        }
    }

    public static void checkYear(String year) throws Exception {
        if (Integer.parseInt(year) < 1900) {
            throw new Exception("Zły rok (rok > 1900)");
        }
    }

    private void createFile() throws Exception {
        Process XMLYearCreator = Runtime.getRuntime().exec("java -jar ./XMLYearCreator-1.0-SNAPSHOT.jar " + year.getText());
        XMLYearCreator.waitFor();
        checkFeedback(XMLYearCreator.exitValue());
    }

    private void checkFeedback(int feedback) throws Exception {
        if (feedback == 1)
            throw new Exception("Problem z utworzeniem pliku XML");
    }
}
