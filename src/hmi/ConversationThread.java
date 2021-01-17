package hmi;

import java.util.ArrayList;

public class ConversationThread {


    //Fields
    private String name ;
    private ArrayList<String> membersIDs = new ArrayList<String>();
    private ArrayList<ThreadMessage> msgHistory = new ArrayList<ThreadMessage>();

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

}
