package commands;

import encodingstrategies.EncodingStrategy;
import model.Document;

import java.awt.event.ActionListener;

public class CommandsFactory {
    private Document document;
    private int slectedLine;
    private EncodingStrategy encodingStrategy;

    private NewDocument newDocument;
    private OpenDocument openDocument;
    private SaveDocument saveDocument;
    private EditDocument editDocument;
    private LineToSpeech lineToSpeech;
    private TuneAudio tuneAudio;
    private DocumentToSpeech documentToSpeech;
    private ReverseDocumentToSpeech reverseDocumentToSpeech;
    private TuneEncodings tuneEncodings;
    private PlayEncodedLine playEncodedLine;
    private PlayReverseLine playReverseLine;
    private EncodedDocumentToSpeech encodedDocumentToSpeech;



    public CommandsFactory(Document document){
    	this.document = document;
    }

    public CommandsFactory(Document document, EncodingStrategy encodingStrategy){
        this.document = document;
        this.encodingStrategy = encodingStrategy;
    }
    public CommandsFactory(Document document, int selectedLine){
        this.document = document;
        this.slectedLine = selectedLine;
    }

    public ActionListener createCommand(String command){

        if (command.equals("openDocument")){
            openDocument =new OpenDocument(document);
            return openDocument;
        } else if (command.equals("newDocument")){
            newDocument = new NewDocument(document);
            return newDocument;
        } else if (command.equals("saveDocument")){
            saveDocument = new SaveDocument(document);
            return saveDocument;
        } else if (command.equals("lineToSpeech")){
            lineToSpeech = new LineToSpeech(document,slectedLine);
            return lineToSpeech;
        } else if (command.equals("editDocument")){
            editDocument = new EditDocument(document);
            return editDocument;
        } else if (command.equals("documentToSpeech")){
            documentToSpeech = new DocumentToSpeech(document);
            return documentToSpeech;
        } else if (command.equals("reverseDocumentToSpeech")){
            reverseDocumentToSpeech = new ReverseDocumentToSpeech(document);
            return reverseDocumentToSpeech;
        } else if (command.equals("tuneEncodings")){
            tuneEncodings = new TuneEncodings(document,encodingStrategy);
            return tuneEncodings;
        } else if (command.equals("playEncodedLine")){
            playEncodedLine = new PlayEncodedLine(document,slectedLine);
            return playEncodedLine;
        } else if (command.equals("playReversedLine")){
            playReverseLine = new PlayReverseLine(document,slectedLine);
            return playReverseLine;
        } else if (command.equals("encodedDocumentToSpeech")){
            encodedDocumentToSpeech = new EncodedDocumentToSpeech(document);
            return encodedDocumentToSpeech;
        }
        return null;
    }

    public ActionListener createCommand(String command,int []tuneParameters){
        if (command.equals("tuneAudio")) {
            tuneAudio = new TuneAudio(document,tuneParameters);
            return tuneAudio;
        }
        return null;
    }



}
