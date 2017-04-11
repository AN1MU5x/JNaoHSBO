package testruns;


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
import utillities.Utts;


/**
     * Created by Lisa on 07.04.2017.
     */
    public class Test_User_Surface extends Application implements EventHandler<ActionEvent> {
        Stage window;
        Scene scene1, scene2;
        Button btn1, btn2;
        GridPane grid1, grid2;
        Label name, port;
        Text scenetitle1, scenetitle2;
        HBox hbBtn1, hbBtn2;
        TextField userTextField, portTextField;


        public static void main(String[] args) throws Exception{
            launch(args);

        }

        @Override
        public void start(Stage primaryStage) {
            window = primaryStage;
            primaryStage.setTitle("Nao");

            //Erstes Fenster
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
            userTextField = new TextField();
            grid1.add(userTextField, 1, 1);

            //Portfeld und Eingabe
            port = new Label("Port:");
            grid1.add(port, 0, 2);
            portTextField = new TextField();
            grid1.add(portTextField, 1, 2);

            //Button Connect
            btn1 = new Button("Connect");
            hbBtn1 = new HBox(10);
            hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn1.getChildren().add(btn1);
            grid1.add(hbBtn1, 1, 4);
            btn1.setOnAction(this);

            //Zweites Fenster
            grid2 = new GridPane();
            grid2.setAlignment(Pos.CENTER);
            grid2.setHgap(10);
            grid2.setVgap(10);
            grid2.setPadding(new Insets(25, 25, 25, 25));

            scene2 = new Scene(grid2, 1200, 800);
            scenetitle2 = new Text("Hallo");
            scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid2.add(scenetitle2, 0, 0, 2, 1);

            btn2 = new Button("TCP change");
            hbBtn2 = new HBox(10);
            hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn2.getChildren().add(btn2);
            grid2.add(hbBtn2, 1, 4);
            btn2.setOnAction(this);

            primaryStage.setScene(scene1);
            primaryStage.show();
        }

        @Override
        public void handle(ActionEvent event){
            if(event.getSource()== btn1) {
                Utts.AppStart(userTextField.getText(),portTextField.getText());
                window.setScene(scene2);
                window.show();
            }
        }
    }

