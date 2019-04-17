package commande;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import details_commande.DetailComWindow;
import items_and_list.GetListProduitForCom;
import items_and_list.ItemsIdClient;
import items_and_list.ItemsIdProduit;
import main_package.Connector;
import main_package.MainWindow;
import method_create_toolbar.SearchBarCommand;
import method_create_toolbar.ToolBar;


@SuppressWarnings({"rawtypes", "unchecked"})
public class CommandWindow extends JFrame{
	private static final long serialVersionUID = 7590066300716725430L;
	protected static JTable tabProduit;
	protected static DefaultTableModel listOfProduit;
	protected static Statement statement;
	private static JTextField textNomProduit;
	private static JTextField textNomClient;
	private static JTextField textQuantiteCommande;
	private static JComboBox comboBoxIdClient;
	private static JComboBox comboBoxIdProduit;
	protected static ArrayList<String> listItemsIdProduit = new ArrayList<String>();
	protected static String[] cmbItemsIdProduit;
	protected static ArrayList<String> listItemsIdClient = new ArrayList<String>();
	protected static String[] cmbItemsIdClient;
	private static JButton btnSecureProduit;
	private static JButton btnSecureClient;
	private static JPanel panel_2;

	public CommandWindow() {
		super("Gestion de stock");
		JPanel commandPanel = (JPanel) this.getContentPane();

		//////////////////////////////////---- SAISIE ----///////////////////////////////////
		JPanel panelSaisie = new JPanel();
		panelSaisie.setBounds(6, 50, 416, 376);
		panelSaisie.setLayout(new BorderLayout(0, 0));
		panelSaisie.setPreferredSize(new Dimension(550, 0));
		
		/************ BOUTON ACHETER ******************/
		JButton btnAcheter = new JButton("Acheter");
		btnAcheter.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		btnAcheter.setBackground(Color.ORANGE);
		btnAcheter.setIcon(new ImageIcon("icons/buy.png"));
		btnAcheter.setBounds(115, 320, 190, 65);
		btnAcheter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAcheterAction(e);
			}
		});
		panelSaisie.add(btnAcheter);
		
		/*******************++++ PRODUIT ++++******************/
		JLabel lblNomDuProduit = new JLabel("Nom du produit");
		lblNomDuProduit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNomDuProduit.setBounds(16, 24, 125, 21);
		panelSaisie.add(lblNomDuProduit);
		
		btnSecureProduit = new JButton();
		btnSecureProduit.setIcon(new ImageIcon("icons/unlockedit.png"));
		btnSecureProduit.setBounds(153, 20, 23, 30);
		btnSecureProduit.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				textNomProduit.setText("");
				textNomProduit.setEditable(true);
				btnSecureProduit.setIcon(new ImageIcon("icons/unlockedit.png"));
			}
		});
		panelSaisie.add(btnSecureProduit);

		textNomProduit = new JTextField();
		textNomProduit.setBounds(175, 19, 145, 35);
		panelSaisie.add(textNomProduit);
		
		JLabel lblIdProduit = new JLabel("ou ID");
		lblIdProduit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblIdProduit.setBounds(325, 24, 45, 21);
		panelSaisie.add(lblIdProduit);
		
		/************ COMBOBOX ID DU PRODUIT ******************/
		comboBoxIdProduit = new JComboBox(ItemsIdProduit.createItemsIdProuit());
		comboBoxIdProduit.setBounds(375, 20, 70, 34);
		comboBoxIdProduit.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxIdProduitAction(e);
			}
		});
		panelSaisie.add(comboBoxIdProduit);
		
		/*******************++++ CLIENT ++++******************/
		JLabel lblNomClient = new JLabel("Nom du client");
		lblNomClient.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNomClient.setBounds(16, 84, 125, 21);
		panelSaisie.add(lblNomClient);
		
		btnSecureClient = new JButton();
		btnSecureClient.setIcon(new ImageIcon("icons/unlockedit.png"));
		btnSecureClient.setBounds(153, 80, 23, 30);
		btnSecureClient.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				textNomClient.setText("");
				textNomClient.setEditable(true);
				btnSecureClient.setIcon(new ImageIcon("icons/unlockedit.png"));
			}
		});
		panelSaisie.add(btnSecureClient);

		textNomClient = new JTextField();
		textNomClient.setBounds(175, 79, 145, 35);
		panelSaisie.add(textNomClient);
		
		JLabel lblIdClient = new JLabel("ou ID");
		lblIdClient.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblIdClient.setBounds(325, 84, 45, 21);
		panelSaisie.add(lblIdClient);
		
		/************ COMBOBOX ID DU CLIENT ******************/
		comboBoxIdClient = new JComboBox(ItemsIdClient.createItemsIdClient());
		comboBoxIdClient.setBounds(375, 80, 70, 34);
		comboBoxIdClient.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxIdClientAction(e);
			}
		});
		panelSaisie.add(comboBoxIdClient);
		
		/*******************++++ QUANTITE ++++******************/
		JLabel lblQuantiteCommande = new JLabel("Quantité commandé");
		lblQuantiteCommande.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblQuantiteCommande.setBounds(16, 144, 165, 21);
		panelSaisie.add(lblQuantiteCommande);

		textQuantiteCommande = new JTextField();
		textQuantiteCommande.setBounds(175, 139, 145, 35);
		panelSaisie.add(textQuantiteCommande);
		
		///////////////////////////-------- BACKGROUND ---------////////////////////////
		JLabel lblBackground = new JLabel();
		lblBackground.setBackground(Color.LIGHT_GRAY);
		lblBackground.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblBackground.setBounds(16, 219, 97, 26);
		panelSaisie.add(lblBackground);
		
		////////////////////////////---- AFFICHAGE DONNEES ----/////////////////////////////	
		/********* TOOLBAR ***********/
		commandPanel.add(ToolBar.createToolBar(), BorderLayout.NORTH);
				
		/************ TABLE PRODUIT ******************/
		tabProduit = new JTable();
		tabProduit.setBackground(new Color(230, 230, 250));
		tabProduit.setColumnSelectionAllowed(true);
		tabProduit.setShowVerticalLines(true);
		tabProduit.setShowHorizontalLines(true);
		tabProduit.setEnabled(false);
		tabProduit.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		tabProduit.setRowHeight(25);
		GetListProduitForCom.getListOfProduit();

		panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(SearchBarCommand.createToolBar(), BorderLayout.NORTH);
		panel_2.add(new JScrollPane(tabProduit));

		/********* SPLIT PANE *************/
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelSaisie, panel_2);
		splitPanel.setResizeWeight(0.0);
		commandPanel.add(splitPanel);		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1200, 600));
		this.setLocationRelativeTo(null);
	}
	
	//////////////// ACTION DU COMBOBOX comboBoxIdProduit --- BEGIN //////////////
	private void comboBoxIdProduitAction(ActionEvent e) {
		ResultSet nomProduitRes;
		String nomProd = new String();
		try {
			nomProduitRes = statement.executeQuery("SELECT Nom_Produit FROM Produit WHERE ID_Produit = '" + comboBoxIdProduit.getSelectedItem() + "'");
			while (nomProduitRes.next()) {
				nomProd = nomProduitRes.getString("Nom_Produit");
			}
		}
		catch (Exception except) {
			System.out.println(except);
		}
		textNomProduit.setEditable(false);
		textNomProduit.setText(nomProd);
		btnSecureProduit.setIcon(new ImageIcon("icons/lockedit.png"));
	}
	////////////////ACTION DU COMBOBOX comboBoxIdProduit --- END //////////////
	
	////////////////ACTION DU COMBOBOX comboBoxIdProduit --- BEGIN //////////////
	private void comboBoxIdClientAction(ActionEvent e) {
		ResultSet nomClientRes;
		String nomCli = new String();
		try {
			nomClientRes = statement.executeQuery("SELECT Nom_Client FROM Client WHERE ID_Client = '" + comboBoxIdClient.getSelectedItem() + "'");
			while (nomClientRes.next()) {
				nomCli = nomClientRes.getString("Nom_Client");
			}
		}
		catch (Exception except) {
			System.out.println(except);
		}
		textNomClient.setEditable(false);
		textNomClient.setText(nomCli);
		btnSecureClient.setIcon(new ImageIcon("icons/lockedit.png"));
	}
	////////////////ACTION DU COMBOBOX comboBoxIdProduit --- END //////////////
	
	////////////////ACTION DU COMBOBOX comboBoxIdProduit --- BEGIN //////////////
	private void btnAcheterAction(ActionEvent e) {
		Date daty = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("Y-MM-dd");
		ResultSet idProduitTest, idProduitRes, idClientTest, idClientRes, ancienneQuantiteRes;
		String intIdProduit = "";
		String intIdClient = "";
		int nouvelleQuantite, ancienneQuantite = 0;
		
		try {
			///---- GET ID PRODUIT ----///
			if ( textNomProduit.isEditable() == true) {
				String sqlIdProduit = "SELECT ID_Produit FROM Produit WHERE Nom_Produit LIKE ?";
				PreparedStatement statIdProduit = Connector.connect().prepareStatement(sqlIdProduit);
				statIdProduit.setString(1, textNomProduit.getText());
				idProduitTest = statIdProduit.executeQuery();
				if (idProduitTest.next() == true) {
					idProduitRes = statIdProduit.executeQuery();
				}
				else {
					idProduitRes = statIdProduit.executeQuery();
					JOptionPane.showMessageDialog(this, "Désolé! Ce produit n'existe pas!");
				}
				while (idProduitRes.next()) {
					intIdProduit = idProduitRes.getString("ID_Produit");
				}
			}
			else {
				intIdProduit = (String) comboBoxIdProduit.getSelectedItem();
			}
			
			///---- GET ID CLIENT ----///
			if ( textNomClient.isEditable() == true) {
				String sqlIdClient = "SELECT ID_Client FROM Client WHERE Nom_Client LIKE ?";
				PreparedStatement statIdClient = Connector.connect().prepareStatement(sqlIdClient);
				statIdClient.setString(1, textNomClient.getText());
				idClientTest = statIdClient.executeQuery();
				if (idClientTest.next() == true) {
					idClientRes = statIdClient.executeQuery();
				}
				else {
					idClientRes = statIdClient.executeQuery();
					JOptionPane.showMessageDialog(this, "Désolé! Le nom du client n'existe pas!");
				}
				while (idClientRes.next()) {
					intIdClient = idClientRes.getString("ID_Client");
				}

			}
			else {
				intIdClient = (String) comboBoxIdClient.getSelectedItem();
			}
			
			///---- INSERT INTO COMMANDE ----///
			if (intIdProduit.equals("")) {
			}
			else {
				String sqlInsertCommande ="INSERT INTO Commande(Date_Commande, Quantite_Commande, ID_Produit, ID_Client) VALUES(?, ?, ?, ?)";
				PreparedStatement statInsertCommande = Connector.connect().prepareStatement(sqlInsertCommande);
				statInsertCommande.setString(1, dateForm.format(daty));
				statInsertCommande.setString(2, textQuantiteCommande.getText());
				statInsertCommande.setString(3, intIdProduit);
				statInsertCommande.setString(4, intIdClient);
				statInsertCommande.execute();
				
				///---- CALCUL ET UPDATE ----///
				ancienneQuantiteRes = statement.executeQuery("SELECT Quantite_Produit FROM Produit WHERE ID_Produit = "+intIdProduit);
				while (ancienneQuantiteRes.next()) {
					ancienneQuantite = ancienneQuantiteRes.getInt("Quantite_Produit");
				}
				nouvelleQuantite = ancienneQuantite - Integer.parseInt(textQuantiteCommande.getText());
				
				statement.execute("UPDATE Produit SET Quantite_Produit = "+nouvelleQuantite+" WHERE ID_Produit = "+intIdProduit);
				//////// ACTUALISATION DE LA TABLE --- BEGIN //////////////
				actualisationTableProduit();
				DetailComWindow.actualiserTable();
				MainWindow.actualisationTableProduitMain();
				//////// ACTUALISATION DE LA TABLE --- END //////////////
				
				JOptionPane.showMessageDialog(this, "Commande bien enregistré!");
			}
		}
		catch (Exception except) {
			System.out.println(except);
		}
	}
	public static void actualisationIdClient() {
		listItemsIdClient.clear();
		cmbItemsIdClient = new String[listItemsIdClient.size()];
		listItemsIdClient.toArray(cmbItemsIdClient);
		comboBoxIdClient.setModel(new DefaultComboBoxModel (ItemsIdClient.createItemsIdClient()));
	}
	
	public static void actualisationIdProduit() {
		listItemsIdProduit.clear();
		cmbItemsIdProduit = new String[listItemsIdProduit.size()];
		listItemsIdProduit.toArray(cmbItemsIdProduit);
		comboBoxIdProduit.setModel(new DefaultComboBoxModel (ItemsIdProduit.createItemsIdProuit()));
	}
	
	public static void actualisationTableProduit() {
		DefaultTableModel model = (DefaultTableModel)tabProduit.getModel();
		model.setRowCount(0);
		GetListProduitForCom.getListOfProduit();
	}
}
