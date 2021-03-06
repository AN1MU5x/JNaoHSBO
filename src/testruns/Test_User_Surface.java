package testruns;

import com.aldebaran.qi.helper.proxies.ALBattery;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utillities.Uts;

/** Created by Lisa on 07.04.2017.
     */
    public class Test_User_Surface extends Application implements EventHandler<ActionEvent> {
        private Stage window;
        private Scene scene1, scene2;
        private Button btn1, btn2;
        private GridPane grid1, grid2;
        private Label name, port, battery, temperatur, lCharge;
        private Text scenetitle1, scenetitle21, scenetitle22;
        private HBox hbBtn1, hbBtn2;
        private TextField userTextField, portTextField;
        private ALBattery charge;
        private String sCharge;

    public static void main(String[] args) throws Exception{
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage)throws Exception {
            window = primaryStage;
            window.setTitle("Nao");
            window.centerOnScreen();

            window1();

            primaryStage.setScene(scene1);
            primaryStage.show();
        }
        @Override
        public void handle(ActionEvent event){
            if(event.getSource()== btn1) {
                Uts.AppStart(userTextField.getText(),portTextField.getText());
                try {
                    Thread.sleep(1000);
                    charge = new ALBattery(Uts.getSESSION());
                    sCharge = ""+(charge.getBatteryCharge());
                    System.out.println("\n"+sCharge+"\n");
                    window2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                window.setScene(scene2);
                window.centerOnScreen();
                window.show();

            }else if(event.getSource()==btn2){
                System.exit(0);
            }
        }
        public void window1(){
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
            btn1.setOnAction(this);
        }
        public void window2() throws Exception {
            grid2 = new GridPane();
            grid2.setAlignment(Pos.CENTER);
            grid2.setHgap(100);
            grid2.setVgap(20);
            grid2.setPadding(new Insets(10, 10, 10, 10));
            grid2.setGridLinesVisible(true);
            scene2 = new Scene(grid2, 1200, 850);

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

            //Programm beenden
            btn2 = new Button("Schließen");
            hbBtn2 = new HBox(10);
            hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn2.getChildren().add(btn2);
            grid2.add(hbBtn2, 10, 35);
            btn2.setOnAction(this);

        }

    }

