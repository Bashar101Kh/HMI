package hmi;

import java.util.ArrayList;
import java.util.UUID;

public class ConversationThread {


    //Fields
    private String name ;
    private UUID uuid;
    private ArrayList<String> membersIDs = new ArrayList<String>();
    private ArrayList<HMI_ThreadMessage> msgHistory = new ArrayList<HMI_ThreadMessage>();

    private String ui ;

    //getter und setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Constructor
    public ConversationThread(String topic, String currentUser, String user){
        membersIDs.add(currentUser);
        membersIDs.add(user);
        this.name = topic;

    }

    public void open(){
        Console_Chat.clearScreen();
        System.out.println("****************************************************\n* "+
                           this.name+
                           "\n****************************************************");
    }

    public void append(HMI_ThreadMessage m){
        msgHistory.add(m);
    }

}
