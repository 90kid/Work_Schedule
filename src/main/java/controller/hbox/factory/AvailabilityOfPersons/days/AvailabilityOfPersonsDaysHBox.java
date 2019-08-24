package controller.hbox.factory.AvailabilityOfPersons.days;

import controller.hbox.factory.AvailabilityOfPersons.EvenColours;
import controller.hbox.factory.IHBox;
import controller.hbox.factory.components.*;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.sample.person.Person;
import model.sample.calendar.day.TypeOfDay;

public class AvailabilityOfPersonsDaysHBox implements IHBox {

    private Person person;

    private int numberOfDays;

    AvailabilityOfPersonsDaysHBox(Person person) {
        this.person = person;
        this.numberOfDays = person.getMonth().getDaysOfTheMonth().size();
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
                    .setPrefWidth(20.0)
                    .setVisible(true)
                    .build();

            VBox vBox = new VBoxCreator.Builder()
                    .setPrefHeight(64)
                    .setPrefWidth(50)
                    .build();

            Button numberOfDay = new ButtonCreator.Builder()
                    .setText(String.valueOf(i) + "D")
                    .setPrefWidth(45)
                    .setStyle(person.getMonth().getDaysOfTheMonth().get(j).getDayTime().getColor())
                    .setOnAction(e -> {
                        person.getMonth().getDaysOfTheMonth().get(j).getDayTime().changeAvailability();
                        Button temp = (Button) e.getSource();
                        temp.setStyle(person.getMonth().getDaysOfTheMonth().get(j).getDayTime().getColor());
                    })
                    .build();
            Separator separatorBetweenTimeOfDay = new SeparatorCreator.Builder()
                    .setPrefHeight(5.0)
                    .setPrefWidth(3.0)
                    .setVisible(true)
                    .build();
            Button numberOfNight = new ButtonCreator.Builder()
                    .setText(String.valueOf(i) + "N")
                    .setPrefWidth(45)
                    .setStyle(person.getMonth().getDaysOfTheMonth().get(j).getNightTime().getColor())
                    .setOnAction(e -> {
                        person.getMonth().getDaysOfTheMonth().get(j).getNightTime().changeAvailability();
                        Button temp = (Button) e.getSource();
                        temp.setStyle(person.getMonth().getDaysOfTheMonth().get(j).getNightTime().getColor());
                    })
                    .build();
            vBox.getChildren().addAll(numberOfDay, separatorBetweenTimeOfDay, numberOfNight);

            if(person.getMonth().getDaysOfTheMonth().get(i-1).getDayType() == TypeOfDay.HOLIDAY){
                vBox.setStyle("-fx-background-color: #0033cc;");
            }
            HBox temp = new HBox();
            temp.getChildren().addAll(separatorBetweenDays, vBox);
            hBox.getChildren().addAll(temp);
        }
        hBox.setStyle(EvenColours.getColour(true));
        return hBox;
    }
}
