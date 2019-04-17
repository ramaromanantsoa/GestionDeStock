package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import items_and_list.GetListClient;
import main_package.Connector;
import method_create_toolbar.DeleteUpdateClient;
import method_create_toolbar.SearchBarClient;
import method_create_toolbar.ToolBar;

public class ClientWindow extends JFrame{
	private static final long serialVersionUID = 7087876176050679731L;
	protected static JTable tabInfoClient;
	protected static DefaultTableModel listOfClient;
	private static PreparedStatement statement;
	private static JTextField textNomClient;
	private static JTextField textPrenomClient;
	private static JTextField textAdresseClient;
	private static JTextField textTelephoneClient;
	private static JTextField textEmailClient;
	private static JPanel panel_2;

	public ClientWindow() {
		super("Gestion de stock");
		JPanel clientPanel = (JPanel) this.getContentPane();

		//////////////////////////////////---- SAISIE ----///////////////////////////////////
		JPanel panelSaisie = new JPanel();
		panelSaisie.setBounds(6, 50, 416, 376);
		panelSaisie.setLayout(new BorderLayout(0, 0));
		//panelSaisie.setPreferredSize(new Dimension(550, 0));
		
		/************ BOUTON ENREGISTREER ******************/
		JButton btnValider = new JButton("Valider");
		btnValider.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		btnValider.setBackground(Color.GREEN);
		btnValider.setBounds(107, 336, 140, 45);
		btnValider.setIcon(new ImageIcon("icons/newclient.png"));
		/////// ACTION DU BOUTON btnValider /////////////
		btnValider.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				btnValiderAction(e);
			}
		});
		panelSaisie.add(btnValider);
		
		/************ NOM DU CLIENT ******************/
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNom.setBounds(16, 19, 125, 21);
		panelSaisie.add(lblNom);

		textNomClient = new JTextField();
		textNomClient.setBounds(145, 19, 165, 35);
		panelSaisie.add(textNomClient);
		textNomClient.setColumns(10);
		
		/************ PRENOM DU CLIENT ******************/
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblPrenom.setBounds(16, 69, 97, 21);
		panelSaisie.add(lblPrenom);

		textPrenomClient = new JTextField();
		textPrenomClient.setBounds(145, 69, 165, 35);
		panelSaisie.add(textPrenomClient);
		textPrenomClient.setColumns(10);
		
		/************ ADRESSE DU CLIENT ******************/
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblAdresse.setBounds(16, 119, 69, 26);
		panelSaisie.add(lblAdresse);
		
		textAdresseClient = new JTextField();
		textAdresseClient.setColumns(10);
		textAdresseClient.setBounds(145, 119, 165, 35);
		panelSaisie.add(textAdresseClient);
		
		/************ TELEPHONE DU CLIENT ******************/
		JLabel lblTelephone = new JLabel("Téléphone");
		lblTelephone.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblTelephone.setBounds(16, 169, 97, 26);
		panelSaisie.add(lblTelephone);

		textTelephoneClient = new JTextField();
		textTelephoneClient.setColumns(10);
		textTelephoneClient.setBounds(145, 169, 165, 35);
		panelSaisie.add(textTelephoneClient);

		/************ EMAIL DU CLIENT ******************/
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblEmail.setBounds(16, 219, 97, 26);
		panelSaisie.add(lblEmail);

		textEmailClient = new JTextField();
		textEmailClient.setColumns(10);
		textEmailClient.setBounds(145, 219, 165, 35);
		panelSaisie.add(textEmailClient);
		
		JLabel lblBackground = new JLabel();
		lblBackground.setBackground(Color.LIGHT_GRAY);
		lblBackground.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblBackground.setBounds(16, 219, 97, 26);
		panelSaisie.add(lblBackground);
		
		////////////////////////////---- AFFICHAGE DONNEES ----/////////////////////////////		
		/********* TOOLBAR ***********/
		clientPanel.add(ToolBar.createToolBar(), BorderLayout.NORTH);
				
		/************ TABLE CLIENT ******************/
		tabInfoClient = new JTable();
		tabInfoClient.setBackground(new Color(230, 230, 250));
		tabInfoClient.setColumnSelectionAllowed(true);
		tabInfoClient.setShowVerticalLines(true);
		tabInfoClient.setShowHorizontalLines(true);
		tabInfoClient.setEnabled(false);
		tabInfoClient.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		tabInfoClient.setRowHeight(25);
		GetListClient.getListOfClient();

		panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(SearchBarClient.createToolBar(), BorderLayout.NORTH);
		panel_2.add(new JScrollPane(tabInfoClient));
		panel_2.add(DeleteUpdateClient.createToolBar(), BorderLayout.SOUTH);
		//panel_2.setPreferredSize(new Dimension(530, 0));

		/********* SPLIT PANE *************/
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelSaisie, panel_2);
		splitPanel.setResizeWeight(0.55);
		clientPanel.add(splitPanel);		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1100, 500));
		this.setLocationRelativeTo(null);
	}


	/////// ACTION DU BOUTON btnValider ^^^^ BEGIN /////////////
	private void btnValiderAction(ActionEvent e) {
		String testNom = textNomClient.getText();
		String testPrenom = textNomClient.getText();
		
		try { 
			
			if (testNom.equals("")) {
				JOptionPane.showMessageDialog(this, "Veuillez indiquer le nom et prénom!");
			}
			else if (testPrenom.equals("")) {
				JOptionPane.showMessageDialog(this, "Veuillez indiquer le nom et prénom!");
			}
			else {
				String sqlInsert = "INSERT INTO Client(Nom_Client, Prenom_Client, Adresse_Client, Telephone_Client, Email_Client) VALUES(?, ?, ?, ?, ?)";
				statement = Connector.connect().prepareStatement(sqlInsert);
				statement.setString(1, textNomClient.getText());
				statement.setString(2, textPrenomClient.getText());
				statement.setString(3, textAdresseClient.getText());
				statement.setString(4, textTelephoneClient.getText());
				statement.setString(5, textEmailClient.getText());
				
				statement.execute();
				
				//////// REINITIALISATION DES CHAMPS --- BEGIN ////////
				 textNomClient.setText("");
				 textPrenomClient.setText("");
				 textAdresseClient.setText("");
				 textTelephoneClient.setText("");
				 textEmailClient.setText("");
				 //////// REINITIALISATION DES CHAMPS --- END ////////
				 
				 //////// ACTUALISATION DE LA TABLE --- BEGIN //////////////
				 DefaultTableModel model = (DefaultTableModel)tabInfoClient.getModel();
				 model.setRowCount(0);
				 GetListClient.getListOfClient();
				 
				 CommandWindow.actualisationIdClient();
				 //////// ACTUALISATION DE LA TABLE --- END //////////////
				 JOptionPane.showMessageDialog(this, "Vos données sont enregistrées avec succés!");
			}
			
		} catch (Exception except) {
			System.out.println(except);
		}
	}
	/////// ACTION DU BOUTON btnValider ^^^^ END /////////////

}