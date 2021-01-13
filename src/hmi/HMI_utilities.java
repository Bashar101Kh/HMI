package hmi;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class HMI_utilities {

    public byte[] StringToBin(String inputString){

        Charset charset = StandardCharsets.UTF_8;
        byte[] byteArray = inputString.getBytes(charset);

        return byteArray;
    }

    public String BinToString(byte[] byteArray) {

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

    //
    public Message extractDataIPC(ComMessage comMessage){

        //convert comMessage byte[] data block byte[] content and fill message object
        Message recMessage = new Message();
        byte[] recData;
        String dataString;
        String headerString;
        String messageString;
        int arrayPos;

        //TODO
        //split data into byte[] JSONHeader and  byte[] data
        //OR convert byte array to string, extract {..} for JSON section and use consecutive section as plaintext

        recData = comMessage.getData();

        dataString = BinToString(recData);

        //split string at JSON end and split into two strings
        arrayPos= getJSONIndexFromString(dataString);
        headerString = dataString.substring(0,arrayPos+1);
        messageString = dataString.substring(arrayPos+1,dataString.length());
        System.out.println(arrayPos);
        System.out.println(headerString);
        System.out.println(messageString);


        return recMessage;
    }

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
            System.out.println("Error in JSON");
            return 0;
        }
        else{
            return n;
        }
    }
}

