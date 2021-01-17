package hmi;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class HMI_utilities {

    public static byte[] stringToBytes(String inputString){

        Charset charset = StandardCharsets.UTF_8;
        byte[] byteArray = inputString.getBytes(charset);

        return byteArray;
    }

    public static String bytesToString(byte[] byteArray) {

        Charset charset = StandardCharsets.UTF_8;
        String string = charset.decode(ByteBuffer.wrap(byteArray))
                .toString();

        return string;
    }

    public static String generateUUID(){

        String uuidString;
        UUID uuid = UUID.randomUUID();
        uuidString = uuid.toString();

        return uuidString;
    }

    //Returns the index of the last '}' char from a JSON string from a concatenated string
    public static int getJSONIndexFromString(String input){

        int counter = 0;
        int i,n=0;
        char[] ca;
        char ch;
        ca = input.toCharArray();
        for(i = 0; i<ca.length; i++){
            if(ca[i]=='{')
                counter++;
            if(ca[i]=='}'){
                counter--;
                n=i;
            }
        }
        if(counter != 0) {
            System.out.println("Error in comMessage String, could not retrieve proper JSON");
            return 0;
        }
        else{
            return n;
        }
    }
}

