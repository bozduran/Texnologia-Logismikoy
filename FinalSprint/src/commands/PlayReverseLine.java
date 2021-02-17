package commands;

import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayReverseLine implements ActionListener {

    private Document document;
    private int selectedLine;
    public PlayReverseLine(Document document, int slectedLine){
        this.document = document;
        this.selectedLine = slectedLine;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        document.playReverseLine(selectedLine);
    }
}
