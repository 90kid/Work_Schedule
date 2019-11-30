package controller.hbox.factory.person.NameToRefactor.days;

import controller.hbox.factory.person.AvailabilityOfPersons.EvenColours;
import controller.hbox.factory.person.IHBoxP;
import controller.hbox.factory.components.ButtonCreator;
import controller.hbox.factory.components.HBoxCreator;
import controller.hbox.factory.components.SeparatorCreator;
import controller.hbox.factory.components.VBoxCreator;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.sample.calendars.TypeOfDay;
import model.sample.person.Person;

public class NameToRefactorDaysHBoxP implements IHBoxP {

    private Person person;

    private int numberOfDays;

    NameToRefactorDaysHBoxP(Person person){
        this.person = person;
        this.numberOfDays = person.getAvailabilityMonth().getNumberOfDays();
    }

    @Override
    public HBox getHBox() {
        HBox hBox = new HBoxCreator.Builder()
                .setPrefHeight(64)
                .setPrefWidth(50)
                .setPrefWidth(Region.USE_COMPUTED_SIZE)
                .build();
        for (int i = 1; i <= numberOfDays; i++) {
            int j = i-1;

            Separator separatorBetweenDays = new SeparatorCreator.Builder()
                    .setPrefWidth(10.0)
                    .setVisible(true)
                    .build();

            VBox vBox = new VBoxCreator.Builder()
                    .setPrefHeight(64)
                    .setPrefWidth(32)
                    .build();

            Button numberOfDay = new ButtonCreator.Builder()
                    .setText("D")
                    .setPrefWidth(22)
                    .setStyle(person.getWorkingMonth().getDays().get(j).getDayTime().getColor())
                    .setOnAction(e -> {
                       /* person.getAvailabilityMonth().getDays().get(j).getDayTime().changeAvailability();
                        Button pressedButton = (Button) e.getSource();
                        pressedButton.setStyle(person.getAvailabilityMonth().getDays().get(j).getDayTime().getColor());*/
                    })
                    .build();
            Separator separatorBetweenTimeOfDay = new SeparatorCreator.Builder()
                    .setPrefHeight(5.0)
                    .setPrefWidth(3.0)
                    .setVisible(true)
                    .build();
            Button numberOfNight = new ButtonCreator.Builder()
                    .setText("N")
                    .setPrefWidth(22)
                    .setStyle(person.getWorkingMonth().getDays().get(j).getNightTime().getColor())
                    .setOnAction(e -> {
                       /* person.getAvailabilityMonth().getDays().get(j).getNightTime().changeAvailability();
                        Button pressedButton = (Button) e.getSource();
                        pressedButton.setStyle(person.getAvailabilityMonth().getDays().get(j).getNightTime().getColor());*/
                    })
                    .build();
            vBox.getChildren().addAll(numberOfDay, separatorBetweenTimeOfDay, numberOfNight);

            if(person.getWorkingMonth().getDays().get(i-1).getDayType() == TypeOfDay.HOLIDAY){
                vBox.setStyle("-fx-background-color: #809fff;");
            }
            HBox temp = new HBox();
            temp.getChildren().addAll(separatorBetweenDays, vBox);
            hBox.getChildren().addAll(temp);
        }
        hBox.setStyle(EvenColours.getColour(true));
        return hBox;
    }
}
