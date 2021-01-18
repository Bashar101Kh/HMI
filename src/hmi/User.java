package hmi;

import jdk.dynalink.linker.ConversionComparator;

import java.util.ArrayList;
import java.util.UUID;

public class User {


    //Fields
    private String name;
    private String uniqueName;
    private UUID uuid;
    private ArrayList<HMI_ConversationThread> threads = new ArrayList<HMI_ConversationThread>();
    private ArrayList<User> friends = new ArrayList<User>();
    //getters
    public String getName() {
        return name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<HMI_ConversationThread> getThreads() {
        return threads;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
    //Constructors
    public User(String name){
        this.name = name;
        uuid = UUID.randomUUID();
        uniqueName = name+"#"+uuid.toString().substring(0,4);

    }

    //Methods


}
