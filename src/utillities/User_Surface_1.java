package utillities;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.helper.proxies.ALBattery;
import com.aldebaran.qi.helper.proxies.ALMemory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import vision.VisionCamera;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by Lisa on 07.04.2017.
 */
public class User_Surface_1 extends Application implements EventHandler<ActionEvent> {
    private static Stage window;
    private static Scene scene1, scene2;
    private static Button btn1, btn2, btnTalk;
    private static GridPane grid1, grid2;
    private static Label name, port, battery, temperatur, lCharge;
    private static Text scenetitle1, scenetitle21, scenetitle22;
    private static HBox hbBtn1, hbBtn2, box;
    private static TextField userTextField, portTextField;
    private static ALBattery charge, chargeA;
    private static String sCharge = "",sChargeA="";
    private static VisionCamera oVision;
    private static BufferedImage oLiveVideoBuffered;
    private static ImageView imgView;
    private static ALMemory word;
    private static String sWord;
    private static float fWord;
    private static TextField textField;
    public static WordRecognizedEvent recognizedEvent;

    //Benötigt als Datenaustausch zwichen den Threads
    public static String sBattery = "Batterie";
    public static String sTemperatur = "Temp";
    private static int iTmp = 0;
    private Timer t1 = new Timer();

    public static void open() throws Exception {
        Application.launch();
    }

    public static void main(String[] args) throws Exception{
        recognizedEvent = new WordRecognizedEvent();
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage)throws Exception {

        window1();
        window = primaryStage;
        window.setTitle("Nao");
        window.centerOnScreen();

        btn1.setOnAction(this);

        primaryStage.setScene(scene1);
        primaryStage.show();

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.4), event -> {
            try {
                aktualisieren();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
    @Override
    public void handle(ActionEvent event){
        if(event.getSource()== btn1) {
            Uts.AppStart(userTextField.getText(),portTextField.getText());
            try {
                Thread.sleep(1000);
                oVision = new VisionCamera();
                word = new ALMemory(Uts.getSESSION());
                charge = new ALBattery(Uts.getSESSION());
                sCharge = ""+(charge.getBatteryCharge());
                sWord = (String) ((List)word.getData("WordRecognized")).get(0);
                fWord = (float) ((List)word.getData("WordRecognized")).get(1);
                window2();
                recognizedEvent.run(Uts.getSESSION());

                btn2.setOnAction(this);
                btnTalk.setOnAction(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            window.setScene(scene2);
            window.centerOnScreen();


        }else if(event.getSource()==btn2){
            System.exit(0);
        }else if(event.getSource()==btnTalk){
            try {
                Uts.talk(textField.getText());
            } catch (Exception e) {
                System.out.println("Ausgabefehler!");
            }
        }
    }
    public static void window1(){
        grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));
        scene1 = new Scene(grid1, 300, 275);
        scenetitle1 = new Text("Welcome ");
        scenetitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle1, 0, 0, 2, 1);

        //Namensfeld und Eingabe
        name = new Label("Name:");
        grid1.add(name, 0, 1);
        userTextField = new TextField("Emma.local");
        grid1.add(userTextField, 1, 1);

        //Portfeld und Eingabe
        port = new Label("Port:");
        grid1.add(port, 0, 2);
        portTextField = new TextField("9559");
        grid1.add(portTextField, 1, 2);

        //Button Connect
        btn1 = new Button("Verbinden");
        hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid1.add(hbBtn1, 1, 4);
    }
    public static void window2() throws Exception {
        grid2 = new GridPane();
        box = new HBox(155);
        box.setFillHeight(true);
        box.setAlignment(Pos.CENTER);
        StackPane pane = new StackPane();
        pane.getChildren().add(box);
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(20);
        grid2.setVgap(20);
        grid2.setPadding(new Insets(10, 10, 10, 10));
        grid2.setGridLinesVisible(true);
        scene2 = new Scene(pane, 1200, 600);

        //Info
        scenetitle21 = new Text("Info ");
        scenetitle21.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid2.add(scenetitle21, 0, 0, 2, 1);

        //Batterie Zustand
        lCharge =new Label(sCharge+"%");
        battery = new Label("Batterie");
        grid2.add(battery,0,1);
        grid2.add(lCharge,1,1);

        //Temperatur Diagnose
        temperatur = new Label("Temperatur");
        grid2.add(temperatur,0,2);

        //Live Video
        oLiveVideoBuffered = new BufferedImage(150,150, BufferedImage.TYPE_INT_RGB);
        imgView = new ImageView(SwingFXUtils.toFXImage(oLiveVideoBuffered, null));
        box.getChildren().add(imgView);

        //Spracherkennung
        grid2.add(new Label("gehört"),0,3);
        grid2.add(new Label(sWord),1,3);
        grid2.add(new Label(String.valueOf(fWord)),2,3);

        //Sprechen
        btnTalk = new Button("Sprechen");
        grid2.add(new Label("Ausgabe"),0,4);
        textField = new TextField("Ich bin Emma!");
        grid2.add(textField,1,4);
        grid2.add(btnTalk,2,4);

        //Programm beenden
        btn2 = new Button("Schließen");
        hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid2.add(hbBtn2, 4, 5);


        box.getChildren().add(grid2);
    }

    public static synchronized void aktualisieren()throws Exception{
        iTmp++;

        try {
            if (Uts.getSESSION() != null) {
                chargeA = new ALBattery(Uts.getSESSION());
                sChargeA = "" + (chargeA.getBatteryCharge());
                lCharge.setText(sChargeA);
                oLiveVideoBuffered = oVision.getImage();
                imgView.setImage(SwingFXUtils.toFXImage(oLiveVideoBuffered, null));
                imgView.setScaleX(2);
                imgView.setScaleY(2);
                sWord = (String) ((ArrayList)word.getData("WordRecognized")).get(0);
                fWord = (float) ((ArrayList)word.getData("WordRecognized")).get(1);
            }
        }catch(ClassCastException cce){
            System.out.println("NAO neustarten bitte!");
            System.exit(0);

        }
    }
}