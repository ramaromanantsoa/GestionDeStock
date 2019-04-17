package method_create_toolbar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import client.ClientWindow;
import items_and_list.GetListClient;
import main_package.Connector;

public class DeleteUpdateClient extends ClientWindow {
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
		
		String colonneItems[] = {"", "Nom", "Prénom", "Adresse", "Téléphone", "Email"};
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
			
			String sqlDelClient = "DELETE FROM Client WHERE ID_Client = ?";
			PreparedStatement statDelClient = Connector.connect().prepareStatement(sqlDelClient);
			statDelClient.setString(2, textID.getText());
			statDelClient.execute();
			
			//////// ACTUALISATION DE LA TABLE --- BEGIN //////////////
			DefaultTableModel model = (DefaultTableModel)tabInfoClient.getModel();
			model.setRowCount(0);
			GetListClient.getListOfClient();
			//////// ACTUALISATION DE LA TABLE --- END //////////////
		}
		catch (Exception except) {
			System.out.println(except);
			//JOptionPane.showMessageDialog(ClientWindow.tabInfoClient, "Veuillez indiquer l'ID de l'enregistrement à supprimer");
		}
	}
	//////ACTION DU BOUTON btnDeleteAction ^^^^ END /////////////
		
	//////ACTION DU BOUTON btnUpdateAction ^^^^ BEGIN /////////////
	private static void btnUpdateAction(ActionEvent e) {
		try {
			
			String triColonne = (String)comboBoxColonne.getSelectedItem();

			if (triColonne == "Nom") {
				String sqlUpdateNom = "UPDATE Client SET Nom_Client = ? WHERE ID_Client = ?";
				PreparedStatement statUpdateNom = Connector.connect().prepareStatement(sqlUpdateNom);
				statUpdateNom.setString(1, textValue.getText());
				statUpdateNom.setString(2, textID.getText());
				statUpdateNom.executeUpdate();
			}
			else if (triColonne == "Prénom") {
				String sqlUpdatePrenom = "UPDATE Client SET Prenom_Client = ? WHERE ID_Client = ?";
				PreparedStatement statUpdatePreom = Connector.connect().prepareStatement(sqlUpdatePrenom);
				statUpdatePreom.setString(1, textValue.getText());
				statUpdatePreom.setString(2, textID.getText());
				statUpdatePreom.executeUpdate();
			}
			else if (triColonne == "Adresse") {
				String sqlUpdateAdresse = "UPDATE Client SET Adresse_Client = ? WHERE ID_Client = ?";
				PreparedStatement statUpdateAdresse = Connector.connect().prepareStatement(sqlUpdateAdresse);
				statUpdateAdresse.setString(1, textValue.getText());
				statUpdateAdresse.setString(2, textID.getText());
				statUpdateAdresse.executeUpdate();
			}
			else if (triColonne == "Téléphone") {
				String sqlUpdateTelephone = "UPDATE Client SET Telephone_Client = ? WHERE ID_Client = ?";
				PreparedStatement statUpdateTelephone = Connector.connect().prepareStatement(sqlUpdateTelephone);
				statUpdateTelephone.setString(1, textValue.getText());
				statUpdateTelephone.setString(2, textID.getText());
				statUpdateTelephone.executeUpdate();
			}
			else if (triColonne == "Email") {
				String sqlUpdateEmail = "UPDATE Client SET Email_Client = ? WHERE ID_Client = ?";
				PreparedStatement statUpdateEmail = Connector.connect().prepareStatement(sqlUpdateEmail);
				statUpdateEmail.setString(1, textValue.getText());
				statUpdateEmail.setString(2, textID.getText());
				statUpdateEmail.executeUpdate();
			}
			else {
				JOptionPane.showMessageDialog(ClientWindow.tabInfoClient, "Nom de colonne non indiqué!");
			}
			
			//////// ACTUALISATION DE LA TABLE --- BEGIN //////////////
			DefaultTableModel model = (DefaultTableModel)tabInfoClient.getModel();
			model.setRowCount(0);
			GetListClient.getListOfClient();
			//////// ACTUALISATION DE LA TABLE --- END //////////////
		}
		catch (Exception except) {
			//JOptionPane.showMessageDialog(ClientWindow.tabInfoClient, "Veuillez indiquer l'ID de l'enregistrement à modifier!");
			System.out.println(except);
		}
	}
	//////ACTION DU BOUTON btnUpdateAction ^^^^ END /////////////
}
