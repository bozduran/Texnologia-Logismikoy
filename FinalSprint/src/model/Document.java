package model;

import encodingstrategies.EncodingStrategy;
import texttospeechapis.TextToSpeechAPI;
import texttospeechapis.TextToSpeechAPIFactory;
import view.TextToSpeechEditorView;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Document {

    private String authorName;
    private String documentTitle;
    private Date creationDate;
    private Date saveDate;
    private ArrayList<Line> contents ;
    private String filePath;

    private TextToSpeechAPI audioManager;
    private EncodingStrategy encodingStrategy;


    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");



    public Document( String authorName,String documentTitle,String filePath ){//Date saveDate,Date creationDate,String authorName,String fileContents){
        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        audioManager = factory.createTTSAPI("FreeTTSAdapter");
        this.documentTitle = documentTitle;
        this.authorName = authorName;
        this.filePath = filePath;
    }

    public void playContents(){

        for (Line line :contents){
            line.setAudioManager(audioManager);
            line.playLine();
        }

    }

    public void playReverseContents(){

        for (int i = contents.size() - 1; i >= 0; i--){
            contents.get(i).setAudioManager(audioManager);
            contents.get(i).playReverseLine();
        }

    }

    public void playEncodedContents(){

        for (Line line:contents){
            line.setAudioManager(audioManager);
            line.tuneEncodingStrategy(encodingStrategy);
            line.playEncodedLine();
        }

    }

    public void playLine(int line){
        if (line <= contents.size() && line >0){
            contents.get(line-1).setAudioManager(audioManager);
            contents.get(line-1).playLine();
        }

    }

    public void playReverseLine(int line){

        if (line <= contents.size() && line >0){
            contents.get(line-1).setAudioManager(audioManager);
            contents.get(line-1).playReverseLine();
        }
    }

    public void playEncodedLine(int line){

        if (line <= contents.size() && line >0){
            contents.get(line-1).setAudioManager(audioManager);
            contents.get(line-1).tuneEncodingStrategy(encodingStrategy);
            contents.get(line-1).playEncodedLine();

        }

    }

    public void tuneEncodingStrategy(EncodingStrategy encodingStrategy){ //EncodingStrategy encodingfile...
        this.encodingStrategy = encodingStrategy;
        TextToSpeechEditorView instance = TextToSpeechEditorView.getSingletonView();
        instance.setCurrentDocument(this);
    }

    public void openDocument(){

        contents = new ArrayList<Line>();
        File file = new File(filePath);
        String stringDate = "";
        Line newLine;
        try {
            Scanner reader = new Scanner(file);
            authorName = reader.nextLine();
            documentTitle = reader.nextLine();

            stringDate = reader.nextLine();
            creationDate = formatter.parse(stringDate);
            stringDate = reader.nextLine();
            saveDate = formatter.parse(stringDate);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                newLine = new Line(data);
                contents.add(newLine);
            }

            TextToSpeechEditorView textToSpeechEditorView = TextToSpeechEditorView.getSingletonView();
            textToSpeechEditorView.setCurrentDocument(this);
            reader.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void newDocument() {

        Date date = new Date();
        creationDate = date;
        TextToSpeechEditorView textToSpeechEditorView = TextToSpeechEditorView.getSingletonView();
        textToSpeechEditorView.setCurrentDocument(this);
    }

    public void saveDocument() {

        Date date = new Date();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(authorName+"\n");
            writer.write(documentTitle+"\n");
            writer.write(formatter.format(creationDate)+"\n");
            writer.write(formatter.format(date)+"\n");
            for (Line line :contents) {
                writer.write(line.getLine());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void editDocument() {
        Date date = new Date();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(authorName+"\n");
            writer.write(documentTitle+"\n");
            writer.write(formatter.format(creationDate)+"\n");
            writer.write(formatter.format(date)+"\n");
            for (Line line :contents) {
                writer.write(line.getLine());
            }
            writer.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void textAreaToContent(String text){
        //Useful for getting the text from the text area and converting it to Line objects
        contents = new ArrayList<Line>();
        String lines[] = text.split("\\r?\\n");
        boolean s = false;
        for (String line : lines){
            if (line.contains("File contents:" ) && s == false ){
                s = true;
            } else if (s == true){
                contents.add(new Line(line));
            }

        }
    }

    public void tuneAudio(int []audioParameters){
        audioManager.setVolume(audioParameters[0]);
        audioManager.setRate(audioParameters[1]);
        audioManager.setPinch(audioParameters[2]);

        TextToSpeechEditorView textToSpeechEditorView = TextToSpeechEditorView.getSingletonView();
        textToSpeechEditorView.setCurrentDocument(this);

    }

    public Date getSaveDate() {
        return saveDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public ArrayList<Line> getFileContents() {
        return contents;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public EncodingStrategy getEncodingStrategy(){
        return encodingStrategy;
    }

    public TextToSpeechAPI getAudioManager(){
        return audioManager;
    }

    public String getFilePath(){
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setContents(ArrayList<Line> contents) {
        this.contents = contents;
    }


    public void setAudioManager(TextToSpeechAPI audioManager) {
        for (Line line :contents){
            line.setAudioManager(audioManager);
        }
        this.audioManager = audioManager;
    }

}
