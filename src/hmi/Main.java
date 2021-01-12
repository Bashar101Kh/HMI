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

import org.json.JSONObject;

import java.lang.String;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        Console_Chat cc = new Console_Chat();
        cc.start();
    }
}