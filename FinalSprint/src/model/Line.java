package model;

import encodingstrategies.EncodingStrategy;
import texttospeechapis.TextToSpeechAPI;

import java.util.ArrayList;

public class Line {
    private String fullLine = null;
    private ArrayList<String> words ;
    private ArrayList<String> encodedWords ;

    private TextToSpeechAPI audioManager;
    private EncodingStrategy encodingStrategy;

    public Line(String line){
        words = new ArrayList<String>();
        this.fullLine = line;
        String[] words = line.split("\\s+");

        for (String word : words) {
            this.words.add(word);
        }
    }

    public void playLine(){
        audioManager.play(fullLine);
    }

    public void  playReverseLine(){

        String reverseLine = "";
        for (int i = words.size() - 1; i >= 0; i--){
            reverseLine += words.get(i) + " ";
        }
        audioManager.play(reverseLine);
    }

    public void playEncodedLine(){
        String encodedLine = "";
        for (String word:encodedWords){
            encodedLine += word + " ";
        }
        audioManager.play(encodedLine);

    }

    public void tuneEncodingStrategy(EncodingStrategy encodingStrategy){

        this.encodingStrategy = encodingStrategy;
        encodedWords = new ArrayList<String>();

        for (String word :words){
            encodedWords.add(encodingStrategy.encode(word));
        }
    }

    public String getLine(){
        return fullLine + "\n";
    }

    public void setAudioManager(TextToSpeechAPI audioManager){
        this.audioManager = audioManager;
    }

}
