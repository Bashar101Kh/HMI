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
    private ArrayList<HMI_User> membersIDs = new ArrayList<HMI_User>();
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
    public HMI_ConversationThread(String topic, HMI_User currentUser, HMI_User user){
        membersIDs.add(currentUser);
        membersIDs.add(user);
        this.name = topic;
        //*************************
        // adding dummy data to the
        // msgHistory only for testing!
        //*************************
        for(int i = 0; i< 10;i++){
            msgHistory.add(new HMI_ThreadMessage(Integer.toString(i)+" blablabla"));
        }
        //*************************

    }

    public void open(){
        Console_Chat.clearScreen();
        System.out.println("****************************************************\n* "+
                           this.name+
                           "\n****************************************************");
        for(int i = 0 ; i< msgHistory.size(); i++){
            msgHistory.get(i).print();
        }
    }

    // append Method should only be used for testing
    // later it must be done over Storage
    // when a new msg is inputed by the user the we inform
    // storage and request the new History list of the thread
    public void append(HMI_ThreadMessage m){
        msgHistory.add(m);
    }

}
