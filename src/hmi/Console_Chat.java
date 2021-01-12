package hmi;

import java.io.IOException;
import java.util.Scanner;

public class Console_Chat {

    //Fields
    private final String[] cmds = { "-help",
                                    "-user",
                                    "-m",
                                    "-exit" };
    private boolean run ;
    Message message;
    //Constructor
    public Console_Chat(){
        message = new Message();


    }

    //Methods
    void start(){
        run = true;
        String input,argUser;
        String argMsg = "";
        int len = 0 ;
        System.out.println("Welcome Bla Bla .. ");

        Scanner scanner = new Scanner(System.in);
        while (run){
            input = scanner.nextLine();
            if (!input.isEmpty()){
                len = input.length();
            }
                // boolean a ? [if true]statment1 : [if false]statment 2 ;
                // -m [userName] [Message]
                // -user -[userName]
                if(len >= cmds[0].length() ? input.substring(0,cmds[0].length()).equals(cmds[0]) : false){
                    clearScreen();
                    System.out.println("The following commands are available:" +
                            "\n-help : list all commands" +
                            "\n-user : list all available contacts" +
                            "\n-user UserName : open chat with user" +
                            "\n-user UserName plainText : send plain text message to user" +
                            "\n-m plainText : when a user chat is opened with '-user Username' a plainText message is sent" +
                            "\n-exit : close the program");
                }else if (len >= cmds[1].length() ? input.substring(0,cmds[1].length()).equals(cmds[1]) : false){
                    System.out.println("-user erkannt");
                    String[] cmd_chunks = input.split(" ");
                    argUser = cmd_chunks [1];
                    for (int i = 2 ; i < cmd_chunks.length ; i++){
                        argMsg += (i != cmd_chunks.length-1) ? (cmd_chunks[i]+" ") : (cmd_chunks[i]) ;

                        //jo851hil
                        message.setPlaintTextContent(argMsg);

                    }

                    //send /view history
                    System.out.println("Message:"+argMsg);
                    System.out.println("User:"+argUser);
                    //nach dem senden löschen
                    argUser ="";
                    argMsg = "";
                }else if (len >= cmds[2].length() ? input.substring(0,cmds[2].length()).equals(cmds[2]): false){
                    System.out.println("-user not implemented jet");
                }else if (len >= cmds[3].length() ? input.substring(0,cmds[3].length()).equals(cmds[3]): false){
                    run = false;
                }else {
                    System.out.println("Default");
                }
                input = null;
        }
    }

    //TODO
    public static void clearScreen(){

    }
}
