/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginUserController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnVolver;
    
    private Usuario user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new Usuario("usuario", "usuario");
    }    

    @FXML
    private void logIn(ActionEvent event) {
        String name = this.txtName.getText();
        String pass = this.txtPass.getText();
        
        if(!name.equals(this.user.getName()) || !pass.equals(this.user.getPass())) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Nombre/Contraseña incorrecta");

            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/menuUsuarioVista.fxml"));

                Parent root = loader.load();

                MenuUsuarioController controlador = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();


                Stage myStage = (Stage) this.btnLogIn.getScene().getWindow();
                myStage.close();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void abrirLoginMenu(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/LoginMenuVista.fxml"));

                Parent root = loader.load();

                LoginMenuController controlador = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();


                Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
                myStage.close();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
