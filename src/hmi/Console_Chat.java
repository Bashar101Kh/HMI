package hmi;

import java.io.IOException;
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
            if (!input.isEmpty()){
                len = input.length();
            }else{
                System.out.println("empty input !");

            }
                // boolean a ? [if true]statment1 : [if false]statment 2 ;
                // -m [userName] [Message]
                // -user -[userName]
                if(input.equals("-"+cmds.help)){
                    System.out.println(cmdHelp);
                //len >= cmds[0].length() ? input.substring(0,cmds[0].length()).equals(cmds[0]) : false
                }else if (len >= cmds.newthread.toString().length() ? input.substring(1,cmds.newthread.toString().length()+1).equals(cmds.newthread.toString()) :false ){
                    System.out.println("-newthread command erkannt");
                    //TODO impliment cmd
                    String[] cmd_chunks = input.split(" ");
                    if (cmd_chunks.length == 3 ){                 // create thread
                        ConversationThread conversationThread = new ConversationThread(cmd_chunks[1],"currentUser",cmd_chunks[2]);
                        conversationThread.open();
                    }else System.out.println("wrong arguments");

                }else if (input.equals("-"+ cmds.userlist)){
                    System.out.println("-userlist command erkannt");
                    //TODO impliment cmd

                }else if (input.equals("-"+ cmds.thread)){
                    System.out.println("-thread command erkannt");
                    //TODO impliment cmd
                    String[] cmd_chunks = input.split(" ");
                    if (input.length() == 1){                                   // extract the cmd
                        System.out.println("you need to provide arguments");
                        break;
                    }
                    if (input.length() == 2){                                   // extract arg1
                        argThreadTopic = cmd_chunks[1];
                        System.out.println("argThreadTopic: "+argThreadTopic);
                        //TODO concret implemntation
                    }
                    if (cmd_chunks.length>2) {                                   // combine the rest as arg2
                        for (int i = 2; i < cmd_chunks.length; i++) {
                            argMsg += (i != cmd_chunks.length - 1) ? (cmd_chunks[i] + " ") : (cmd_chunks[i]);
                        }
                        //TODO concret implemntation

                        argThreadTopic = "";
                        argMsg = "";
                    }

                }else if (input.equals("-"+ cmds.clear)){
                    clearScreen();

                }else if (input.equals("-"+ cmds.exit)){
                    System.out.println("quitting..");
                    run = false;
                }else {
                    System.out.println("Default");
                }
                input = null;
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
        this.currentUserName = scanner.nextLine();
        this.currentUserID  = hmiUtils.generateUUID();

        //functionality to send data to STOR via Stream/Socket of Daemon-process
    }
    public void UserInit(){

    }
}
