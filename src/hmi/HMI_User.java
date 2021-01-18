package hmi;

import java.util.ArrayList;
import java.util.UUID;

public class HMI_User {


    //Fields
    private String name;
    private String uniqueName;
    private UUID uuid;
    private ArrayList<HMI_ConversationThread> threads = new ArrayList<HMI_ConversationThread>();
    private ArrayList<HMI_User> friends = new ArrayList<HMI_User>();
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

    public ArrayList<HMI_User> getFriends() {
        return friends;
    }
    //Constructors
    public HMI_User(String name){
        this.name = name;
        uuid = UUID.randomUUID();
        uniqueName = name+"#"+uuid.toString().substring(0,4);

    }

    //Methods


}
