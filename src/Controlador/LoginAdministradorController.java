/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginAdministradorController implements Initializable {

    @FXML
    private TextField txtName;
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegresar;
    
    private Administrador admin;
    @FXML
    private PasswordField pswPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin = new Administrador();
    }    

    @FXML
    private void login(ActionEvent event) {
        String name = this.txtName.getText();
        String password = this.pswPass.getText();
        
        System.out.println("Introducido:"+name+"\nAdmin:"+this.admin.getName());

        
        if(!name.equals(this.admin.getName()) || !password.equals(this.admin.getPass())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Nombre/Contraseña incorrecta");

            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/MenuAdministradorVista.fxml"));

                Parent root = loader.load();

                MenuAdministradorController controlador = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();


                Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
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
            
            
            Stage myStage = (Stage) this.btnRegresar.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
