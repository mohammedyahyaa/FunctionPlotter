/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;

/**
 *
 * @author muham
 */
public class Task1 extends Application {

    Scene scene;
    String finalResult;
    double minXValue;
    double maxXvalue;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ScriptException {

        launch(args);

    }

    public double calC(String input) throws ScriptException {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return (double) engine.eval(input + "*1.0");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Task1");
        primaryStage.setWidth(1700);
        primaryStage.setHeight(1000);

        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();
        GridPane gridPane3 = new GridPane();

        scene = new Scene(gridPane1);
        gridPane1.add(gridPane2, 0, 2);

        Label l2 = new Label("Task one");
        gridPane1.add(l2, 0, 1);
        l2.setStyle("-fx-font: normal bold 50px 'serif'; -fx-text-fill: firebrick; -fx-padding:30px");

        Label l3 = new Label("Enter Your Equation");
        gridPane2.add(l3, 3, 0);
        l3.setStyle("-fx-font: normal bold 40px 'serif'; -fx-text-fill: firebrick");

        TextField input = new TextField();
        input.setPrefWidth(800);
        input.setPrefHeight(40);
        gridPane2.add(input, 3, 1);
        input.setPromptText("Enter Your Equation");
        input.setFont(Font.font("Serif", 20));

        Label resLabel = new Label("result :");
        gridPane3.add(resLabel, 4, 2);
        resLabel.setStyle("-fx-font: normal bold 50px 'serif'; -fx-text-fill: firebrick; -fx-padding:30px");

        Text result = new Text("");
        gridPane2.add(result, 5, 1);
        result.setStyle("-fx-font: normal bold 40px 'serif'; -fx-text-fill: firebrick");

        TextField xValue = new TextField();
        input.setPrefWidth(30);
        input.setPrefHeight(40);
        gridPane3.add(xValue, 3, 1);
        xValue.setPromptText("Enter X Value");
        xValue.setFont(Font.font("Serif", 20));

        TextField xMin = new TextField();
        xMin.setPrefWidth(30);
        xMin.setPrefHeight(40);
        gridPane3.add(xMin, 3, 2);
        xMin.setPromptText("Enter Min Of X");
        xMin.setFont(Font.font("Serif", 20));

        TextField xMax = new TextField();
        xMax.setPrefWidth(30);
        xMax.setPrefHeight(40);
        gridPane3.add(xMax, 3, 3);
        xMax.setPromptText("Enter Max Of X");
        xMax.setFont(Font.font("Serif", 20));

        gridPane2.add(gridPane3, 4, 1);

        Button calcButton = new Button("Calculate");
        gridPane2.add(calcButton, 3, 3);
        calcButton.setStyle(" -fx-text-fill: white; -fx-background-color: royalblue; -fx-padding:15px 50px; -fx-font-size:17px;-fx-font: normal bold 20px 'serif'");

        Button ClearButtob = new Button("Reset");
        gridPane2.add(ClearButtob, 4, 3);
        ClearButtob.setStyle(" -fx-text-fill: white; -fx-background-color: royalblue; -fx-padding:15px 50px; -fx-font-size:17px;-fx-font: normal bold 20px 'serif'");

        ClearButtob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!input.getText().isEmpty()) {
                    input.setText("");
                    result.setText("");
                    xValue.setText("");
                    xMin.setText("");
                    xMax.setText("");

                }
            }
        });

        calcButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String checkInput = input.getText();

                    if (input.getText().isEmpty() || xValue.getText().isEmpty() || xMin.getText().isEmpty() || xMax.getText().isEmpty()) {
                        JOptionPane pane = new JOptionPane();
                        JOptionPane.showMessageDialog(pane, "You Must Enter All Fields",
                                "Warnning Message", JOptionPane.WARNING_MESSAGE);
                    }

                    while (checkInput.contains("^")) {

                        int Locate = checkInput.indexOf("^");
                        char before = checkInput.charAt(Locate - 1);
                        char after = checkInput.charAt(Locate + 1);

                        checkInput = checkInput.replace("" + before + "" + "^" + "" + after + "", "Math.pow(" + before + "," + after + ")");
                        System.out.println(checkInput);

                    }

                    minXValue = Double.parseDouble(xMin.getText());
                    maxXvalue = Double.parseDouble(xMax.getText());

                    if (checkInput.contains("x") && maxXvalue > minXValue) {

                        checkInput = checkInput.replaceAll("x", xValue.getText());

                    }

                    finalResult = String.valueOf(calC(checkInput));
                    result.setText(finalResult);

                } catch (ScriptException ex) {
                    Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane pane = new JOptionPane();
                    if (maxXvalue < minXValue) {
                        JOptionPane.showMessageDialog(pane, "Max X Value must be greater than Min X ",
                                "Warnning Message", JOptionPane.WARNING_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(pane, "Please Enter Right Equation",
                            "Warnning Message", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        gridPane2.setVgap(50);
        gridPane1.setVgap(30);
        gridPane1.setHgap(50);
        gridPane2.setHgap(50);
        gridPane3.setHgap(30);
        gridPane3.setVgap(5);

        primaryStage.setScene(scene);
        primaryStage.show();
        gridPane1.setStyle("-fx-background-color:khaki");

    }

}
