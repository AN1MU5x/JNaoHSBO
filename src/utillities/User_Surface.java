package utillities;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.helper.proxies.ALBattery;
import com.aldebaran.qi.helper.proxies.ALLeds;
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
import motion.Position;
import vision.VisionCamera;

import java.awt.image.BufferedImage;
import java.util.*;

public class User_Surface extends Application implements EventHandler<ActionEvent> {
    private static Stage window;
    private static Scene scene1, scene2;
    private static Button btn1, btnClose, btnTalk, btnStand, btnSit, btnLie, btnCrouch, btnWigWag;
    private static GridPane grid1, grid2;
    private static Label name, port, battery, temperatur, lCharge,lrecword,probability;
    private static Text scenetitle1, scenetitle21;
    private static HBox hbBtn1, hbBtn2, box, poseBox;
    private static TextField userTextField, portTextField;
    private static ALBattery chargeA;
    private static String sCharge = "",sChargeA="",sName,sPort;
    private static VisionCamera oVision;
    private static BufferedImage oLiveVideoBuffered;
    private static ImageView imgView;
    private static ALMemory word;
    private static String sWord;
    private static float fWord;
    private static TextField textField;
    private static WordRecognizedEvent recognizedEvent;

    //Benötigt als Datenaustausch zwichen den Threads
    private static int iTmp = 0;
    private Timer t1 = new Timer();


    //für den Aufruf der Oberfläche aus einer anderen Methode
    public static void open() throws Exception {
        Application.launch();
    }

