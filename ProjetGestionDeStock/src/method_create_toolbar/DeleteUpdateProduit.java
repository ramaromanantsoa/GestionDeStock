package method_create_toolbar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import commande.CommandWindow;
import items_and_list.GetListProduit;
import main_package.MainWindow;

public class DeleteUpdateProduit extends MainWindow {
	private static final long serialVersionUID = 1L;
	private static JTextField textID;
	private static JTextField textValue;
	private static JButton btnDelete ;
	private static JButton btnUpdate;
	private static JComboBox<String> comboBoxColonne;
	
	public static JToolBar createToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setForeground(Color.BLUE);
		toolbar.setBackground(Color.WHITE);
		toolbar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		JLabel lblID = new JLabel("ID");
		lblID.setBackground(Color.LIGHT_GRAY);
		lblID.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblID.setBounds(16, 376, 97, 26);
		toolbar.add(lblID);
		
		textID = new JTextField();
		textID.setColumns(5);
		toolbar.add(textID);
		
		toolbar.addSeparator();
		
		btnDelete = new JButton();
		btnDelete.setBounds(115, 372, 30, 35);
		btnDelete.setIcon(new ImageIcon("icons/delete.png"));
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDeleteAction(e);
			}
		});
		toolbar.add(btnDelete);

		toolbar.addSeparator();
		
		String colonneItems[] = {"", "Prix", "Nom du produit", "Unité", "Catégorie"};
		comboBoxColonne = new JComboBox<String>(colonneItems);
		comboBoxColonne.setBounds(155, 372, 134, 34);
		toolbar.add(comboBoxColonne);
		
		textValue = new JTextField();
		textValue.setColumns(15);
		toolbar.add(textValue);
		
		btnUpdate = new JButton();
		btnUpdate.setBounds(430, 372, 30, 35);
		btnUpdate.setIcon(new ImageIcon("icons/update.png"));
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnUpdateAction(e);
			}
		});
		toolbar.add(btnUpdate);
		
		return toolbar;
	}
	
	//////ACTION DU BOUTON btnDeleteAction ^^^^ BEGIN /////////////
	private static void btnDeleteAction(ActionEvent e) {
		try {
			
			statement.execute("DELETE FROM Produit WHERE ID_Produit = "+textID.getText());
			
			//////// ACTUALISATION DE LA TABLE --- BEGIN //////////////
			DefaultTableModel model = (DefaultTableModel)tabProduit.getModel();
			model.setRowCount(0);
			GetListProduit.getListOfProduit();
			//////// ACTUALISATION DE LA TABLE --- END //////////////
		}
		catch (Exception except) {
			JOptionPane.showMessageDialog(MainWindow.tabProduit, "Veuillez indiquer l'ID de l'enregistrement à supprimer");
		}
	}
	//////ACTION DU BOUTON btnDeleteAction ^^^^ END /////////////
		
	//////ACTION DU BOUTON btnUpdateAction ^^^^ BEGIN /////////////
	private static void btnUpdateAction(ActionEvent e) {
		ResultSet idUniteTest, idUnite, idCategorieTest, idCategorie;
		Integer intIdUnite = 1;
		Integer intIdCategorie = 1;
		try {
			
			String triColonne = (String)comboBoxColonne.getSelectedItem();
			
			if (triColonne == "Prix") {
				statement.execute("UPDATE Produit SET Prix_Produit = "+textValue.getText()+" WHERE ID_Produit = "+textID.getText());
			}
			else if (triColonne == "Nom du produit") {
				statement.execute("UPDATE Produit SET Nom_Produit = '"+textValue.getText()+"' WHERE ID_Produit = "+textID.getText());
			}
			//////////////  U N I T E  ///////////////
			else if (triColonne == "Unité") {		
				idUniteTest = statement.executeQuery("SELECT ID_Unite FROM Unite WHERE Type_Unite = '" + textValue.getText() + "'");
				if ( idUniteTest.next() == true ) {
					idUnite = statement.executeQuery("SELECT ID_Unite FROM Unite WHERE Type_Unite = '" + textValue.getText() + "'");
				}
				else{
					statement.execute("INSERT INTO Unite(Type_Unite) VALUES('" + textValue.getText() + "')");
					idUnite = statement.executeQuery("SELECT ID_Unite FROM Unite WHERE Type_Unite = '" + textValue.getText() + "'");
				}
				while (idUnite.next()) {
					intIdUnite = idUnite.getInt("ID_Unite");
				}
				statement.execute("UPDATE Produit SET ID_Unite = "+intIdUnite+" WHERE ID_Produit = "+textID.getText());
			}
			//////////////  C A T E G O R I E  ///////////////
			else if (triColonne == "Catégorie") {		
				idCategorieTest = statement.executeQuery("SELECT ID_Categorie FROM Categorie WHERE Nom_Categorie = '" + textValue.getText() + "'");
				if ( idCategorieTest.next() == true ) {
					idCategorie = statement.executeQuery("SELECT ID_Categorie FROM Categorie WHERE Nom_Categorie = '" + textValue.getText() + "'");
				}
				else{
					statement.execute("INSERT INTO Categorie(Nom_Categorie) VALUES('" + textValue.getText() + "')");
					idCategorie = statement.executeQuery("SELECT ID_Categorie FROM Categorie WHERE Nom_Categorie = '" + textValue.getText() + "'");
				}
				while (idCategorie.next()) {
					intIdCategorie = idCategorie.getInt("ID_Categorie");
				}
				statement.execute("UPDATE Produit SET ID_Categorie = "+intIdCategorie+" WHERE ID_Produit = "+textID.getText());
			}
			else {
				JOptionPane.showMessageDialog(MainWindow.tabProduit, "Nom de colonne non indiqué!");
			}
			
			//////// ACTUALISATION DE LA TABLE --- BEGIN //////////////
			DefaultTableModel model = (DefaultTableModel)tabProduit.getModel();
			model.setRowCount(0);
			GetListProduit.getListOfProduit();
			CommandWindow.actualisationTableProduit();
			//////// ACTUALISATION DE LA TABLE --- END //////////////
		}
		catch (Exception except) {
			JOptionPane.showMessageDialog(MainWindow.tabProduit, "Veuillez indiquer l'ID de l'enregistrement à modifier!");
		}
	}
	//////ACTION DU BOUTON btnUpdateAction ^^^^ END /////////////
}
