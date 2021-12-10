package sample;

import javafx.application.Application;
import com.sun.glass.ui.*;
import com.sun.glass.ui.Cursor;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.input.*;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Random;
import javafx.scene.control.*;
import sample.MyTreeSetLab9;

public class MyLab9 extends Application {
    public static final int SCENE_WIDTH = 1024;
    public static final int SCENE_HEIGHT = 640;
    public static final int MAX_VALUE = Short.MAX_VALUE;
    public void start(Stage primaryStage)
    {
        Pane pane = new Pane();
        MyTreeSetLab9<Integer> myTree = new MyTreeSetLab9<>();

        Random rand = new Random();
        pane.setOnMouseClicked(evt -> {
            if (evt.getButton() == MouseButton.SECONDARY) {
                int rint = rand.nextInt(MAX_VALUE);
                System.out.print("Adding " + rint + "...");
                boolean result = myTree.add(rint);
                System.out.println(result ? "good" : "bad");
                System.out.println(myTree);
            }
        });

        pane.getChildren().add(myTree);

        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("271 Lab-niner");
        primaryStage.show();
    }


}