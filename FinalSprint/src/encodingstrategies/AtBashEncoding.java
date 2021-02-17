package encodingstrategies;

import java.util.HashMap;

public class AtBashEncoding extends TemplateEncoding {


    public AtBashEncoding(){

    }


    @Override
    public String encode(String stringToEncode) {
        String encodedString = "";
        char c ;
        for (int i = 0; i < stringToEncode.length(); i++){
            c = mapCharacter(stringToEncode.charAt(i));
            encodedString += String.valueOf(c);
        }
        return encodedString;
    }

    protected char mapCharacter(char letter){
        char characteToReturn = 0;
        if (letter >= 'A' && letter <= 'Z') {
            characteToReturn = (char) ('Z'-(letter - 'A')) ;
        } else if (letter >= 'a' && letter <= 'z') {
            characteToReturn = (char) ('z'-(letter - 'a'));
        }
        return characteToReturn;

    }


}
