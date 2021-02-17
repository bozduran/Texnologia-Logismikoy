package junittests;

import commands.CommandsFactory;
import model.Document;
import model.Line;
import org.junit.Test;
import texttospeechapis.FakeTextToSpeechAPI;
import texttospeechapis.TextToSpeechAPI;
import texttospeechapis.TextToSpeechAPIFactory;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class DocumentToSpeechTest {
    private Document document;

    @Test
    public void test(){
        String testFileName = "testFile.txt";
        String authorName = "Hobbit";
        String title = "A small story about a naugthy dragon.";
        String contentLine = "The mountain obsesion.";
        String secondContentLine = "Ancienyt ruins.";
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

        CommandsFactory commandsFactory = new CommandsFactory(document);
        ActionListener command = commandsFactory.createCommand("documentToSpeech");
        command.actionPerformed(null);

        api = document.getAudioManager();
        ArrayList<String> contentToPlay = ((FakeTextToSpeechAPI) api).getContentToPlay();
        assertEquals(contentLine,contentToPlay.get(0));
        assertEquals(secondContentLine,contentToPlay.get(1));


    }
}
