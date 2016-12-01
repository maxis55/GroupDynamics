package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    public TextArea textArea;
    @FXML
    Button button1;
    @FXML
    TextField textField1;
    @FXML
    TextField textField2;
    @FXML
    TextField textField3;


    public void initialize() {


        textField1.setText("E:\\5.txt");
    }


    public void button1Clicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Initial File");

        File file = new File("E:\\");
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
                if (CB2[i] == ',' || CB2[i] == '.' || CB2[i] == '\\' || CB2[i] == '/' || CB2[i] == '!' || CB2[i] == '?'
                        || CB2[i] == '<' || CB2[i] == '>' || CB2[i] == '{' || CB2[i] == '}' || CB2[i] == '[' || CB2[i] == ']'
                        || CB2[i] == '|' || CB2[i] == '(' || CB2[i] == ')' || CB2[i] == '^' || CB2[i] == '&' || CB2[i] == '$'
                        || CB2[i] == '#' || CB2[i] == '@' || CB2[i] == '"' || CB2[i] == '\'' || CB2[i] == '~' || CB2[i] == '+'
                        || CB2[i] == '=' || CB2[i] == ':' || CB2[i] == ';' || CB2[i] == '№' || CB2[i] == '%' || CB2[i] == '_' || CB2[i] == '-')
                    CB2[i] = ' ';
            }

            String words = new String(CB2);
            words = words.replace("  ", " ");
            String[] partsText = words.split(" |\n|\t");
            textArea.setText(textArea.getText() + " " + Arrays.toString(partsText) + "\n");

            for (int i = 0; i < partsText.length; i++) {
                String tempS = partsText[i] + "z";
                System.out.println(partsText[i]);
                if (tempS.length() > 2)
                    textArea.setText(textArea.getText() + " " + partsText[i] + " contains " + (tempS.split("а|у|е|о|ы|я|и|і|А|У|Е|О|Ы|Я|И|І|є|Є|ї|Ї|ю|Ю").length - 1) + " vowels\n");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void button4Clicked(ActionEvent actionEvent) throws IOException {
        textArea.clear();
        String okonchanie = textField2.getText();
        ArrayList<String> list = new ArrayList<>();
        String path = textField1.getText().replace("\\", "//");

        Files.lines(Paths.get(path), Charset.forName("windows-1251")).forEachOrdered(list::add);
        int count = 0;
        boolean check = true;

        for (String s : list) {
            String[] ss = s.split("[ ,;:.!?\\t\\\\s]+");
            for (int i = 0; i < ss.length; i++) {
                for (int j = 0; j < ss[i].length(); j++) {
                    if (Character.toLowerCase(ss[i].charAt(j)) == 'а' || Character.toLowerCase(ss[i].charAt(j)) == 'о' || Character.toLowerCase(ss[i].charAt(j)) == 'у' || Character.toLowerCase(ss[i].charAt(j)) == 'е' || Character.toLowerCase(ss[i].charAt(j)) == 'и'
                            || Character.toLowerCase(ss[i].toLowerCase().charAt(j)) == 'я' || Character.toLowerCase(ss[i].charAt(j)) == 'э' || Character.toLowerCase(ss[i].charAt(j)) == 'ю' || Character.toLowerCase(ss[i].charAt(j)) == 'ы' || Character.toLowerCase(ss[i].charAt(j)) == 'ё' || Character.toLowerCase(ss[i].charAt(j)) == 'і') {
                        count++;
                    }
                }

                int schetchick = 0;
                check =true;
                for (int j = ss[i].length() - okonchanie.length(); j < ss[i].length(); j++) {
                    if (j < 0) {
                        check = false;
                        break;
                    }
                    if (Character.toLowerCase(ss[i].charAt(j)) != Character.toLowerCase(okonchanie.charAt(schetchick))) {
                        check = false;
                        break;
                    }
                    schetchick++;
                }
                try {
                    if (ss[i].length() != 0 && ss[i].length() != 1 && check == true && count == Integer.parseInt(textField3.getText())) {
                        textArea.setText(textArea.getText() + ss[i] + "  " + count + " vowels\n");
                    }
                    count = 0;
                } catch (Exception e) {
                        textArea.setText("Введите данные коректно");
                }
            }
        }
    }
}
