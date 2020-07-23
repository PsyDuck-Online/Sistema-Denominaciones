/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Bitacora;
import Modelo.Denominacion;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MenuAdministradorController implements Initializable {

    @FXML
    private TableView<Denominacion> tblDenominaciones;
    @FXML
    private Button btnRetirar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnBitacora;
    @FXML
    private TextField txtCantNueva;
    @FXML
    private TextField txtCantAlmacenada;
    @FXML
    private Button btnVolver;
    @FXML
    private TableColumn colDenominaciones;
    
    private ObservableList<Denominacion> denominaciones;
    
    private Bitacora bitacora;
    
    private BD bd;
    
    @FXML
    private Button btnRetirarTodo;
    @FXML
    private TextArea areaBitacora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bd = new BD();
        
        bitacora = new Bitacora();
        
        denominaciones = FXCollections.observableArrayList();
        
        this.colDenominaciones.setCellValueFactory(new PropertyValueFactory("valor"));
        
        Denominacion d1000 = new Denominacion(bd.selectValDenominacion(1000),bd.selectCantDenominacion(1000));
        Denominacion d500 = new Denominacion(bd.selectValDenominacion(500),bd.selectCantDenominacion(500));
        Denominacion d200 = new Denominacion(bd.selectValDenominacion(200),bd.selectCantDenominacion(200));
        Denominacion d100 = new Denominacion(bd.selectValDenominacion(100),bd.selectCantDenominacion(100));
        Denominacion d50 = new Denominacion(bd.selectValDenominacion(50),bd.selectCantDenominacion(50));
        Denominacion d20 = new Denominacion(bd.selectValDenominacion(20),bd.selectCantDenominacion(20));
        Denominacion d10 = new Denominacion(bd.selectValDenominacion(10),bd.selectCantDenominacion(10));
        Denominacion d5 = new Denominacion(bd.selectValDenominacion(5),bd.selectCantDenominacion(5));
        Denominacion d2 = new Denominacion(bd.selectValDenominacion(2),bd.selectCantDenominacion(2));
        Denominacion d1 = new Denominacion(bd.selectValDenominacion(1),bd.selectCantDenominacion(1));
        
        this.denominaciones.add(d1000);
        this.denominaciones.add(d500);
        this.denominaciones.add(d200);
        this.denominaciones.add(d100);
        this.denominaciones.add(d50);
        this.denominaciones.add(d20);
        this.denominaciones.add(d10);
        this.denominaciones.add(d5);
        this.denominaciones.add(d2);
        this.denominaciones.add(d1);
        
        this.tblDenominaciones.setItems(denominaciones);
    }    

    @FXML
    private void retirar(ActionEvent event) {
        try {
            Denominacion d = this.tblDenominaciones.getSelectionModel().getSelectedItem();
            int cant = Integer.parseInt(this.txtCantNueva.getText());
            
            if(d == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Debes seleccionar una cantidad");

                alert.showAndWait();
            } else {
                if ((d.getCantidadAlmacenada() - cant) < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficiente saldo en la caja");

                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmar");
                    alert.setHeaderText(null);
                    alert.setContentText("Seguro que quieres retirar esta cantidad?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK){
                        d.retirar(cant);
                    
                        this.tblDenominaciones.refresh();

                        this.txtCantAlmacenada.setText(d.getCantidadAlmacenada()+"");
                        
                        String texto = "Denominacion: " + d.getValor() + " |Se retiró: " + cant + " | " + "Total: " + d.getCantidadAlmacenada()+"\n";
                        bitacora.escribirBitacora(texto);
                        
                        this.bd.updateCantDenominacion(d.getValor(), d.getCantidadAlmacenada());
                    } else {
                        // No se hace nada
                    }
                    
                }
            }
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Formato de catnidad erroneo");
        }
    }

    @FXML
    private void agregar(ActionEvent event) {
        try {
            Denominacion d = this.tblDenominaciones.getSelectionModel().getSelectedItem();
            int cant = Integer.parseInt(this.txtCantNueva.getText());

            if(d == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Debes seleccionar una cantidad");

                alert.showAndWait();
            } else {                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar");
                alert.setHeaderText(null);
                alert.setContentText("Seguro que quieres agregar esta cantidad?");
                Optional<ButtonType> result = alert.showAndWait();
                
                if(result.get() == ButtonType.OK) {
                    d.agregar(cant);
                
                    this.tblDenominaciones.refresh();

                    this.txtCantAlmacenada.setText(d.getCantidadAlmacenada()+"");
                    
                    String texto = "Denominacion: " + d.getValor() + "|Se agrego: " + cant + " | " + "Total: " + d.getCantidadAlmacenada() + "\n";
                    bitacora.escribirBitacora(texto);
                    
                    this.bd.updateCantDenominacion(d.getValor(), d.getCantidadAlmacenada());
                } else {
                    // No se hace nada
                }
            
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Formato de catnidad erroneo");
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

    @FXML
    private void seleccionar(MouseEvent event) {
        
        Denominacion d = this.tblDenominaciones.getSelectionModel().getSelectedItem();
        
        if(d != null) {
            this.txtCantAlmacenada.setText(d.getCantidadAlmacenada() + "");            
        }
        
    }

    @FXML
    private void retirarTodo(ActionEvent event) {
        Denominacion d = this.tblDenominaciones.getSelectionModel().getSelectedItem();

        if(d == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar una cantidad");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText(null);
            alert.setContentText("Seguro que quieres retirar todo de esta denominacion?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                d.setCantidadAlmacenada(0);

                this.txtCantAlmacenada.setText(d.getCantidadAlmacenada() + "");

                String data = "Denominacion: " + d.getValor() + " | Se retiro todo | Total: " + d.getCantidadAlmacenada() + "\n";
                bitacora.escribirBitacora(data);
                
                this.bd.updateCantDenominacion(d.getValor(), d.getCantidadAlmacenada());
            }else {

            }
        }
        
    }

    @FXML
    private void leerBitacora(ActionEvent event) {
        
        this.areaBitacora.setText(this.bitacora.leerBitacora());
        
    }
    
}
