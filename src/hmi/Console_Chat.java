package hmi;

import java.util.Scanner;

public class Console_Chat {
    //Fields
    boolean run ;



    //Constructor


    //Methods
    void start(){
        run = true;
        String input ;
        System.out.println("Welcome Bla Bla .. ");

        Scanner scanner = new Scanner(System.in);
        while (run){
            input = scanner.nextLine();
            switch (input){
                case "-help":   //list all commands
                    System.out.println("no list jet");
                    break;
                case "-m":      //send Message
                    System.out.println("-m not implemented jet");
                    break;
                case "-user":   //select user
                    System.out.println("-user not implemented jet");
                    break;
            }
        }
    }
}
