package hmi;

import org.json.JSONException;
import org.json.JSONObject;

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
        test,
    }
    //Fields
    //****************************************************
    //Only for testing
    private ArrayList<HMI_ConversationThread> a_threads = new ArrayList<HMI_ConversationThread>();
    private Iterator<HMI_ConversationThread> it = a_threads.iterator();
    //****************************************************
    private boolean run ;
    private User currentUser ;
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
        System.out.println("Welcome "+currentUser.getName()+"\n"+
                            "uuid: "+currentUser.getUuid()+"\n"+
                            "uniqueName: "+currentUser.getUniqueName());

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
                        HMI_ConversationThread HMIConversationThread = new HMI_ConversationThread(cmd_chunks[1],"currentUser",cmd_chunks[2]);
                        a_threads.add(HMIConversationThread);
                        currentThread = HMIConversationThread;
                        HMIConversationThread.open();

                    }else {
                        System.out.println("wrong arguments");
                    }

                }else if (len >= cmds.userlist.toString().length() + 1 && input.substring(1, cmds.userlist.toString().length() + 1).equals(cmds.userlist.toString())){
                    System.out.println("-userlist command erkannt");
                    //TODO impliment cmd

                }else if (len >= cmds.thread.toString().length()+1 && input.substring(1, cmds.thread.toString().length() + 1).equals(cmds.thread.toString())) {
                    System.out.println("-thread command erkannt");
                    String[] cmd_chunks = input.split(" ");
                    if (cmd_chunks.length == 2) {
                        argThreadTopic = cmd_chunks[1];
                        boolean threadFound = false;
                        for (int i = 0; i < a_threads.size(); i++) {
                            if (a_threads.get(i).getName().equals(argThreadTopic)) {
                                a_threads.get(i).open();
                                threadFound = true;
                                break;
                            }
                        }
                        if (!threadFound) {
                            System.out.println("the specified thread could not be found !");
                        }
                    }
                    if (cmd_chunks.length == 3) {
                        System.out.println("-thread [topic] [user] not implemented jet");
                    }

                    argThreadTopic = "";
                    argMsg = "";
                }else if (len >= cmds.test.toString().length() + 1 && input.substring(1, cmds.test.toString().length() + 1).equals(cmds.test.toString())) {
                    test();


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
        currentUser = new User(scanner.nextLine());
        //functionality to send data to STOR via Stream/Socket of Daemon-process
    }
    public void UserInit(){

    }

    public void test(){

        byte[] messageInByte;
        String testMsg = "A string meant for testing, because testing is fun.";
        JSONObject testCmd;
        HMI_ThreadMessage testSendMessage = new HMI_ThreadMessage(testMsg);
        HMI_ThreadMessage testReceiveMessage = new HMI_ThreadMessage();
        HMI_Directive testDirective;
        messageInByte = testSendMessage.messageToByte();
        System.out.println("\n----------Start of testing procedure----------\n");

        System.out.println("Test: Send a directive via stream socket." +
                "\nThe test ThreadMessage will be setup automatically with all fields and a dummy text message." +
                "\nPrinting the test ThreadMessage yields the following result (please note that printing the UUID and a formatted date is not part of current message feature):\n");
        testSendMessage.print();
        System.out.println("\nPlease provide the cmd in form of a formatted JSON, for example: {\"cmd\":\"example\",\"id\":\"1\",\"args\":{\"args1\":\"none\"}}");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        try {
            testCmd = new JSONObject(userInput);
            System.out.println("Passing "+testCmd.toString()+" as JSON to the directive.");
            testDirective = new HMI_Directive(testCmd,messageInByte);
            //TODO Send Directive via Stream, write method for Receive Directive deconstruction into ThreadMessage
            System.out.println("\nCurrently no socket/stream is being used to transfer this HMI_Directive. It is simply deconstructed back into a ThreadMessage.");
            testReceiveMessage.getThreadMessageFromHmiDirective(testDirective);
            System.out.println("\nPrinting the deconstructed ThreadMessage\n");
            testReceiveMessage.print();
            System.out.println("\n----------End of testing procedure----------\n\n");
        }catch(JSONException err){
            System.out.println("Incorrectly formatted JSONObject.");
        }
    }
}
