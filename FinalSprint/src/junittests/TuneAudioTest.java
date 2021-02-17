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
public class TuneAudioTest {

    private Document document;

    @Test
    public void testVolume(){
        int []audioParameters = new int[3];
        int newVolume = 234253;
        audioParameters[0] = newVolume;


        document = new Document(null,null,null);
        ArrayList<Line> contents = new ArrayList<Line>();
        contents.add(new Line("Example"));
        document.setContents(contents);
        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        TextToSpeechAPI textToSpeechAPI = factory.createTTSAPI("FakeTextToSpeechAPI");
        document.setAudioManager(textToSpeechAPI);

        CommandsFactory commandsFactory = new CommandsFactory(document);
        ActionListener command = commandsFactory.createCommand("tuneAudio",audioParameters);
        command.actionPerformed(null);
        TextToSpeechAPI textToSpeechAPI1 = document.getAudioManager();
        int volume = ((FakeTextToSpeechAPI) textToSpeechAPI1).getVolumeValue();
        assertEquals(volume,newVolume);
    }

    @Test
    public void testRate(){
        int []audioParameters = new int[3];
        int newRate = 3245;
        audioParameters[1] = newRate;


        document = new Document(null,null,null);
        ArrayList<Line> contents = new ArrayList<Line>();
        contents.add(new Line("Example"));
        document.setContents(contents);
        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        TextToSpeechAPI textToSpeechAPI = factory.createTTSAPI("FakeTextToSpeechAPI");
        document.setAudioManager(textToSpeechAPI);

        CommandsFactory commandsFactory = new CommandsFactory(document);
        ActionListener command = commandsFactory.createCommand("tuneAudio",audioParameters);
        command.actionPerformed(null);
        TextToSpeechAPI textToSpeechAPI1 = document.getAudioManager();
        int volume = ((FakeTextToSpeechAPI) textToSpeechAPI1).getRateValue();
        assertEquals(volume,newRate);
    }

    @Test
    public void testPinch(){
        int []audioParameters = new int[3];
        int newPintch = 3245;
        audioParameters[2] = newPintch;


        document = new Document(null,null,null);
        ArrayList<Line> contents = new ArrayList<Line>();
        contents.add(new Line("Example"));
        document.setContents(contents);
        TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
        TextToSpeechAPI textToSpeechAPI = factory.createTTSAPI("FakeTextToSpeechAPI");
        document.setAudioManager(textToSpeechAPI);

        CommandsFactory commandsFactory = new CommandsFactory(document);
        ActionListener command = commandsFactory.createCommand("tuneAudio",audioParameters);
        command.actionPerformed(null);
        TextToSpeechAPI textToSpeechAPI1 = document.getAudioManager();
        int volume = ((FakeTextToSpeechAPI) textToSpeechAPI1).getPinchValue();
        assertEquals(volume,newPintch);
    }
}
