/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginMenuController implements Initializable {

    @FXML
    private Button btnAdministrador;
    @FXML
    private Button btnIngresarUsuario;
    @FXML
    private Button btnCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginAdministrador(ActionEvent event) {
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/LoginAdministradorVista.fxml"));
            
            Parent root = loader.load();
            
            LoginAdministradorController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            
            Stage myStage = (Stage) this.btnAdministrador.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void loginUsuario(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/LoginUserVista.fxml"));

                Parent root = loader.load();

                LoginUserController controlador = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();


                Stage myStage = (Stage) this.btnIngresarUsuario.getScene().getWindow();
                myStage.close();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void crearUsuario(ActionEvent event) {
    }
    
}
