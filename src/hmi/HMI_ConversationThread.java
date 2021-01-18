package hmi;

import java.util.ArrayList;
import java.util.UUID;

/*
*   this is the Main Container of a Conversation History between 2 or more users
*/
public class HMI_ConversationThread {


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
    public HMI_ConversationThread(String topic, String currentUser, String user){
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
