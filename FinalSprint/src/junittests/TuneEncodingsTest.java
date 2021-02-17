package junittests;

import commands.CommandsFactory;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.Rot13Encoding;
import encodingstrategies.StrategiesFactory;
import model.Document;
import org.junit.Test;

import java.awt.event.ActionListener;
import static org.junit.Assert.assertEquals;
public class TuneEncodingsTest {
    private Document document;

    @Test
    public void test(){
        document = new Document(null,null,null);

        StrategiesFactory strategiesFactory = new StrategiesFactory();
        EncodingStrategy encodingStrategy = strategiesFactory.createEncodingStrategy("Rot13");
        CommandsFactory commandsFactory = new CommandsFactory(document,encodingStrategy);
        ActionListener command = commandsFactory.createCommand("tuneEncodings");
        command.actionPerformed(null);
        EncodingStrategy getEncoding = document.getEncodingStrategy();
        assertEquals(getEncoding,encodingStrategy);
    }

    @Test
    public void test1(){
        document = new Document(null,null,null);

        StrategiesFactory strategiesFactory = new StrategiesFactory();
        EncodingStrategy encodingStrategy = strategiesFactory.createEncodingStrategy("AtBash");
        CommandsFactory commandsFactory = new CommandsFactory(document,encodingStrategy);
        ActionListener command = commandsFactory.createCommand("tuneEncodings");
        command.actionPerformed(null);
        EncodingStrategy getEncoding = document.getEncodingStrategy();
        assertEquals(getEncoding,encodingStrategy);
    }
}
