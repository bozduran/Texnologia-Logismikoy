package commands;

import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenDocument implements ActionListener {
    private Document document;
    public OpenDocument(Document document){
        this.document = document;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        document.openDocument();
    }
}
