package hmi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Console_Chat {
    enum cmds {
        help,
        exit,
        newthread,
        clear,
        thread,
        userlist,
    }
    //Fields
    //****************************************************
    //Only for testing
    private ArrayList<HMI_ConversationThread> a_threads = new ArrayList<HMI_ConversationThread>();
    private Iterator<HMI_ConversationThread> it = a_threads.iterator();
    //****************************************************
    private boolean run ;
    private HMI_User currentHMIUser;
    private static HMI_ConversationThread currentThread;


    //jo851hil TODO
    HMI_ThreadMessage sendHMIThreadMessage;
    HMI_ThreadMessage receiveHMIThreadMessage;

    private String cmdHelp = "The following commands are available:" +
                             "\n-help : list all commands" +
                             "\n-thread [threadTopic] : opens the specified Thread" +
                             "\n-thread [threadTopic] [msg:String] : send a String Message to the specified Thread" +
                             "\n-userlist [LocalIP] : gets the availabe users on the specified IP adress" +
                             "\n-newthread [threadTopic] [UserID] : creates a new Thread with the specified user"+
                             "\n-exit : close the program";
    //Constructor
    public Console_Chat(){


    }

    //Methods
    void start(){
        run = true;
        String input, argThreadTopic, argMsg;
        argThreadTopic = "";
        argMsg = "";
        int len = 0 ;
        System.out.println("Welcome "+ currentHMIUser.getName()+"\n"+
                            "uuid: "+ currentHMIUser.getUuid()+"\n"+
                            "uniqueName: "+ currentHMIUser.getUniqueName());

        Scanner scanner = new Scanner(System.in);
        while (run){
            input = scanner.nextLine();
            len = input.length();
            if (!input.isEmpty()){

                if(input.equals("-"+cmds.help)){
                    System.out.println(cmdHelp);
                    //len >= cmds[0].length() ? input.substring(0,cmds[0].length()).equals(cmds[0]) : false
                }else if (len >= cmds.newthread.toString().length() + 1 && input.substring(1, cmds.newthread.toString().length() + 1).equals(cmds.newthread.toString())){
                    System.out.println("-newthread command erkannt");
                    //TODO impliment cmd
                    String[] cmd_chunks = input.split(" ");
                    if (cmd_chunks.length == 3 ){                 // create thread
                        HMI_ConversationThread HMIConversationThread = new HMI_ConversationThread(cmd_chunks[1],"currentHMIUser",cmd_chunks[2]);
                        a_threads.add(HMIConversationThread);
                        currentThread = HMIConversationThread;
                        HMIConversationThread.open();

                    }else {
                        System.out.println("wrong arguments");
                    }

                }else if (len >= cmds.userlist.toString().length() + 1 && input.substring(1, cmds.userlist.toString().length() + 1).equals(cmds.userlist.toString())){
                    System.out.println("-userlist command erkannt");
                    //TODO impliment cmd

                }else if (len >= cmds.thread.toString().length()+1 && input.substring(1, cmds.thread.toString().length() + 1).equals(cmds.thread.toString())){
                    System.out.println("-thread command erkannt");
                    String[] cmd_chunks = input.split(" ");
                    if (cmd_chunks.length == 2 ){
                        argThreadTopic = cmd_chunks[1];
                        boolean threadFound = false;
                        for (int i = 0 ; i < a_threads.size(); i++){
                            if(a_threads.get(i).getName().equals(argThreadTopic)){
                                a_threads.get(i).open();
                                threadFound = true;
                                break;
                            }
                        }
                        if (!threadFound){
                            System.out.println("the specified thread could not be found !");
                        }
                    }
                    if (cmd_chunks.length == 3){
                        System.out.println("-thread [topic] [user] not implemented jet");
                    }

                    argThreadTopic = "";
                    argMsg = "";
                }else if (input.equals("-"+ cmds.exit)){
                    System.out.println("quitting..");
                    run = false;
                }else if (input.equals("-"+ cmds.clear)){
                    clearScreen();

                }else {
                    System.out.println("Default");
                }
                input = null;
            }else{
                System.out.println("empty input !");

            }

        }
    }

    public static void clearScreen(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(){

        System.out.println("Please enter your desired username");
        Scanner scanner = new Scanner(System.in);

        //check if input correct and in limitations
        currentHMIUser = new HMI_User(scanner.nextLine());
        //functionality to send data to STOR via Stream/Socket of Daemon-process
    }
    public void UserInit(){

    }
}
