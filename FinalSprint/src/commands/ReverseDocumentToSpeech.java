package commands;

import model.Document;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReverseDocumentToSpeech implements ActionListener {
    private Document document;
    public ReverseDocumentToSpeech(Document document){
        this.document = document;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        document.playReverseContents();
    }
}
