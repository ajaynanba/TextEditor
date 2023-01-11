package com.accio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import  java.util.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    JMenuItem newFile,openFile,saveFile;

    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    TextEditor(){

        frame = new JFrame();

        textArea = new JTextArea();

        menuBar = new JMenuBar();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newFile = new JMenuItem("New");
        openFile = new JMenuItem("open");
        saveFile = new JMenuItem("save");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionevent){

        if(actionevent.getSource()==cut){
            textArea.cut();
        }
        if(actionevent.getSource()==copy){
            textArea.copy();
        }
        if(actionevent.getSource()==paste){
            textArea.paste();
        }
        if(actionevent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionevent.getSource()==close){
            System.exit(0);
        }
        if(actionevent.getSource()==newFile){
            TextEditor newWindow = new TextEditor();
        }
        if(actionevent.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("c");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption==fileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try{
                    BufferedReader bf = new BufferedReader(new FileReader(filePath));
                    String intermediate = "",output = "";
                    while((intermediate = bf.readLine())!=null){
                        output+=intermediate+"|n";
                    }
                    textArea.setText(output);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(actionevent.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("c");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    textArea.write(bw);
                    bw.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
