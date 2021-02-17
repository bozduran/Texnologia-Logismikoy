

package view;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.*;

import commands.CommandsFactory;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import model.Line;


/**
 * @author Mpoz Ntoyran 2310
 */
public class TextToSpeechEditorView extends JFrame {
    private Document currentDocument;
    private static TextToSpeechEditorView singleInstance = null;
    private CommandsFactory commandsFactory;
    private StrategiesFactory strategiesFactory = new StrategiesFactory();

    private ActionListener command;
    private TextToSpeechEditorView(){}

    public static TextToSpeechEditorView getSingletonView(){
        if (singleInstance == null)
            singleInstance = new TextToSpeechEditorView();
        return singleInstance;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TextToSpeechEditorView GUI = new TextToSpeechEditorView();
                GUI.initComponents();
                GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GUI.setVisible(true);
            }
        });
    }

    private void menuNewDocumentActionPerformed(ActionEvent e) {
        NewFileView popUp = new NewFileView(TextToSpeechEditorView.this);
        popUp.setModal(true);
        popUp.setVisible(true);
        String author = popUp.getAuthorName();
        String title = popUp.getNewTitle();
        if (title.equals("") || author.equals("")){
            documentErrorDialog(6);
            return;
        }
        currentDocument = new Document(author,title,null);
        commandsFactory = new CommandsFactory(currentDocument);
        command = commandsFactory.createCommand("newDocument");
        command.actionPerformed(e);
        showContents("newDocument");

    }

    private void menuOpenDocumentActionPerformed(ActionEvent e) {

        int returnValue = fileChosen.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChosen.getSelectedFile();
            if ( file.getAbsolutePath().contains(".txt") != true ){
                documentErrorDialog(4);
                return;
            }
            currentDocument = new Document(null,null,file.getAbsolutePath());
            commandsFactory = new CommandsFactory(currentDocument);
            command = commandsFactory.createCommand("openDocument");
            command.actionPerformed(e);
            showContents("openDocument");

        }else {
            documentErrorDialog(4);
        }

    }

    private void menuSaveDocumentActionPerformed(ActionEvent e) {

        int returnValue = -1;
        if (currentDocument != null){
            fileChosen.setDialogTitle("Specify a file to save");
            returnValue = fileChosen.showSaveDialog(null);
        } else {
            documentErrorDialog(1);
        }


        if (returnValue == JFileChooser.APPROVE_OPTION &&
                currentDocument != null ){

            File file = fileChosen.getSelectedFile();
            if (file.getAbsolutePath().contains(".txt") != true){
                documentErrorDialog(5);
                return;
            }
            currentDocument.setFilePath(file.getAbsolutePath());
            currentDocument.textAreaToContent(textArea1.getText() );
            commandsFactory = new CommandsFactory(currentDocument);
            command = commandsFactory.createCommand("saveDocument");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(3);
        }

    }

    private void menuExitActionPerformed(ActionEvent e) {

        System.exit(1);
    }

    private void menuDocumentToSpeechActionPerformed(ActionEvent e) {

        if (currentDocument != null){
            command = commandsFactory.createCommand("documentToSpeech");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(1);

        }
    }

    private void menuEditDocumentActionPerformed(ActionEvent e) {

        if (currentDocument != null){
            currentDocument.textAreaToContent(textArea1.getText() );
            command = commandsFactory.createCommand("editDocument");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(1);
        }
    }

    private void menuReverseDocumentActionPerformed(ActionEvent e) {

        if (currentDocument != null){
            command = commandsFactory.createCommand("reverseDocumentToSpeech");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(1);
        }
    }

    private void menuLineToSpeechActionPerformed(ActionEvent e) {

        if ( currentDocument != null  ){
            if (lineSearch.getText().equals("")){
                documentErrorDialog(7);
                return;
            }

            commandsFactory = new CommandsFactory(currentDocument,getLineToPlay());
            command = commandsFactory.createCommand("lineToSpeech");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(1);
        }
    }

    private void menuEncodeLineActionPerformed(ActionEvent e) {

        if (currentDocument != null){
            if (currentDocument.getEncodingStrategy() != null){
                if (lineSearch.getText().equals("")){
                    documentErrorDialog(7);
                    return;
                }
                commandsFactory = new CommandsFactory(currentDocument,getLineToPlay());
                command = commandsFactory.createCommand("playEncodedLine");
                command.actionPerformed(e);
            } else {
                documentErrorDialog(2);
            }
        } else {
            documentErrorDialog(1);
        }
    }

    private void menuReverseLineToSpeechActionPerformed(ActionEvent e) {

        if ( currentDocument != null ){
            if (lineSearch.getText().equals("")){
                documentErrorDialog(7);
                return;
            }
            commandsFactory = new CommandsFactory(currentDocument,getLineToPlay());
            command = commandsFactory.createCommand("playReversedLine");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(1);
        }
    }

    private void showContents(String fileMode){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
        Date creationDate;
        Date saveDate;

        if (fileMode.equals("openDocument")){
            textArea1.setText(currentDocument.getAuthorName() +"\n");
            textArea1.append(currentDocument.getDocumentTitle() + "\n");
            creationDate = currentDocument.getCreationDate();
            textArea1.append(formatter.format(creationDate) +"\n" );
            saveDate = currentDocument.getSaveDate();
            textArea1.append(formatter.format(saveDate) +"\n");
            textArea1.append("File contents:\n");
            for (Line line : currentDocument.getFileContents()){
                textArea1.append(line.getLine());
            }

        } else if (fileMode.equals("newDocument")){
            textArea1.setText(currentDocument.getAuthorName()+"\n");
            textArea1.append(currentDocument.getDocumentTitle() +"\n");
            creationDate = currentDocument.getCreationDate();
            textArea1.append(formatter.format(creationDate) + "\n");
            textArea1.append("File contents:\n");

        }

    }

    private void menuSelectEncodingRot13ActionPerformed(ActionEvent e) {

        if (currentDocument != null){
            EncodingStrategy encodingStrategy = strategiesFactory.createEncodingStrategy("Rot13");
            commandsFactory = new CommandsFactory(currentDocument,encodingStrategy);
            command = commandsFactory.createCommand("tuneEncodings");
            command.actionPerformed(e);
        } else {
            documentErrorDialog(1);
        }

    }

    private void menuSelectAtBashEncodingActionPerformed(ActionEvent e) {

        if (currentDocument != null){
            EncodingStrategy encodingStrategy = strategiesFactory.createEncodingStrategy("AtBash");
            commandsFactory = new CommandsFactory(currentDocument,encodingStrategy);
            command = commandsFactory.createCommand("tuneEncodings");
            command.actionPerformed(e);

        } else {
            documentErrorDialog(1);
        }
    }

    private void sliderVolumeStateChanged(ChangeEvent e) {
        int []tuneParameters = new int[3];
        tuneParameters[0] = sliderVolume.getValue();
        tuneParameters[1] = sliderSpeechRate.getValue();
        tuneParameters[2] = sliderPitch.getValue();
        if (currentDocument != null){
            command = commandsFactory.createCommand("tuneAudio",tuneParameters);
            command.actionPerformed(null);
        } else {
            documentErrorDialog(1);
        }

    }

    private void sliderSpeechRateStateChanged(ChangeEvent e) {

        int []tuneParameters = new int[3];
        tuneParameters[0] = sliderVolume.getValue();
        tuneParameters[1] = sliderSpeechRate.getValue();
        tuneParameters[2] = sliderPitch.getValue();
        if (currentDocument != null){
            command = commandsFactory.createCommand("tuneAudio",tuneParameters);
            command.actionPerformed(null);
        } else {
            documentErrorDialog(1);
        }
    }

    private void sliderPitchStateChanged(ChangeEvent e) {

        int []tuneParameters = new int[3];
        tuneParameters[0] = sliderVolume.getValue();
        tuneParameters[1] = sliderSpeechRate.getValue();
        tuneParameters[2] = sliderPitch.getValue();
        if (currentDocument != null){
            command = commandsFactory.createCommand("tuneAudio",tuneParameters);
            command.actionPerformed(null);
        } else {
            documentErrorDialog(1);
        }
    }

    private void documentErrorDialog(int option){
        if (option == 1) {
            JOptionPane.showMessageDialog(null, "Error no document to perform action.\n" +
                            "Please select a document or create one.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);
        } else if (option == 2){
            JOptionPane.showMessageDialog(null,"No encoding strategy is select.\n"+
                            "Please select encoding strategy and retry.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        } else if (option ==3){
            JOptionPane.showMessageDialog(null, "Error no file or path chosen\n"
                            +"Please check your document or the path.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        } else if (option == 4){
            JOptionPane.showMessageDialog(null, "Error coudnt open the requested document.\n"
                            +"Please check your document or the path.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        } else if (option == 5){
            JOptionPane.showMessageDialog(null, "Error coudnt save the requested document.\n"
                            +"All document must have .txt at the end.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        } else if (option == 6){
            JOptionPane.showMessageDialog(null, "Error coudnt create a document.\n"
                            +"Plese check your author and title fields.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        } else if (option == 7){
            JOptionPane.showMessageDialog(null, "Error no selected line.\n"
                            +"Please select line and try again.", "Error: "
                    , JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void menuItemPlayEncodedContentsActionPerformed(ActionEvent e) {

        if (currentDocument != null && currentDocument.getEncodingStrategy() != null){
            command = commandsFactory.createCommand("encodedDocumentToSpeech");
            command.actionPerformed(e);
        }
        if (currentDocument == null){
            documentErrorDialog(1);
        } else if (currentDocument.getEncodingStrategy()==null){
            documentErrorDialog(2);
        }


    }

    private void initComponents() {

        fileChosen = new JFileChooser();
        menuBar1 = new JMenuBar();
        fileMenu = new JMenu();
        menuNewDocument = new JMenuItem();
        menuOpenDocument = new JMenuItem();
        menuSaveDocument = new JMenuItem();
        menuEditDocument = new JMenuItem();
        menuExit = new JMenuItem();
        textToSpeechMenu = new JMenu();
        menuDocumentToSpeech = new JMenuItem();
        menuReverseDocumentToSpeech = new JMenuItem();
        menuItemPlayEncodedContents = new JMenuItem();
        menuEncodingsOption = new JMenu();
        menuSelectEncodingRot13 = new JMenuItem();
        menuSelectAtBashEncoding = new JMenuItem();
        menuAudioParameters = new JMenu();
        menuItemVolumeSelector = new JMenuItem();
        sliderVolume = new JSlider();
        menuItemSpeechRate = new JMenuItem();
        sliderSpeechRate = new JSlider();
        menuItemPitch = new JMenuItem();
        sliderPitch = new JSlider();
        menuLineOptions = new JMenu();
        menuLineToSpeech = new JMenuItem();
        menuReverseLineToSpeech = new JMenuItem();
        menuEncodeLine = new JMenuItem();
        lineSearch = new JTextPane();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        setTitle("TextToSpeechEditor");
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- menuNewDocument ----
                menuNewDocument.setText("New document");
                menuNewDocument.addActionListener(e -> menuNewDocumentActionPerformed(e));
                fileMenu.add(menuNewDocument);

                //---- menuOpenDocument ----
                menuOpenDocument.setText("Open document");
                menuOpenDocument.addActionListener(e -> menuOpenDocumentActionPerformed(e));
                fileMenu.add(menuOpenDocument);

                //---- menuSaveDocument ----
                menuSaveDocument.setText("Save document");
                menuSaveDocument.addActionListener(e -> menuSaveDocumentActionPerformed(e));
                fileMenu.add(menuSaveDocument);

                //---- menuEditDocument ----
                menuEditDocument.setText("Edit Document");
                menuEditDocument.addActionListener(e -> menuEditDocumentActionPerformed(e));
                fileMenu.add(menuEditDocument);

                //---- menuExit ----
                menuExit.setText("Exit");
                menuExit.addActionListener(e -> menuExitActionPerformed(e));
                fileMenu.add(menuExit);
            }
            menuBar1.add(fileMenu);

            //======== textToSpeechMenu ========
            {
                textToSpeechMenu.setText("Text to speech");

                //---- menuDocumentToSpeech ----
                menuDocumentToSpeech.setText("Transform document to speech");
                menuDocumentToSpeech.addActionListener(e -> menuDocumentToSpeechActionPerformed(e));
                textToSpeechMenu.add(menuDocumentToSpeech);

                //---- menuReverseDocumentToSpeech ----
                menuReverseDocumentToSpeech.setText("Play documents in reverse");
                menuReverseDocumentToSpeech.addActionListener(e -> menuReverseDocumentActionPerformed(e));
                textToSpeechMenu.add(menuReverseDocumentToSpeech);

                //---- menuItemPlayEncodedContents ----
                menuItemPlayEncodedContents.setText("Play encoded contents");
                menuItemPlayEncodedContents.addActionListener(e -> menuItemPlayEncodedContentsActionPerformed(e));
                textToSpeechMenu.add(menuItemPlayEncodedContents);
            }
            menuBar1.add(textToSpeechMenu);

            //======== menuEncodingsOption ========
            {
                menuEncodingsOption.setText("Encoding options");

                //---- menuSelectEncodingRot13 ----
                menuSelectEncodingRot13.setText("Select Rot13 encoding");
                menuSelectEncodingRot13.addActionListener(e -> menuSelectEncodingRot13ActionPerformed(e));
                menuEncodingsOption.add(menuSelectEncodingRot13);

                //---- menuSelectAtBashEncoding ----
                menuSelectAtBashEncoding.setText("Select AtBash encoding");
                menuSelectAtBashEncoding.addActionListener(e -> menuSelectAtBashEncodingActionPerformed(e));
                menuEncodingsOption.add(menuSelectAtBashEncoding);
            }
            menuBar1.add(menuEncodingsOption);

            //======== menuAudioParameters ========
            {
                menuAudioParameters.setText("Audio options");

                //---- menuItemVolumeSelector ----
                menuItemVolumeSelector.setText("Volume");
                menuAudioParameters.add(menuItemVolumeSelector);

                //---- sliderVolume ----
                sliderVolume.setValue(100);
                sliderVolume.addChangeListener(e -> sliderVolumeStateChanged(e));
                menuAudioParameters.add(sliderVolume);

                //---- menuItemSpeechRate ----
                menuItemSpeechRate.setText("Speech rate");
                menuAudioParameters.add(menuItemSpeechRate);

                //---- sliderSpeechRate ----
                sliderSpeechRate.setMaximum(150);
                sliderSpeechRate.setValue(150);
                sliderSpeechRate.addChangeListener(e -> sliderSpeechRateStateChanged(e));
                menuAudioParameters.add(sliderSpeechRate);

                //---- menuItemPitch ----
                menuItemPitch.setText("Pitch");
                menuAudioParameters.add(menuItemPitch);

                //---- sliderPitch ----
                sliderPitch.setMaximum(11);
                sliderPitch.addChangeListener(e -> sliderPitchStateChanged(e));
                menuAudioParameters.add(sliderPitch);
            }
            menuBar1.add(menuAudioParameters);

            //======== menuLineOptions ========
            {
                menuLineOptions.setText("Line Options");

                //---- menuLineToSpeech ----
                menuLineToSpeech.setText("Line to speech");
                menuLineToSpeech.addActionListener(e -> menuLineToSpeechActionPerformed(e));
                menuLineOptions.add(menuLineToSpeech);

                //---- menuReverseLineToSpeech ----
                menuReverseLineToSpeech.setText("Reverse line to speech");
                menuReverseLineToSpeech.addActionListener(e -> menuReverseLineToSpeechActionPerformed(e));
                menuLineOptions.add(menuReverseLineToSpeech);

                //---- menuEncodeLine ----
                menuEncodeLine.setText("Encode line and play it");
                menuEncodeLine.addActionListener(e -> menuEncodeLineActionPerformed(e));
                menuLineOptions.add(menuEncodeLine);
            }
            menuBar1.add(menuLineOptions);
            menuBar1.add(lineSearch);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- textArea1 ----
            textArea1.setLineWrap(true);
            scrollPane1.setViewportView(textArea1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

    }


    private JMenuBar menuBar1;
    private JMenu fileMenu;
    private JMenuItem menuNewDocument;
    private JMenuItem menuOpenDocument;
    private JMenuItem menuSaveDocument;
    private JMenuItem menuEditDocument;
    private JMenuItem menuExit;
    private JMenu textToSpeechMenu;
    private JMenuItem menuDocumentToSpeech;
    private JMenuItem menuReverseDocumentToSpeech;
    private JMenuItem menuItemPlayEncodedContents;
    private JMenu menuEncodingsOption;
    private JMenuItem menuSelectEncodingRot13;
    private JMenuItem menuSelectAtBashEncoding;
    private JMenu menuAudioParameters;
    private JMenuItem menuItemVolumeSelector;
    private JSlider sliderVolume;
    private JMenuItem menuItemSpeechRate;
    private JSlider sliderSpeechRate;
    private JMenuItem menuItemPitch;
    private JSlider sliderPitch;
    private JMenu menuLineOptions;
    private JMenuItem menuLineToSpeech;
    private JMenuItem menuReverseLineToSpeech;
    private JMenuItem menuEncodeLine;
    private JTextPane lineSearch;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;


    private JFileChooser fileChosen;

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public int getLineToPlay() {
        return Integer.parseInt(lineSearch.getText());
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }


}
