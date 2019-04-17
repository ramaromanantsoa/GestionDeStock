package main_package;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import commande.CommandWindow;
import items_and_list.GetListProduit;
import items_and_list.ItemsCategorie;
import items_and_list.ItemsUnite;
import method_create_toolbar.DeleteUpdateProduit;
import method_create_toolbar.SearchBarProduit;
import method_create_toolbar.ToolBar;

public class MainWindow extends JFrame {
	/* Mes codes --- Début */
	private static final long serialVersionUID = 1L;
	private JTextField textNomProduit;
	private JTextField textCategorieProduit;
	private JTextField textQuantiteProduit;
	private JTextField textPrixProduit;
	protected static ArrayList<String> listItemsUnite = new ArrayList<String>();
	protected static String[] cmbItemsUnite;
	protected static ArrayList<String> listItemsCategorie = new ArrayList<String>();
	protected static String[] cmbItemsCategorie;
	private static JComboBox<String> comboBoxUnite;
	private static JComboBox<String> comboBoxCategorie;
	private JButton btnSecureCategorie;
	private JButton btnSecureUnite;
	protected static Statement statement;
	private JTextField textUnite;
	protected static JTable tabProduit;
	protected static DefaultTableModel listOfProduit;
	private static JPanel panel_2;
	private static JPanel panel;

