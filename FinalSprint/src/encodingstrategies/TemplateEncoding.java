package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {
    public TemplateEncoding(){

    }
    public String encode(String stringToEncode){
        return stringToEncode;
    }

    abstract protected char mapCharacter(char letter);
}
