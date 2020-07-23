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
import java.util.ListIterator;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MenuUsuarioController implements Initializable {
    
    private BD bd;
    
    private Bitacora bitacora;

    @FXML
    private TableView<Denominacion> tblDenominaciones;
    @FXML
    private TableColumn colDenominaciones;
    @FXML
    private TableView<Denominacion> tblCambio;
    @FXML
    private TableColumn colDenominacion;
    @FXML
    private TableColumn colCantidad;
    
    private ObservableList<Denominacion> denominaciones;
    private ObservableList<Denominacion> cambio;
    @FXML
    private Button btnSolCambio;
    @FXML
    private Button btnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bd = new BD();
        bitacora = new Bitacora();
        
        denominaciones = FXCollections.observableArrayList();
        cambio = FXCollections.observableArrayList();
        
        this.colDenominaciones.setCellValueFactory(new PropertyValueFactory("valor"));
        this.colDenominacion.setCellValueFactory(new PropertyValueFactory("valor"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidadDevolver"));
        
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
    private void solicitarCambio(ActionEvent event) {
        cambio.clear(); // Se limpia la lista cambio
        Denominacion d = this.tblDenominaciones.getSelectionModel().getSelectedItem();
        int cantSolicitada = d.getValor();
        
        if(d == null) { // En caso de que no se haya seleccionado ningun item de la tabla
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar una cantidad");

            alert.showAndWait();
        } else {            
            Denominacion aux = null;
            ObservableList<Denominacion> auxList = FXCollections.observableArrayList(); // Lista auxiliar en la que se almacenan las copias del cambio
            for(Denominacion currentTab : this.denominaciones) { // Se recorre toda la lista "denominaciones"
                currentTab.setCantidadDevolver(0);
                try{
                    aux = currentTab.clone();
                    // Mientras haya "monedas/billetes" de la denominacion actual y haya cambio por devolver entra
                    while(aux.getCantidadAlmacenada() != 0 && cantSolicitada > 0) {
                        
                        // Para realizar los cambios y las restas el resultado debe ser mayor o igual a 0
                        if(cantSolicitada - aux.getValor() >= 0) {
                            aux.aumentarCantidadDevolver(); // Aumenta en uno la cantidad a devolver de la "denominacion actual"
                            aux.disminuirCantidadAlmacenada(); // Disminuye en uno la cantidad almacenada en el sistema de la "denominacion actual"
                            cantSolicitada -= aux.getValor();
                        } else {
                            break;
                        }
                    }                   
                    auxList.add(aux);
                } catch (CloneNotSupportedException cnse) {
                    System.out.println("No se puede clonar");
                }
            }
            
            if(cantSolicitada == 0) {
                this.tblCambio.setItems(auxList); // Se muestran en la tabla "tblCambio" el contenido de la lista "auxList"
                
                this.denominaciones = auxList; // Se guardan los cambios en la lista "denominaciones" (lsita principal del sistema)
                bitacora.escribirBitacora("-----USUARIO----\n");
                bitacora.escribirBitacora("Total a retirar: "+d.getValor()+"\n");
                for(Denominacion currentTab : this.denominaciones) {
                    this.bd.updateCantDenominacion(currentTab.getValor(), currentTab.getCantidadAlmacenada());
                    String texto = "Denominacion: " + currentTab.getValor() + " |Se retiró: " + currentTab.getCantidadDevolver() + " | " + "Total: " + currentTab.getCantidadAlmacenada()+"\n";
                    bitacora.escribirBitacora(texto);
                }
                bitacora.escribirBitacora("----FIN USUARIO----\n");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No hay cambio suficiente en la maquina");
                
                alert.showAndWait();
                
                // En caso de que no se haya podido completar el cambio, se muestra que no se devolvio ni una sola moneda.
                for(Denominacion currentTab : this.denominaciones) {
                    currentTab.setCantidadDevolver(0);
                }
                this.tblCambio.refresh();
                
                
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
