package com.company;

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