package controles;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.LocalTime;
import java.util.List;

import Banco.Conexao;
import entidades.Curso;
import entidades.Horario;
import entidades.Materia;
import entidades.Professor;
import entidades.Questionmarks;
import janelas.JanelaEditarMateriasCursos;
import janelas.JanelaEditarProfessores;

public class CelulaComboBox extends TableCell<Horario, String>{
	 private ComboBox<String> comboBox;


     public CelulaComboBox()
     {
         comboBox = new ComboBox<>();
     }


     @Override
     public void startEdit()
     {
         if ( !isEmpty() )
         {
             super.startEdit();
             ObservableList<String> listnew = FXCollections.observableArrayList();
             List<Questionmarks> lsitassa = Conexao.selectQuery("from Questionmarks where diasemana = '"+ getTableView().getColumns().get(1).getText()+"-Feira' and hora_entrada <= '"+ getTableView().getItems().get( getIndex() ).getHora_disp() + "' and hora_saida >= '" + getTableView().getItems().get(getIndex()+1).getHora_disp() + "'");
             for (Questionmarks q : lsitassa){
            	 for(Materia m : q.getProfessor().getMaterias()){
            		 listnew.add(q.getProfessor().getNome_professor() + "-" + m.getNome_materia());
            	 }
             }

             comboBox.setItems(listnew);
             comboBox.getSelectionModel().select( 1 );

             comboBox.focusedProperty().addListener( new ChangeListener<Boolean>()
             {
                 @Override
                 public void changed( ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue )
                 {
                     if ( !newValue )
                     {
                         commitEdit(comboBox.getSelectionModel().getSelectedItem());
                     }
                 }
             } );

             setText( null );
             setGraphic( comboBox );
         }
     }


     @Override
     public void cancelEdit()
     {
         super.cancelEdit();

         setText( ( String ) getItem() );
         setGraphic( null );
     }


     @Override
     public void updateItem( String item, boolean empty )
     {
         super.updateItem( item, empty );

         if ( empty )
         {
             setText( null );
             setGraphic( null );
         }
         else
         {
             if ( isEditing() )
             {
                 setText( null );
                 setGraphic( comboBox );
             }
             else
             {
                 setText( getItem() );
                 setGraphic( null );
             }
         }
     }
 }