    public static void main(String[] args) throws Exception{
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage)throws Exception {
        recognizedEvent = new WordRecognizedEvent();
        window1();
        window = primaryStage;
        window.setTitle("Nao");
        window.centerOnScreen();

        btn1.setOnAction(this);

        primaryStage.setScene(scene1);
        primaryStage.show();

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.25), event -> {
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

            //Start des Programms mit eingegebener Addresse des Roboters
            sName = userTextField.getText();
            sPort = portTextField.getText();
            Uts.AppStart(userTextField.getText(),portTextField.getText());

            try {
                Thread.sleep(1000);
                chargeA = new ALBattery(Uts.getSESSION());
                oVision = new VisionCamera();
                oVision.start();
                word = new ALMemory(Uts.getSESSION());
                sCharge = ""+(chargeA.getBatteryCharge());
                sWord = (String) ((List)word.getData("WordRecognized")).get(0);
                fWord = (float) ((List)word.getData("WordRecognized")).get(1);
                window2();
                recognizedEvent.run(Uts.getSESSION());

                btnClose.setOnAction(this);
                btnTalk.setOnAction(this);
                btnLie.setOnAction(this);
                btnSit.setOnAction(this);
                btnStand.setOnAction(this);
                btnCrouch.setOnAction(this);
                btnWigWag.setOnAction(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            window.setScene(scene2);
            window.centerOnScreen();


            //Aufruf verschiedener Funktionen durch Knopfdruck
        }else if(event.getSource()== btnClose){
            try {
                Uts.talk("Auf Wiedersehen!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        }else if(event.getSource()==btnTalk){
            try {
                Uts.talk(textField.getText());
            } catch (Exception e) {
                System.out.println("Ausgabefehler!");
            }
        }else if(event.getSource()==btnStand){
            try {
                Position.stehen(Uts.getSESSION());
            } catch (Exception e) {
                System.out.println("Fehler: Bewegung");
            }
        }else if(event.getSource()==btnSit){
            try {
                Position.sitzen(Uts.getSESSION());
            } catch (Exception e) {
                System.out.println("Fehler: Bewegung");
            }
        }else if(event.getSource()==btnLie){
            try {
                Position.liegenRuecken(Uts.getSESSION());
            } catch (Exception e) {
                System.out.println("Fehler: Bewegung");
            }
        }else if(event.getSource()==btnWigWag) {
            try {
                Position.winken(Uts.getSESSION());
            } catch (Exception e) {
                System.out.println("Fehler: Bewegung");
            }
        }else if(event.getSource()==btnCrouch){
            try {
                Position.hocke(Uts.getSESSION());
            } catch (Exception e) {
                System.out.println("Fehler: Bewegung");
            }
        }
    }
    public static void window1(){

        //StartUp Oberfläche
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

        //Bedienoberfläche
        grid2 = new GridPane();
        box = new HBox(160);
        box.setFillHeight(true);
        box.setAlignment(Pos.CENTER);
        StackPane pane = new StackPane();
        pane.getChildren().add(box);
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(20);
        grid2.setVgap(20);
        grid2.setPadding(new Insets(10, 5, 10, 5));
        grid2.setGridLinesVisible(false);
        scene2 = new Scene(pane, 1000, 500);

        //Info
        scenetitle21 = new Text("Info ");
        scenetitle21.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid2.add(scenetitle21, 0, 0, 2, 1);

        //Batterie Zustand
        lCharge =new Label(sCharge );
        battery = new Label("Batterie");
        grid2.add(battery,0,2);
        grid2.add(lCharge,1,2);

        //Roboter Daten(Wurden im ersten Fenster eingegeben)
        temperatur = new Label("Roboter");
        grid2.add(temperatur,0,1);
        grid2.add(new Label(sName),1,1);
        grid2.add(new Label("Port: "+sPort),2,1);

        //Live Video
        //(einzelenes Bild von der Cam, dass immer neu erstellt und unter "aktualisieren" ersetzt wird)
        oLiveVideoBuffered = new BufferedImage(150,150, BufferedImage.TYPE_INT_RGB);
        imgView = new ImageView(SwingFXUtils.toFXImage(oLiveVideoBuffered, null));
        box.getChildren().add(imgView);

        //Spracherkennung
        //(Wörter und Sicherheit werden unter "aktualisieren" erneuert)
        grid2.add(new Label("gehört"),0,3);
        lrecword =new Label(sWord);
        grid2.add(lrecword,1,3);
        probability= new Label(String.valueOf(fWord));
        grid2.add(probability,2,3);

        //Sprechen
        //Eingegebener Text kann direkt vom Roboter gesagt werden
        btnTalk = new Button("Sprechen");
        grid2.add(new Label("Ausgabe"),0,4);
        textField = new TextField("Ich bin Emma!");
        grid2.add(textField,1,4);
        grid2.add(btnTalk,2,4);

        //Bewegen
        //Vorgefertigte Posen sind per Button aktivierbar
        //Kamerabild friert ein währen der Bewegung
        poseBox = new HBox(5);
        btnStand = new Button("stehen");
        btnSit = new Button("sitzen");
        btnLie = new Button("liegen");
        btnCrouch = new Button("hocken");
        btnWigWag = new Button("winken");
        grid2.add(new Label("Pose"),0,5);
        poseBox.getChildren().addAll(btnStand,btnSit,btnLie,btnCrouch, btnWigWag);
        grid2.add(poseBox,1,5,2,1);

        //Programm beenden
        btnClose = new Button("Schließen");
        hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btnClose);
        grid2.add(hbBtn2, 4, 6);


        box.getChildren().add(grid2);
    }

    public static synchronized void aktualisieren()throws Exception{
        //Aktualisierung der Oberfläche für sich ändernde Daten

        try {
            if (Uts.getSESSION() != null) {
                //Aktualisierung Batterieladung
                sChargeA = "" + (chargeA.getBatteryCharge()+"%");
                lCharge.setText(sChargeA);

                //Aktualisierung Bild(LiveCam)
                oLiveVideoBuffered = oVision.getBufferedImage();
                imgView.setImage(SwingFXUtils.toFXImage(oLiveVideoBuffered, null));
                imgView.setScaleX(2);
                imgView.setScaleY(2);

                //Aktualisierung Worterkennung
                sWord = (String) ((ArrayList)word.getData("WordRecognized")).get(0);
                lrecword.setText(sWord);
                fWord = (float) ((ArrayList)word.getData("WordRecognized")).get(1);
                probability.setText(String.valueOf(fWord));
            }
        }catch(ClassCastException cce){
            //Catch für NULLPOINTEREXCEPTION
            //interner Fehler des Roboters
            //ersatz für die Ausgabe der Fehlermeldung mit bitte zum Neustart, da dieser das Problem löst
            System.out.println("NAO neustarten bitte!");
            System.exit(0);

        }
    }
}