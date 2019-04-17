package method_create_toolbar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import items_and_list.GetListProduit;
import main_package.Connector;
import main_package.MainWindow;

public class SearchBarProduit extends MainWindow {
	private static final long serialVersionUID = 1L;
	private static JTextField textSearch;
	private static JComboBox<String> comboBoxTri;
	public static JToolBar createToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setForeground(Color.BLUE);
		toolbar.setBackground(Color.WHITE);
		toolbar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		/***** COMBOBOX TRI *****/
		String typeTri[] = {"Nom du produit", "Catégorie"};
		comboBoxTri = new JComboBox<String>(typeTri);
		toolbar.add(comboBoxTri);
		
		/***** CHAMP DE RECHERCHE *****/
		textSearch = new JTextField();
		textSearch.setColumns(15);
		toolbar.add(textSearch);
		
		/***** BOUTON DE RECHERCHE *****/
		JButton btnSearch = new JButton();
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnSearch.setIcon(new ImageIcon("icons/search.png"));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchProduit(e);
			}
		});
		toolbar.add(btnSearch);
		
		toolbar.addSeparator();
		
		/***** BOUTON AFFICHER TOUS *****/
		JButton btnShowAll = new JButton();
		btnShowAll.setForeground(Color.BLACK);
		btnShowAll.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnShowAll.setIcon(new ImageIcon("icons/all.png"));
		btnShowAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GetListProduit.getListOfProduit();
			}
		});
		toolbar.add(btnShowAll);
				
		return toolbar;
	}
	
	private static void searchProduit(ActionEvent ev) {
		listOfProduit = new	DefaultTableModel();
		String requete = new String();
		String selectedTri = (String)comboBoxTri.getSelectedItem();
		
		ResultSet result = null;
		
		try {

			if(selectedTri.equals("Catégorie")) {
				requete = "SELECT p.ID_Produit, p.Nom_Produit, u.Type_Unite, p.Quantite_Produit, p.Prix_Produit, c.Nom_Categorie "
						+ "FROM Unite u INNER JOIN (Produit p INNER JOIN Categorie c ON p.ID_Categorie = c.ID_Categorie) "
						+ "ON p.ID_Unite = u.ID_Unite WHERE c.Nom_Categorie LIKE ? "
						+ "ORDER BY c.Nom_Categorie ASC";
			}
			else {
				requete = "SELECT p.ID_Produit, p.Nom_Produit, u.Type_Unite, p.Quantite_Produit, p.Prix_Produit, c.Nom_Categorie "
						+ "FROM Unite u INNER JOIN (Produit p INNER JOIN Categorie c ON p.ID_Categorie = c.ID_Categorie) "
						+ "ON p.ID_Unite = u.ID_Unite WHERE p.Nom_Produit LIKE ? "
						+ "ORDER BY c.Nom_Categorie ASC";
			}

			PreparedStatement statSearch= Connector.connect().prepareStatement(requete);
			statSearch.setString(1, textSearch.getText());
			
			result = statSearch.executeQuery();
			
			listOfProduit.addColumn("ID");
			listOfProduit.addColumn("Nom du produit");
			listOfProduit.addColumn("Unité");
			listOfProduit.addColumn("Prix unitaire");
			listOfProduit.addColumn("Quantité");
			listOfProduit.addColumn("Catégorie");
			
			while(result.next()) {
				listOfProduit.addRow(new Object[] {
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(5),
					result.getString(4),
					result.getString(6)
				});
			}
			tabProduit.setModel(listOfProduit);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
}
