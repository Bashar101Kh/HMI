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
    private boolean run ;
    private String currentUserID;
    private String currentUserName;
    private ArrayList<ConversationThread> threads = new ArrayList<ConversationThread>();
    Iterator<ConversationThread> it = threads.iterator();

    //jo851hil TODO
    ThreadMessage sendThreadMessage;
    ThreadMessage receiveThreadMessage;
    HMI_utilities hmiUtils = new HMI_utilities();

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
        System.out.println("Welcome Bla Bla .. ");

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
                        ConversationThread conversationThread = new ConversationThread(cmd_chunks[1],"currentUser",cmd_chunks[2]);
                        threads.add(conversationThread);
                        conversationThread.open();

                    }else {
                        System.out.println("wrong arguments");
                    }

                }else if (len >= cmds.userlist.toString().length() + 1 && input.substring(1, cmds.userlist.toString().length() + 1).equals(cmds.userlist.toString())){
                    System.out.println("-userlist command erkannt");
                    //TODO impliment cmd

                }else if (len >= cmds.thread.toString().length()+1 && input.substring(1, cmds.thread.toString().length() + 1).equals(cmds.thread.toString())){
                    System.out.println("-thread command erkannt");
                    String[] cmd_chunks = input.split(" ");
                    /*if (cmd_chunks.length == 2 ){
                        argThreadTopic = cmd_chunks[1];
                        boolean threadFound = false;
                        while(it.hasNext()){
                            ConversationThread con = it.next();
                            if(con.getName().equals(argThreadTopic)){
                                threadFound = true;
                                con.open();
                                break;
                            }
                        }
                        if (!threadFound){
                            System.out.println("the specified thread could not be found !");
                        }
                    }*/
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
        System.out.println("tesssssssssssssssssssssssssssssssst");
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
        this.currentUserName = scanner.nextLine();
        this.currentUserID  = hmiUtils.generateUUID();

        //functionality to send data to STOR via Stream/Socket of Daemon-process
    }
    public void UserInit(){

    }
}
