package model.sample.availability.calendar.day;

import model.sample.availability.calendar.day.components.AvailabilityDayTime;
import model.sample.availability.calendar.day.components.AvailabilityNightTime;

public class AvailabilityDay implements Cloneable {
    private AvailabilityDayTime availabilityDayTime;
    private AvailabilityNightTime availabilityNightTime;
    private TypeOfAvailabilityDay dayType;
    private int numberOfDay;

    public AvailabilityDay(int numberOfDay, TypeOfAvailabilityDay dayType){
        this.numberOfDay = numberOfDay;
        this.dayType = dayType;
        this.availabilityDayTime = new AvailabilityDayTime();
        this.availabilityNightTime = new AvailabilityNightTime();
    }

    public TypeOfAvailabilityDay getDayType(){
        return this.dayType;
    }

    public int getNumberOfDay(){
        return numberOfDay;
    }

    public AvailabilityDayTime getAvailabilityDayTime() {
        return availabilityDayTime;
    }

    public AvailabilityNightTime getAvailabilityNightTime() {
        return availabilityNightTime;
    }

    public AvailabilityDay clone(){
        try{
            AvailabilityDay temp = (AvailabilityDay) super.clone();
            temp.availabilityDayTime = temp.availabilityDayTime.clone();
            temp.availabilityNightTime = temp.availabilityNightTime.clone();
            return temp;
        }
        catch (Exception ex){
            return null;
        }
    }
}
