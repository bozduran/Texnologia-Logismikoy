package commands;

import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncodedDocumentToSpeech implements ActionListener {

    private Document document;

    public EncodedDocumentToSpeech(Document document){
        this.document = document;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        document.playEncodedContents();
    }
}
