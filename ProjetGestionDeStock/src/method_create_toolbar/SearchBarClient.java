package method_create_toolbar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import client.ClientWindow;
import items_and_list.GetListClient;;

public class SearchBarClient extends ClientWindow {
	private static final long serialVersionUID = 1L;
	private static JTextField textSearch;
	private static JComboBox<String> comboBoxTri;
	private static JComboBox<String> comboBoxRightTri;
	private static Statement statement;
	public static JToolBar createToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setForeground(Color.BLUE);
		toolbar.setBackground(Color.WHITE);
		toolbar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		/***** COMBOBOX TRI *****/
		String typeTri[] = {"Nom", "Prénom"};
		comboBoxTri = new JComboBox<String>(typeTri);
		toolbar.add(comboBoxTri);
		
		/***** CHAMP DE RECHERCHE *****/
		textSearch = new JTextField();
		textSearch.setColumns(18);
		toolbar.add(textSearch);
		
		/***** BOUTON DE RECHERCHE *****/
		JButton btnSearch = new JButton();
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnSearch.setIcon(new ImageIcon("icons/search.png"));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchClient(e);
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
				GetListClient.getListOfClient();
			}
		});
		toolbar.add(btnShowAll);
		
		toolbar.addSeparator();
		
		/***** COMBOBOX TRIER *****/
		String typeRightTri[] = {"", "Adresse", "Téléphone", "Email"};
		comboBoxRightTri = new JComboBox<String>(typeRightTri);
		comboBoxRightTri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchRightTri(e);
			}
		});
		toolbar.add(comboBoxRightTri);
				
		return toolbar;
	}
	
	private static void searchClient(ActionEvent ev) {
		listOfClient = new	DefaultTableModel();
		String requete = new String();
		String selectedTri = (String)comboBoxTri.getSelectedItem();
		
		Connection connection;
		ResultSet result = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/stock", "root", "root1234");

			statement = connection.createStatement();
			if(selectedTri == "Prénom") {
				requete = "SELECT * FROM Client WHERE Prenom_Client LIKE '"+ textSearch.getText() +"'";
			}
			else {
				requete = "SELECT * FROM Client WHERE Nom_Client LIKE '"+ textSearch.getText() +"'";
			}
			
			result = statement.executeQuery(requete);
			
			listOfClient.addColumn("ID");
			listOfClient.addColumn("Nom");
			listOfClient.addColumn("Prénom");
			listOfClient.addColumn("Adresse");
			listOfClient.addColumn("Téléphone");
			listOfClient.addColumn("Email");
			
			while(result.next()) {
				listOfClient.addRow(new Object[] {
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getString(5),
					result.getString(6)
				});
			}
			tabInfoClient.setModel(listOfClient);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	////RIGHT TRI////
	private static void searchRightTri(ActionEvent ev) {
		listOfClient = new	DefaultTableModel();
		String requete = new String();
		String selectedTri = (String)comboBoxRightTri.getSelectedItem();
		
		Connection connection;
		ResultSet result = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/stock", "root", "root1234");

			statement = connection.createStatement();
			if(selectedTri == "Adresse") {
				requete = "SELECT ID_Client, Nom_Client, Adresse_Client FROM Client";
				
				result = statement.executeQuery(requete);
				
				listOfClient.addColumn("ID");
				listOfClient.addColumn("Nom");
				listOfClient.addColumn("Adresse");
				
				while(result.next()) {
					listOfClient.addRow(new Object[] {
						result.getInt(1),
						result.getString(2),
						result.getString(3)
					});
				}
				tabInfoClient.setModel(listOfClient);
			}
			else if(selectedTri == "Téléphone") {
				requete = "SELECT ID_Client, Nom_Client, Telephone_Client FROM Client";
				
				result = statement.executeQuery(requete);
				
				listOfClient.addColumn("ID");
				listOfClient.addColumn("Nom");
				listOfClient.addColumn("Téléphone");
				
				while(result.next()) {
					listOfClient.addRow(new Object[] {
						result.getInt(1),
						result.getString(2),
						result.getString(3)
					});
				}
				tabInfoClient.setModel(listOfClient);
			}
			else if(selectedTri == "Email") {
				requete = "SELECT ID_Client, Nom_Client, Email_Client FROM Client";
				
				result = statement.executeQuery(requete);
				
				listOfClient.addColumn("ID");
				listOfClient.addColumn("Nom");
				listOfClient.addColumn("Email");
				
				while(result.next()) {
					listOfClient.addRow(new Object[] {
						result.getInt(1),
						result.getString(2),
						result.getString(3)
					});
				}
				tabInfoClient.setModel(listOfClient);
			}
			else {
				GetListClient.getListOfClient();				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
}
