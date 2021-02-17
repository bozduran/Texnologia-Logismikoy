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

public class ReverseDocumentToSpeechTest {
    private Document document;
    @Test
    public void test(){
        String testFileName = "testFile.txt";
        String authorName = "Hobbit";
        String title = "A small story about a naugthy dragon.";
        String contentLine = "The mountain obsseion.";
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

        CommandsFactory commandsFactory = new CommandsFactory(document);
        ActionListener actionListener = commandsFactory.createCommand("reverseDocumentToSpeech");
        actionListener.actionPerformed(null);

        api = document.getAudioManager();
        ArrayList<String> contentToPlay = ((FakeTextToSpeechAPI) api).getContentToPlay();

        String []splitLine = secondContentLine.split("\\s+");
        String reversedLine = splitLine[1] + " "+ splitLine[0] + " ";
        assertEquals(reversedLine,contentToPlay.get(0));

        splitLine = contentLine.split("\\s+");
        reversedLine =splitLine[2] + " "+ splitLine[1] + " "+ splitLine[0] + " ";
        assertEquals(reversedLine,contentToPlay.get(1));




    }
}
