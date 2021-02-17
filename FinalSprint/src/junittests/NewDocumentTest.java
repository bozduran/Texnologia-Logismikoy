package junittests;
import commands.CommandsFactory;
import model.Document;
import org.junit.Test;
import view.TextToSpeechEditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static org.junit.Assert.*;

public class NewDocumentTest {
    private Document newDocument;

    @Test
    public void test(){
        String authorName = "Geralt of Rivia";
        String title = "The journey of a witcher";
        newDocument = new Document(authorName,title,null);
        CommandsFactory factory = new CommandsFactory(newDocument);
        TextToSpeechEditorView instance = TextToSpeechEditorView.getSingletonView();
        ActionListener command = factory.createCommand("newDocument");
        command.actionPerformed(null);
        Document document = instance.getCurrentDocument();
        assertEquals(authorName,document.getAuthorName());
        assertEquals(title,document.getDocumentTitle());
        assertEquals(null,document.getFileContents() );

    }

    @Test
    public void test1(){
        String authorName = "Garen of Demacia";
        String title = "A small strory in the rift";
        Date date  = new Date();
        newDocument = new Document(authorName,title,null);
        assertEquals(authorName,newDocument.getAuthorName());
        assertEquals(title,newDocument.getDocumentTitle());
        assertEquals(null,newDocument.getSaveDate());
        assertEquals(null,newDocument.getFilePath());
        assertNotEquals(date,newDocument.getCreationDate());
    }

    @Test
    public void test2(){
        String authorName = "A";
        String title = "T";
        Date date  = new Date();
        newDocument = new Document(authorName,title,null);
        assertEquals(authorName,newDocument.getAuthorName());
        assertEquals(title,newDocument.getDocumentTitle());
        assertEquals(null,newDocument.getSaveDate());
        assertEquals(null,newDocument.getFilePath());
        assertNotEquals(date,newDocument.getCreationDate());
    }

    @Test
    public void test3(){
        String authorName = "-@*$#%&#%#586";
        String title = "!@$#%@$&%$*^&(#%$^#$^%";
        Date date  = new Date();
        newDocument = new Document(authorName,title,null);
        assertEquals(authorName,newDocument.getAuthorName());
        assertEquals(title,newDocument.getDocumentTitle());
        assertEquals(null,newDocument.getSaveDate());
        assertEquals(null,newDocument.getFilePath());
        assertNotEquals(date,newDocument.getCreationDate());
    }



}
