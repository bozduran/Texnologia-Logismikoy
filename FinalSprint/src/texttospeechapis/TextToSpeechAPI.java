package texttospeechapis;

public interface TextToSpeechAPI {
    void play(String contentToPlay);
    void setVolume(int volumeValue);
    void setPinch(int pinchValue);
    void setRate(int rateValue);
}
