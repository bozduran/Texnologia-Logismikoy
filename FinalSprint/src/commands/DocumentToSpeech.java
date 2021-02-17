package commands;


import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentToSpeech implements ActionListener {

    private Document document;

    public DocumentToSpeech(Document document){
        this.document = document;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        document.playContents();

    }
}
