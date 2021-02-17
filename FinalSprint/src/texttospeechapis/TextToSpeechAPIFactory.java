package texttospeechapis;

public class TextToSpeechAPIFactory {
    private TextToSpeechAPI ttsAPI;
    public TextToSpeechAPIFactory(){

    }

    public TextToSpeechAPI createTTSAPI(String api){

        if (api.equals("FreeTTSAdapter")){
            ttsAPI = new FreeTTSAdapter();
            return ttsAPI;
        } else if (api.equals("FakeTextToSpeechAPI")){
            ttsAPI = new FakeTextToSpeechAPI();
            return ttsAPI;
        }
        return null;
    }


}
