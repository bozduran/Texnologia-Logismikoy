package commands;

import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDocument implements ActionListener {

    private Document document;
    public NewDocument(Document document){
        this.document = document;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        document.newDocument();
    }
}
