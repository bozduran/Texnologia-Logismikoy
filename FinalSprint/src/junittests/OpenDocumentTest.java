package junittests;


import commands.CommandsFactory;
import model.Document;
import model.Line;
import org.junit.Test;
import view.TextToSpeechEditorView;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class OpenDocumentTest {
    private Document document;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");

    @Test
    public void test(){

        String testFileName = "testFile.txt";
        String authorName = "Geralt of Rivia";
        String title = "The journey of a witcher";
        String contentLine = "The shadow of the night...";
        Date creationDate = new Date();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date saveDate = new Date();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName));
            writer.write(authorName+"\n");
            writer.write(title+"\n");
            writer.write(formatter.format(creationDate)+"\n");
            writer.write(formatter.format(saveDate)+"\n");
            writer.write(contentLine+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        document = new Document(null,null,testFileName);
        CommandsFactory factory = new CommandsFactory(document);
        TextToSpeechEditorView instance = TextToSpeechEditorView.getSingletonView();
        ActionListener command = factory.createCommand("openDocument");
        command.actionPerformed(null);
        Document openDocument = instance.getCurrentDocument();
        ArrayList<Line> content = openDocument.getFileContents();
        Line line = content.get(0);
        assertEquals(authorName,openDocument.getAuthorName());
        assertEquals(title,openDocument.getDocumentTitle());
        assertEquals(creationDate,openDocument.getCreationDate());
        assertEquals(saveDate,openDocument.getSaveDate());
        assertEquals(contentLine+"\n",line.getLine());

    }

    @Test
    public void test1(){

        String testFileName = "testFileNumber1.txt";
        String authorName = "Garen of Demacia";
        String title = "A small strory in the rift";
        Date creationDate = new Date();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date saveDate = new Date();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName));
            writer.write(authorName+"\n");
            writer.write(title+"\n");
            writer.write(formatter.format(creationDate)+"\n");
            writer.write(formatter.format(saveDate)+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        document = new Document(null,null,testFileName);
        document.openDocument();

        assertEquals(authorName,document.getAuthorName());
        assertEquals(title,document.getDocumentTitle());
        assertEquals(creationDate,document.getCreationDate());
        assertEquals(saveDate,document.getSaveDate());

    }

    @Test
    public void test2(){

        String testFileName = "testFileNumber2.txt";
        String authorName = "Hobbit!@#!@%!@#%$!@%#";
        String title = "A small story about a naugthy dragon.!@#%!@^$#@$%#$%";
        String contentLine = "This is a simple line.";
        Date creationDate = new Date();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date saveDate = new Date();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName));
            writer.write(authorName+"\n");
            writer.write(title+"\n");
            writer.write(formatter.format(creationDate)+"\n");
            writer.write(formatter.format(saveDate)+"\n");
            writer.write(contentLine);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        document = new Document(null,null,testFileName);
        document.openDocument();

        assertEquals(authorName,document.getAuthorName());
        assertEquals(title,document.getDocumentTitle());
        assertEquals(creationDate,document.getCreationDate());
        assertEquals(saveDate,document.getSaveDate());
        ArrayList<Line> lines = document.getFileContents();
        Line justOneLine = lines.get(0);
        assertEquals(contentLine+"\n",justOneLine.getLine());

    }

    @Test
    public void test3(){
        //Write a small document for testing
        String testFileName = "testFileNumber3.txt";
        String authorName = "Some random elephant.";
        String title = "Best languages for printing stuff";
        String contentLineOne = "Python print(Hello world)";
        String contentLineTwo = "Java System.out.println(Hello world)";
        String contentLineThree = "Asembly who need printing stuff right?";
        Date creationDate = new Date();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date saveDate = new Date();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName));
            writer.write(authorName+"\n");
            writer.write(title+"\n");
            writer.write(formatter.format(creationDate)+"\n");
            writer.write(formatter.format(saveDate)+"\n");
            writer.write(contentLineOne+"\n");
            writer.write(contentLineTwo+"\n");
            writer.write(contentLineThree+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        document = new Document(null,null,testFileName);
        document.openDocument();

        assertEquals(authorName,document.getAuthorName());
        assertEquals(title,document.getDocumentTitle());
        assertEquals(creationDate,document.getCreationDate());
        assertEquals(saveDate,document.getSaveDate());
        ArrayList<Line> lines = document.getFileContents();
        Line justOneLine = lines.get(0);
        Line justTwoLine = lines.get(1);
        Line justThreeLine = lines.get(2);
        assertEquals(contentLineOne+"\n",justOneLine.getLine());
        assertEquals(contentLineTwo+"\n",justTwoLine.getLine());
        assertEquals(contentLineThree+"\n",justThreeLine.getLine());

    }
}
