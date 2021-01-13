package hmi;

/******************************************************************
 * WORKPACKAGES:
 * 1. Command Lines -- DONE:
 *    - Nötige Command Lines für Testprogramm festlegen und Funktionalität definieren - erledigt
 *
 * 2. Console aufbauen
 *    - Consolen Input auslesen und in Message Format (JSON & Byte[]) wandeln - erledigt
 *    - TODO Console Output - "send Funktion" - 1.
 *    - TODO Console Output - dummy Nachricht festlegen & print Funktion dafür auswerfen
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

import org.json.JSONObject;

import java.lang.String;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        Console_Chat cc = new Console_Chat();
        cc.start();
    }
}