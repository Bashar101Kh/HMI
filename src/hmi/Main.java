package hmi;

/******************************************************************
 * WORKPACKAGES:
 * 1. Command Lines -- DONE:
 *    - Nötige Command Lines für Testprogramm festlegen und Funktionalität definieren
 *
 * 2. Console aufbauen
 *    - Consolen Input auslesen und in Message Format (JSON & Byte[]) wandeln
 *    - Console Output
 *
 * 3. JSON & Byte[] als byte[] data verpacken (JSON Objekt umwandeln & Byte[] "anhängen", Klasse Data: Byte[])
 *
 * 4. Übergabe von ComMessage(Header: JSON, Data: Byte[]) and comAPI
 *
 * 5.
 *
 *
 ******************************************************************/

import java.lang.String;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) {

        String testString;

        testing();

        Message message = new Message();
        testString = message.getReceiverID();

        System.out.println(testString);


    }

    public static void testing(){

        String theInputString = "Ein normaler Text, den man alltäglich schreiben könnte. Je nachdem, sogar mit Zeilen-\n" +
                "Umbruch";

        byte[] byteArray;
        String theOutputString;

        System.out.println(theInputString);

        byteArray = StringToBinaryConversion.StringToBin(theInputString);

        theOutputString = StringToBinaryConversion.BinToString(byteArray);

        System.out.println(Arrays.toString(byteArray));

        System.out.println(theOutputString);

    }
}