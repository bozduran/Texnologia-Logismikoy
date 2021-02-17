package junittests;

import commands.CommandsFactory;
import model.Document;
import model.Line;
import org.junit.Test;
import view.TextToSpeechEditorView;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SaveDocumentTest {
    private Document document;
    @Test
    public void test(){
        String testFileName = "testFile.txt";
        String authorName = "Geralt of Rivia";
        String title = "The journey of a witcher";
        String contentLine = "The shadow of the night...";
        TextToSpeechEditorView instance =TextToSpeechEditorView.getSingletonView();
        Line line = new Line(contentLine);
        ArrayList<Line> content = new ArrayList<Line>();
        content.add(line);
        document = new Document(authorName,title,testFileName);
        document.newDocument();
        document.setContents(content);
        CommandsFactory factory = new CommandsFactory(document);
        ActionListener command = factory.createCommand("saveDocument");
        Date date = new Date();
        command.actionPerformed(null);
        Document openSavedDocument = new Document(null,null,testFileName);
        factory = new CommandsFactory(openSavedDocument);
        command = factory.createCommand("openDocument");
        command.actionPerformed(null);
        openSavedDocument = instance.getCurrentDocument();
        assertEquals(authorName,openSavedDocument.getAuthorName());
        assertEquals(title,openSavedDocument.getDocumentTitle());
        assertEquals(document.getCreationDate(),openSavedDocument.getCreationDate());
        assertEquals(date,openSavedDocument.getSaveDate());

    }

    @Test
    public void test1(){
        String testFileName = "testFileNumberOne.txt";
        String authorName = "Hobbit";
        String title = "A small story about a naugthy dragon.";
        String contentLine = "The mountain obsesion.";
        String contentLineNumberOne = "The 3 movie who had to be just one.";

        TextToSpeechEditorView instance =TextToSpeechEditorView.getSingletonView();
        Line line = new Line(contentLine);
        ArrayList<Line> content = new ArrayList<Line>();
        content.add(line);
        line = new Line(contentLineNumberOne);
        content.add(line);
        document = new Document(authorName,title,testFileName);
        document.newDocument();
        document.setContents(content);
        CommandsFactory factory = new CommandsFactory(document);
        ActionListener command = factory.createCommand("saveDocument");
        Date date = new Date();
        command.actionPerformed(null);
        Document openSavedDocument = new Document(null,null,testFileName);
        factory = new CommandsFactory(openSavedDocument);
        command = factory.createCommand("openDocument");
        command.actionPerformed(null);
        openSavedDocument = instance.getCurrentDocument();
        content = openSavedDocument.getFileContents();
        Line lineone = content.get(0);
        Line lineTwo = content.get(1);
        assertEquals(authorName,openSavedDocument.getAuthorName());
        assertEquals(title,openSavedDocument.getDocumentTitle());
        assertEquals(document.getCreationDate(),openSavedDocument.getCreationDate());
        assertEquals(date,openSavedDocument.getSaveDate());
        assertEquals(contentLine+"\n",lineone.getLine());
        assertEquals(contentLineNumberOne+"\n",lineTwo.getLine());

    }
}
