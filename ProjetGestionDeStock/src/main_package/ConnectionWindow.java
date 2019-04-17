package main_package;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ConnectionWindow extends JFrame {
	/* Mes codes --- Début */
	private static final long serialVersionUID = 1L;
	private JTextField textHost;
	private JTextField textDBName;
	private JTextField textDBUser;
	private JPasswordField textDBPass;

	protected static MainWindow mainwindow;

	public static String host;
	public static String dbname;
	public static String addition;
	public static String dbuser;
	public static String dbpasswd;
	public static Connection connection;

	// Configuration de la fentre avec un constructeur
	public ConnectionWindow() {
		super("Gestion de stock");
		setResizable(false);
		JPanel mainPanel = (JPanel) this.getContentPane();
		/* Mes codes --- Fin */
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 50, 416, 376);
		panel.setLayout(new BorderLayout(0, 0));
		//panel.setPreferredSize(new Dimension(550, 0));
		
		
		/************ BOUTON ENREGISTREER ******************/
		JButton btnEnregistrer = new JButton("Connect");
		btnEnregistrer.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		btnEnregistrer.setBackground(UIManager.getColor("Tree.selectionBackground"));
		btnEnregistrer.setBounds(115, 215, 170, 35);
		/////// ACTION DU BOUTON btnEnregistrer /////////////
		btnEnregistrer.addActionListener(new ActionListener() {
			// Creation d'une class anonyme pour l'ecoute de l'evenement
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEnregistrerAction(e);
			}
		});

		panel.add(btnEnregistrer);

		/************ HOST ******************/
		JLabel lblHost = new JLabel("HOST");
		lblHost.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblHost.setBounds(50, 25, 55, 21);
		panel.add(lblHost);

		textHost = new JTextField();
		textHost.setBounds(145, 19, 145, 35);
		panel.add(textHost);
		textHost.setColumns(10);
		
		/************ DB NAME ******************/
		JLabel lblDBName = new JLabel("DB NAME");
		lblDBName.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblDBName.setBounds(50, 75, 97, 21);
		panel.add(lblDBName);

		textDBName = new JTextField();
		textDBName.setBounds(145, 69, 145, 35);
		panel.add(textDBName);
		textDBName.setColumns(10);
		
		/************ DB USER ******************/
		JLabel lblDBUser = new JLabel("DB USER");
		lblDBUser.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblDBUser.setBounds(50, 123, 97, 26);
		panel.add(lblDBUser);

		textDBUser = new JTextField();
		textDBUser.setColumns(10);
		textDBUser.setBounds(145, 119, 145, 35);
		panel.add(textDBUser);

		/************ PASSWORD ******************/
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblPassword.setBounds(50, 172, 97, 26);
		panel.add(lblPassword);

		textDBPass = new JPasswordField();
		textDBPass.setColumns(10);
		textDBPass.setBounds(145, 169, 145, 35);
		panel.add(textDBPass);
		
		/********** LABEL BACKGROUND *******/
		JLabel lblBackground = new JLabel();
		lblBackground.setBackground(Color.LIGHT_GRAY);
		lblBackground.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblBackground.setBounds(16, 319, 97, 26);
		panel.add(lblBackground);
		
		mainPanel.add(panel);
	
		/* Mes "main" codes --- Début */
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		/* Mes "main" codes --- Fin */
	}


	/////// ACTION DU BOUTON btnEnregistrer ^^^^ BEGIN /////////////
	@SuppressWarnings("deprecation")
	private void btnEnregistrerAction(ActionEvent e) {
		host = textHost.getText();
		dbname = textDBName.getText();
		addition = host+"/"+dbname;
		dbuser = textDBUser.getText();
		dbpasswd = textDBPass.getText();
		
		try { 
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://"+addition, dbuser, dbpasswd);
			MainClass.rendreVisibleProduit();
			this.setVisible(false);
			
		} catch (Exception exept) {
			JOptionPane.showMessageDialog(this, "Impossible de se connecter!");
		}
	}
	
	public static Connection connect() {
		return connection;
	}
	/////// ACTION DU BOUTON btnEnregistrer ^^^^ END /////////////
}
