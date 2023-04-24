package it.polimi.ingsw.socket;

import java.io.IOException;
import java.util.ArrayList;
import it.polimi.ingsw.socket.Message;
public abstract class Observable {

    //list of observes
    private ArrayList<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void notifyObserver(Message message){
        observers.forEach(observer -> {
            try {
                observer.update(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
