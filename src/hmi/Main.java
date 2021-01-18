package hmi;

/******************************************************************
 * WORKPACKAGES:
 * 1. Command Lines -- DONE:
 *    - Nötige Command Lines für Testprogramm festlegen und Funktionalität definieren - erledigt
 *
 * 2. Console aufbauen
 *    - Consolen Input auslesen und in Message Format (JSON & Byte[]) wandeln - erledigt
 *    - Console Output - "send Funktion" - 1. - erledigt
 *    - Console Output - dummy Nachricht festlegen & print Funktion dafür auswerfen - erledigt
 *
 * 3. JSON & Byte[] als byte[] data verpacken (JSON Objekt umwandeln & Byte[] "anhängen", Klasse Data: Byte[])
 *    - erledigt
 *
 * 4. Übergabe von ComMessage(Header: JSON, Data: Byte[]) and comAPI
 *    TODO Abwarten der einheitlichen comMessage Klasse und Funktionalität
 *
 * 5. Neu-Strukturierung der Console_Chat Klasse , keine feste Codierung der OutputStrings
 *
 * 6.
 *
 *
 *
 ******************************************************************/

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;
import java.util.Arrays;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) {

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





        Console_Chat cc = new Console_Chat();
        cc.registerUser();
        cc.start();
    }
}