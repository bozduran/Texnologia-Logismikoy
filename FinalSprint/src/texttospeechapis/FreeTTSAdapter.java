package texttospeechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {
    private VoiceManager vm;
    private Voice voice;

    public FreeTTSAdapter(){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin16");
        if (voice != null){
            voice.allocate();
        }
    }

    @Override
    public void play(String contentToPlay) {
        voice.speak(contentToPlay);
    }

    @Override
    public void setVolume(int volumeValue) {
        voice.setVolume( (float)volumeValue/100 );
    }

    @Override
    public void setPinch(int pinchValue) {
        voice.setPitchRange((float)pinchValue);
    }

    @Override
    public void setRate(int rateValue) {
        voice.setRate((float)rateValue);
    }
}
