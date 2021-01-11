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


}

