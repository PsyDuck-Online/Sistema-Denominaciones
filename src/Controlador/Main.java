import Modelo.BD;
import Modelo.Denominacion;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    @Override
    public void start(Stage primaryStage) {
 
        try {
            
            BD bd = new BD();
            Denominacion d1000 = new Denominacion(1000);
            Denominacion d500 = new Denominacion(500);
            Denominacion d200 = new Denominacion(200);
            Denominacion d100 = new Denominacion(100);
            Denominacion d50 = new Denominacion(50);
            Denominacion d20 = new Denominacion(20);
            Denominacion d10 = new Denominacion(10);
            Denominacion d5 = new Denominacion(5);
            Denominacion d2 = new Denominacion(2);
            Denominacion d1 = new Denominacion(1);
            bd.insertDenominacion(d1000);
            bd.insertDenominacion(d500);
            bd.insertDenominacion(d200);
            bd.insertDenominacion(d100);
            bd.insertDenominacion(d50);
            bd.insertDenominacion(d20);
            bd.insertDenominacion(d10);
            bd.insertDenominacion(d5);
            bd.insertDenominacion(d2);
            bd.insertDenominacion(d1); 
            
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Vista/LoginMenuVista.fxml"));
            // Cargo la ventana
            Pane ventana = (Pane) loader.load();
 
            // Cargo el scene
            Scene scene = new Scene(ventana);
 
            // Seteo la scene y la muestro
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}