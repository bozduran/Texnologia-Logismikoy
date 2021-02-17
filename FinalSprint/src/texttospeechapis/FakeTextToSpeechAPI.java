package texttospeechapis;

import java.util.ArrayList;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {


    private ArrayList<String> contentToPlay = new ArrayList<String>();
    private int volumeValue;
    private int pinchValue;
    private int rateValue;

    public FakeTextToSpeechAPI(){

    }

    @Override
    public void play(String contentToPlay) {
        this.contentToPlay.add(contentToPlay);
    }

    @Override
    public void setVolume(int volumeValue) {
        this.volumeValue = volumeValue;
    }

    @Override
    public void setPinch(int pinchValue) {
        this.pinchValue = pinchValue;
    }

    @Override
    public void setRate(int rateValue) {
        this.rateValue = rateValue;
    }

    public ArrayList<String> getContentToPlay() {
        return contentToPlay;
    }

    public int getVolumeValue() {
        return volumeValue;
    }

    public int getPinchValue() {
        return pinchValue;
    }

    public int getRateValue() {
        return rateValue;
    }
}
