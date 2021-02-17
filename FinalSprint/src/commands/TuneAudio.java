package commands;

import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuneAudio implements ActionListener{

    private Document document;
    private int []tuneParameters;

    public TuneAudio(Document document,int[] tuneParameters){
        this.document = document;
        this.tuneParameters = tuneParameters;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        document.tuneAudio(tuneParameters);
    }
}
