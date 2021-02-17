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

import static org.junit.Assert.assertEquals;

public class EditDocumentTest {
    private Document document ;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");

    @Test
    public void test(){

        String testFileName = "testFile.txt";
        String authorName = "Geralt of Rivia";
        String title = "The journey of a witcher";
        String contentLine = "The shadow of the night...";
        String contentLineToAdd = "The dark forest was ahead.";
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
        document.openDocument();
        ArrayList<Line> newContents = document.getFileContents();
        newContents.add(new Line(contentLineToAdd));
        document.setContents(newContents);
        CommandsFactory factory = new CommandsFactory(document);
        ActionListener command = factory.createCommand("editDocument");
        command.actionPerformed(null);

        document = new Document(null,null,testFileName);
        document.openDocument();
        ArrayList<Line> contents = document.getFileContents();
        Line addedLine = contents.get(1);
        assertEquals(contentLineToAdd+"\n",addedLine.getLine());
    }


    @Test
    public void test1(){

        String testFileName = "testFileNumber1.txt";
        String authorName = "Garen of Demacia";
        String title = "A small strory in the rift";
        String contentLineAdded = "A double kill";
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
        ArrayList<Line> newContents = new ArrayList<Line>();
        newContents.add(new Line(contentLineAdded));
        document.setContents(newContents);
        document.editDocument();
        document = new Document(null,null,testFileName);
        document.openDocument();
        ArrayList<Line> contents = document.getFileContents();
        Line addedLine = contents.get(0);
        assertEquals(contentLineAdded+"\n",addedLine.getLine());
    }

    @Test
    public void test2(){

        String testFileName = "testFileNumber1.txt";
        String authorName = "Garen of Demacia";
        String title = "A small strory in the rift";
        String contentLineAddedNumberOne = "A double kill";
        String contentLineAddedNumberTwo = "A triple kill";
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
        ArrayList<Line> newContents = new ArrayList<Line>();
        newContents.add(new Line(contentLineAddedNumberOne));
        newContents.add(new Line(contentLineAddedNumberTwo));
        document.setContents(newContents);
        document.editDocument();
        document = new Document(null,null,testFileName);
        document.openDocument();
        ArrayList<Line> contents = document.getFileContents();
        Line addedLineOne = contents.get(0);
        Line addedLineTwo = contents.get(1);

        assertEquals(contentLineAddedNumberOne+"\n",addedLineOne.getLine());
        assertEquals(contentLineAddedNumberTwo+"\n",addedLineTwo.getLine());
    }
}