	// Configuration de la fentre avec un constructeur
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainWindow() {
		super("Gestion de stock");
		JPanel mainPanel = (JPanel) this.getContentPane();
		/* Mes codes --- Fin */
		
		panel = new JPanel();
		panel.setBounds(6, 50, 416, 376);
		panel.setLayout(new BorderLayout(0, 0));
		//panel.setPreferredSize(new Dimension(550, 0));
		
		
		/************ BOUTON ENREGISTREER ******************/
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		btnEnregistrer.setBackground(new Color(0, 191, 255));
		btnEnregistrer.setBounds(115, 320, 190, 45);
		btnEnregistrer.setIcon(new ImageIcon("icons/save.png"));
		/////// ACTION DU BOUTON btnEnregistrer /////////////
		btnEnregistrer.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEnregistrerAction(e);
			}
		});

		panel.add(btnEnregistrer);

		/************ NOM DU PRODUIT ******************/
		JLabel lblNomDuProduit = new JLabel("Nom du produit");
		lblNomDuProduit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNomDuProduit.setBounds(16, 19, 125, 21);
		panel.add(lblNomDuProduit);

		textNomProduit = new JTextField();
		textNomProduit.setBounds(145, 19, 145, 35);
		panel.add(textNomProduit);
		textNomProduit.setColumns(10);
		
		/************ CATEGORIE DU PRODUIT ******************/
		JLabel lblCategorie = new JLabel("Catégorie");
		lblCategorie.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblCategorie.setBounds(16, 69, 97, 21);
		panel.add(lblCategorie);

		textCategorieProduit = new JTextField();
		textCategorieProduit.setBounds(145, 69, 145, 35);
		panel.add(textCategorieProduit);
		textCategorieProduit.setColumns(10);

		comboBoxCategorie = new JComboBox(ItemsCategorie.createItemsCategorie());
		comboBoxCategorie.setBounds(295, 67, 124, 34);
		comboBoxCategorie.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				textCategorieProduit.setEditable(false);
				textCategorieProduit.setText((String)comboBoxCategorie.getSelectedItem());
				btnSecureCategorie.setIcon(new ImageIcon("icons/lockedit.png"));
			}
		});
		
		panel.add(comboBoxCategorie);
		
		btnSecureCategorie = new JButton();
		btnSecureCategorie.setIcon(new ImageIcon("icons/unlockedit.png"));
		btnSecureCategorie.setBounds(118, 70, 23, 30);
		btnSecureCategorie.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				textCategorieProduit.setText("");
				comboBoxCategorie.setSelectedItem("");
				textCategorieProduit.setEditable(true);
				btnSecureCategorie.setIcon(new ImageIcon("icons/unlockedit.png"));
			}
		});
		panel.add(btnSecureCategorie);
		
		/************ UNITE DU PRODUIT ******************/
		JLabel lblUnit = new JLabel("Unité");
		lblUnit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblUnit.setBounds(16, 119, 59, 26);
		panel.add(lblUnit);
		
		textUnite = new JTextField();
		textUnite.setColumns(10);
		textUnite.setBounds(145, 119, 145, 35);
		panel.add(textUnite);

		comboBoxUnite = new JComboBox (ItemsUnite.createItemsUnites());
		//comboBoxUnite.setModel(new DefaultComboBoxModel<String> (ItemsUnite.createItemsUnites()));
		comboBoxUnite.setBounds(295, 117, 124, 34);
		comboBoxUnite.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				textUnite.setEditable(false);
				textUnite.setText((String)comboBoxUnite.getSelectedItem());
				btnSecureUnite.setIcon(new ImageIcon("icons/lockedit.png"));
			}
		});
		panel.add(comboBoxUnite);
		
		btnSecureUnite = new JButton();
		btnSecureUnite.setIcon(new ImageIcon("icons/unlockedit.png"));
		btnSecureUnite.setBounds(118, 119, 23, 30);
		btnSecureUnite.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				textUnite.setText("");
				comboBoxUnite.setSelectedItem("");
				textUnite.setEditable(true);
				btnSecureUnite.setIcon(new ImageIcon("icons/unlockedit.png"));
			}
		});
		panel.add(btnSecureUnite);
		
		/************ QUANTITE DU PRODUIT ******************/
		JLabel lblQuantit = new JLabel("Quantité");
		lblQuantit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblQuantit.setBounds(16, 169, 97, 26);
		panel.add(lblQuantit);

		textQuantiteProduit = new JTextField();
		textQuantiteProduit.setColumns(10);
		textQuantiteProduit.setBounds(145, 169, 145, 35);
		panel.add(textQuantiteProduit);

		/************ PRIX DU PRODUIT ******************/
		JLabel lblPrixD = new JLabel("Prix unitaire");
		lblPrixD.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblPrixD.setBounds(16, 219, 97, 26);
		panel.add(lblPrixD);

		textPrixProduit = new JTextField();
		textPrixProduit.setColumns(10);
		textPrixProduit.setBounds(145, 219, 145, 35);
		panel.add(textPrixProduit);
		
		/********** LABEL BACKGROUND *******/
		JLabel lblBackground = new JLabel();
		lblBackground.setBackground(Color.LIGHT_GRAY);
		lblBackground.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblBackground.setBounds(16, 319, 97, 26);
		panel.add(lblBackground);
		
		/************ PANEL_2 ******************/
		panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));
		
		/************ TABLE PRODUIT ******************/
		tabProduit = new JTable();
		tabProduit.setBackground(new Color(230, 230, 250));
		tabProduit.setColumnSelectionAllowed(true);
		tabProduit.setShowVerticalLines(true);
		tabProduit.setShowHorizontalLines(true);
		tabProduit.setEnabled(false);
		tabProduit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		tabProduit.setRowHeight(25);
		GetListProduit.getListOfProduit();
		panel_2.add(new JScrollPane(tabProduit));
		panel_2.add(SearchBarProduit.createToolBar(), BorderLayout.NORTH);
		panel_2.add(DeleteUpdateProduit.createToolBar(), BorderLayout.SOUTH);
		
		/********* SPLIT PANE *************/
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, panel_2);
		splitPanel.setResizeWeight(0.65);
		mainPanel.add(splitPanel);
		
		/********* TOOLBAR ***********/
		mainPanel.add(ToolBar.createToolBar(), BorderLayout.NORTH);
	
		/* Mes "main" codes --- Début */
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1200, 500));
		this.setLocationRelativeTo(null);
		/* Mes "main" codes --- Fin */
	}


	/////// ACTION DU BOUTON btnEnregistrer ^^^^ BEGIN /////////////
	private void btnEnregistrerAction(ActionEvent e) {
		ResultSet idUnite, idCategorie, idUniteTest, idCategorieTest;
		Integer intIdUnite = 1;
		Integer  intIdCategorie = 1;
		String testTextUnite = textUnite.getText();
		String testTextCategorie = textCategorieProduit.getText();
		
		try { 
			
			/////// Combo box UNITE	
			if ( testTextUnite.equals("")) {
				intIdUnite = 1;				
			}
			else {
				if ( textUnite.isEditable() ==  true) {
					String sqlIdUnite = "SELECT ID_Unite FROM Unite WHERE Type_Unite = ?";
					PreparedStatement statIdUnite = Connector.connect().prepareStatement(sqlIdUnite);
					statIdUnite.setString(1, textUnite.getText());
					idUniteTest = statIdUnite.executeQuery();
					if ( idUniteTest.next() == true ) {
						idUnite = statIdUnite.executeQuery();
					}
					else{
						String sqlInsertUnite = "INSERT INTO Unite(Type_Unite) VALUES(?)";
						PreparedStatement statInsertUnite = Connector.connect().prepareStatement(sqlInsertUnite);
						statInsertUnite.setString(1, textUnite.getText());
						statInsertUnite.execute();
						idUnite = statIdUnite.executeQuery();
					}
				}
				else {
					idUnite = statement.executeQuery("SELECT ID_Unite FROM Unite WHERE Type_Unite = '" + comboBoxUnite.getSelectedItem() + "'");
				}
				while (idUnite.next()) {
					intIdUnite = idUnite.getInt("ID_Unite");
				}
			}
			
			////// Combo box CATEGORIE
			if ( testTextCategorie.equals("")) {
				intIdCategorie = 1;				
			}
			else {
				if ( textCategorieProduit.isEditable() == true) {
					String sqlIdCategorie = "SELECT ID_Categorie FROM Categorie WHERE Nom_Categorie = ?";
					PreparedStatement statIdCategorie = Connector.connect().prepareStatement(sqlIdCategorie);
					statIdCategorie.setString(1, textCategorieProduit.getText());
					idCategorieTest = statIdCategorie.executeQuery();
					if (idCategorieTest.next() == true) {
						idCategorie = statIdCategorie.executeQuery();
					}
					else {
						String sqlInsertCategorie = "INSERT INTO Categorie(Nom_Categorie) VALUES(?)";
						PreparedStatement statInsertCategorie = Connector.connect().prepareStatement(sqlInsertCategorie);
						statInsertCategorie.setString(1, textCategorieProduit.getText());
						statInsertCategorie.execute();
						idCategorie = statIdCategorie.executeQuery();
					}
				}
				else {
					idCategorie = statement.executeQuery("SELECT ID_Categorie FROM Categorie WHERE Nom_Categorie = '" + comboBoxCategorie.getSelectedItem() + "'");
				}
				while (idCategorie.next()) {
					intIdCategorie = idCategorie.getInt("ID_Categorie");
				}
			}
			
			String sqlInsertProduit = "INSERT INTO Produit(Nom_Produit, ID_Unite, ID_Categorie, Quantite_Produit, Prix_Produit) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement statInsertProduit = Connector.connect().prepareStatement(sqlInsertProduit);
			statInsertProduit.setString(1, textNomProduit.getText());
			statInsertProduit.setInt(2, intIdUnite);
			statInsertProduit.setInt(3, intIdCategorie);
			statInsertProduit.setString(4, textQuantiteProduit.getText());
			statInsertProduit.setString(5, textPrixProduit.getText());
			statInsertProduit.execute();

			 //////// REINITIALISATION DES CHAMPS --- BEGIN ////////
			 textNomProduit.setText("");
			 textCategorieProduit.setText("");
			 textQuantiteProduit.setText("");
			 textPrixProduit.setText("");
			 textUnite.setText("");
			 comboBoxCategorie.setSelectedItem("");
			 comboBoxUnite.setSelectedItem("");
			 
			 textCategorieProduit.setEditable(true);
			 textUnite.setEditable(true);
			 btnSecureUnite.setIcon(new ImageIcon("icons/unlockedit.png"));
			 btnSecureCategorie.setIcon(new ImageIcon("icons/unlockedit.png"));
			 //////// REINITIALISATION DES CHAMPS --- END ////////
			 
			 //////// ACTUALISATION DE LA TABLE ET DU COMBOBOX --- BEGIN //////////////
			 DefaultTableModel model = (DefaultTableModel)tabProduit.getModel();
			 model.setRowCount(0);
			 GetListProduit.getListOfProduit();
			 	///// ComboBoxUnite /////
			 listItemsUnite.clear();
			 cmbItemsUnite = new String[listItemsUnite.size()];
			 listItemsUnite.toArray(cmbItemsUnite);
			 comboBoxUnite.setModel(new DefaultComboBoxModel<String>(ItemsUnite.createItemsUnites()));
			 	///// ComboBoxCategorie /////
			 listItemsCategorie.clear();
			 cmbItemsCategorie = new String[listItemsCategorie.size()];
			 listItemsCategorie.toArray(cmbItemsCategorie);
			 comboBoxCategorie.setModel(new DefaultComboBoxModel<String>(ItemsCategorie.createItemsCategorie()));
			 
			 CommandWindow.actualisationIdProduit();
			 //////// ACTUALISATION DE LA TABLE ET DU COMBOBOX --- END //////////////
			 
			 JOptionPane.showMessageDialog(this, "Vos données sont enregistrées avec succés!");
			
		} catch (Exception exept) {
			JOptionPane.showMessageDialog(this, "Veuillez completer tous les champs!");
		}
	}
	/////// ACTION DU BOUTON btnEnregistrer ^^^^ END /////////////
	public static void actualisationTableProduitMain() {
		DefaultTableModel model = (DefaultTableModel)tabProduit.getModel();
		model.setRowCount(0);
		GetListProduit.getListOfProduit();
	}
}
