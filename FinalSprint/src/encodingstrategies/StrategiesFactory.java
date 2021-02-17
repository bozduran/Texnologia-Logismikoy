package encodingstrategies;

public class StrategiesFactory {

    private EncodingStrategy ret;

    public StrategiesFactory(){

    }

    public EncodingStrategy createEncodingStrategy(String encoding) {

        if (encoding.equals("Rot13") ){
            ret = new Rot13Encoding();
            return ret;
        } else if (encoding.equals("AtBash")){
            ret = new AtBashEncoding();
            return ret;
        }
        return null;
    }

}

