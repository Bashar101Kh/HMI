package hmi;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class HMI_utilities {

    public byte[] StringToBytes(String inputString){

        Charset charset = StandardCharsets.UTF_8;
        byte[] byteArray = inputString.getBytes(charset);

        return byteArray;
    }

    public String BytesToString(byte[] byteArray) {

        Charset charset = StandardCharsets.UTF_8;
        String string = charset.decode(ByteBuffer.wrap(byteArray))
                .toString();

        return string;
    }

    public String generateUUID(){

        String uuidString;
        UUID uuid = UUID.randomUUID();
        uuidString = uuid.toString();

        return uuidString;
    }

    //Create a Message Object from the comMessage byte[] data block
    public ThreadMessage extractDataIPC(HMI_Directive HMIDirective){

        //Convert comMessage byte[] data block byte[] content and fill message object
        ThreadMessage recThreadMessage = new ThreadMessage();
        JSONObject  messageJSON = null;
        byte[] comData;
        String comDataString;
        String messageHeaderString;
        String messageDataString;
        int arrayPos;

        //TODO
        //Split data into byte[] JSONHeader and  byte[] data, this would require an index
        //OR convert byte array to string, extract {..} for JSON section and use consecutive section as plaintext

        comData = HMIDirective.getData();
        comDataString = BytesToString(comData);

        //Get last index of JSON String ('}'), Split comDataString at this index into two separate strings
        arrayPos = getJSONIndexFromString(comDataString);
        messageHeaderString = comDataString.substring(0,arrayPos+1);
        messageDataString = comDataString.substring(arrayPos+1,comDataString.length());

        //Print for testing purposes
//        System.out.println(messageHeaderString);
//        System.out.println(messageDataString);

        //Convert string of JSONObject into JSONObject
        //TODO try catch, proper catch logic
        try {
            messageJSON = new JSONObject(messageHeaderString);
        }catch(JSONException err){
            System.out.println("Exception while creating JSONObject from comMessage");
        }
        //Give Message parameters to JSON
        recThreadMessage.createMessageFromJSON(messageJSON);
        recThreadMessage.setHeader(messageJSON);
        recThreadMessage.setContent(messageDataString.getBytes(StandardCharsets.UTF_8));
        recThreadMessage.setPlainTextContent(messageDataString);

        return recThreadMessage;
    }

    //Returns the index of the last '}' char from a JSON string from a concatenated string
    public int getJSONIndexFromString(String input){

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

