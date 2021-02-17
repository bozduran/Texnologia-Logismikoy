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

public class LineToSpeechTest {
    private Document document;

    @Test
    public void test(){
        String testFileName = "testFile.txt";
        String authorName = "Hobbit";
        String title = "A small story about a naugthy dragon.";
        String contentLine = "The mountain obsesion.";
        String contentLineToPlay = "The eagles are great but no one can understand that.";
        document = new Document(authorName,title,testFileName);
        ArrayList<Line> contents = new ArrayList<Line>();
        Line firstLine = new Line(contentLine);
        Line secondLine = new Line(contentLineToPlay);
        contents.add(firstLine);
        contents.add(secondLine);
        document.setContents(contents);
        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        TextToSpeechAPI api = factory.createTTSAPI("FakeTextToSpeechAPI");
        document.setAudioManager(api);

        CommandsFactory commandsFactory = new CommandsFactory(document,2);
        ActionListener command = commandsFactory.createCommand("lineToSpeech");
        command.actionPerformed(null);
        api = document.getAudioManager();
        ArrayList<String> contentToPlay = ((FakeTextToSpeechAPI) api).getContentToPlay();
        assertEquals(contentToPlay.get(0),contentLineToPlay);
    }
}
