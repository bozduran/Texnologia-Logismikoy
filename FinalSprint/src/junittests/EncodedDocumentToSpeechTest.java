package junittests;

import commands.CommandsFactory;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import model.Line;
import org.junit.Test;
import texttospeechapis.FakeTextToSpeechAPI;
import texttospeechapis.TextToSpeechAPI;
import texttospeechapis.TextToSpeechAPIFactory;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EncodedDocumentToSpeechTest {

    private Document document;

    @Test
    public void test(){
        String testFileName = "testFile.txt";
        String authorName = "Hobbit";
        String title = "A small story about a naughty dragon.";
        String contentLine = "The mountain obsession.";
        String secondContentLine = "Ancient ruins.";

        document = new Document(authorName,title,testFileName);
        ArrayList<Line> contents = new ArrayList<Line>();
        Line firstLine = new Line(contentLine);
        Line secondLine = new Line(secondContentLine);
        contents.add(firstLine);
        contents.add(secondLine);
        document.setContents(contents);

        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        TextToSpeechAPI api = factory.createTTSAPI("FakeTextToSpeechAPI");
        document.setAudioManager(api);

        StrategiesFactory strategiesFactory = new StrategiesFactory();
        EncodingStrategy encodingStrategy = strategiesFactory.createEncodingStrategy("Rot13");

        CommandsFactory commandsFactory = new CommandsFactory(document,encodingStrategy);
        ActionListener command = commandsFactory.createCommand("tuneEncodings");
        command.actionPerformed(null);

        commandsFactory = new CommandsFactory(document);
        command = commandsFactory.createCommand("encodedDocumentToSpeech");
        command.actionPerformed(null);

        api = document.getAudioManager();
        ArrayList<String> encodedContent = ((FakeTextToSpeechAPI) api).getContentToPlay();
        //The mountain obsession -> Gur zbhagnva bofrffvba encoded in online encoder
        //Ancient ruins -> Napvrag ehvaf
        assertEquals(encodedContent.get(0).trim(),"Gur zbhagnva bofrffvba".trim());
        assertEquals(encodedContent.get(1).trim(),"Napvrag ehvaf".trim());

    }

    @Test
    public void test1(){
        String testFileName = "testFile.txt";
        String authorName = "Hobbit";
        String title = "A small story about a naughty dragon.";
        String contentLine = "The mountain obsession.";
        String secondContentLine = "Ancient ruins.";

        document = new Document(authorName,title,testFileName);
        ArrayList<Line> contents = new ArrayList<Line>();
        Line firstLine = new Line(contentLine);
        Line secondLine = new Line(secondContentLine);
        contents.add(firstLine);
        contents.add(secondLine);
        document.setContents(contents);

        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        TextToSpeechAPI api = factory.createTTSAPI("FakeTextToSpeechAPI");
        document.setAudioManager(api);

        StrategiesFactory strategiesFactory = new StrategiesFactory();
        EncodingStrategy encodingStrategy = strategiesFactory.createEncodingStrategy("AtBash");

        CommandsFactory commandsFactory = new CommandsFactory(document,encodingStrategy);
        ActionListener command = commandsFactory.createCommand("tuneEncodings");
        command.actionPerformed(null);

        commandsFactory = new CommandsFactory(document);
        command = commandsFactory.createCommand("encodedDocumentToSpeech");
        command.actionPerformed(null);

        api = document.getAudioManager();
        ArrayList<String> encodedContent = ((FakeTextToSpeechAPI) api).getContentToPlay();
        //The mountain obsession -> "Gsv nlfmgzrm lyhvhhrlm "encoded in online encoder
        //Ancient ruins -> Zmxrvmg ifrmh
        assertEquals(encodedContent.get(0).trim(),"Gsv nlfmgzrm lyhvhhrlm".trim());
        assertEquals(encodedContent.get(1).trim(),"Zmxrvmg ifrmh".trim());

    }
}
