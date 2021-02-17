package commands;

import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineToSpeech implements ActionListener {

    private Document document;
    private int selectedLine;
    public LineToSpeech(Document document, int selectedLine){
        this.document = document;
        this.selectedLine=selectedLine;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        document.playLine(selectedLine);
    }
}
