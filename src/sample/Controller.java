package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Controller {
    public TextArea textArea;
    @FXML
    Button button1;
    @FXML
    TextField textField1;


    public void initialize() {


        textField1.setText("D:\\Programs\\DropBox\\Dropbox\\Homework\\4K\\Групова динаміка\\Program_test\\Test_text.txt");
    }



    public void button1Clicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Initial File");

        File file = new File("D:\\Programs\\DropBox\\Dropbox\\Homework\\4K\\Групова динаміка\\Program_test");
        fileChooser.setInitialDirectory(file);
        file = fileChooser.showOpenDialog(null);
        textField1.setText(file.getPath());
    }


    public void button3Clicked(ActionEvent actionEvent) {

        try {

            textArea.clear();
            File f = new File(textField1.getText());
            char[] CB2 = new char[(int) f.length()];
            Reader reader2 = new InputStreamReader(new FileInputStream(f), "cp1251");
            reader2.read(CB2);
            reader2.close();
            for (int i = 0; i < CB2.length; i++) {
                if(CB2[i]==','||CB2[i]=='.'||CB2[i]=='\\'||CB2[i]=='/'||CB2[i]=='!'||CB2[i]=='?'
                        ||CB2[i]=='<'||CB2[i]=='>'||CB2[i]=='{'||CB2[i]=='}'||CB2[i]=='['||CB2[i]==']'
                        ||CB2[i]=='|'||CB2[i]=='('||CB2[i]==')'||CB2[i]=='^'||CB2[i]=='&'||CB2[i]=='$'
                        ||CB2[i]=='#'||CB2[i]=='@'||CB2[i]=='"'||CB2[i]=='\''||CB2[i]=='~'||CB2[i]=='+'
                        ||CB2[i]=='='||CB2[i]==':'||CB2[i]==';'||CB2[i]=='№'||CB2[i]=='%'||CB2[i]=='_'||CB2[i]=='-')
                    CB2[i]=' ';
            }

            String words = new String(CB2);
            words=words.replace("  "," ");
            String[] partsText = words.split(" |\n|\t");
            textArea.setText(textArea.getText() + " " + Arrays.toString(partsText) + "\n");

            for (int i = 0; i < partsText.length; i++) {
                String tempS=partsText[i]+"z";
                if(tempS.length()>2)
                textArea.setText(textArea.getText() + " " + partsText[i]+" contains "+(tempS.split("а|у|е|о|ы|я|и|і|А|У|Е|О|Ы|Я|И|І|є|Є|ї|Ї").length-1) + " vowels\n");
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
