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
    private ArrayList<User> firends = new ArrayList<User>();
    //Constructors
    public User(String name){
        this.name = name;
        uuid = UUID.randomUUID();
        uniqueName = name+"#"+uuid.toString().substring(0,4);

    }

    //Methods


}
