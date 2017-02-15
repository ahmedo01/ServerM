import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComboBox;

public class ServerWindow extends JFrame {

	private JPanel contentPane;
	private DefaultListModel listModel;
	private DefaultListModel ConsoleBoxM;
	private Server server;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow frame = new ServerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	
	public ServerWindow() {
		setTitle("ServerM Alpha 0.0.1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblSunucuDurumu = new JLabel("Sunucu Bilgileri");
		lblSunucuDurumu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSunucuDurumu.setBounds(12, 14, 128, 19);
		contentPane.add(lblSunucuDurumu);
		
		JLabel lblSunucuDurumu_1 = new JLabel("Sunucu Durumu:");
		lblSunucuDurumu_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSunucuDurumu_1.setBounds(12, 43, 128, 16);
		contentPane.add(lblSunucuDurumu_1);
		
		JLabel lblBalKullanclar = new JLabel("Ba\u011Fl\u0131 Kullan\u0131c\u0131lar:");
		lblBalKullanclar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBalKullanclar.setBounds(12, 72, 128, 16);
		contentPane.add(lblBalKullanclar);
		
		JLabel lblMaksimumKullanc = new JLabel("Kullan\u0131c\u0131 Limiti:");
		lblMaksimumKullanc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaksimumKullanc.setBounds(12, 101, 128, 16);
		contentPane.add(lblMaksimumKullanc);
		
		JLabel label = new JLabel("Maksimum Kullan\u0131c\u0131:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(12, 130, 128, 16);
		contentPane.add(label);
		
		JLabel lblSunucuVersiyonu = new JLabel("Sunucu Versiyonu:");
		lblSunucuVersiyonu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSunucuVersiyonu.setBounds(12, 159, 128, 16);
		contentPane.add(lblSunucuVersiyonu);
		
		JLabel lblKullanclar = new JLabel("Kullan\u0131c\u0131lar:");
		lblKullanclar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKullanclar.setBounds(12, 188, 128, 16);
		contentPane.add(lblKullanclar);
		
		JLabel StatusText = new JLabel("ONLINE");
		StatusText.setFont(new Font("Tahoma", Font.BOLD, 13));
		StatusText.setBounds(146, 43, 128, 16);
		contentPane.add(StatusText);
		
		JLabel UserCount = new JLabel("ONLINE");
		UserCount.setFont(new Font("Tahoma", Font.BOLD, 13));
		UserCount.setBounds(146, 72, 128, 16);
		contentPane.add(UserCount);
		
		JLabel UserLimit = new JLabel("ONLINE");
		UserLimit.setFont(new Font("Tahoma", Font.BOLD, 13));
		UserLimit.setBounds(146, 101, 128, 16);
		contentPane.add(UserLimit);
		
		JLabel label_3 = new JLabel("ONLINE");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(146, 130, 128, 16);
		contentPane.add(label_3);
		
		JLabel VersionText = new JLabel("ONLINE");
		VersionText.setFont(new Font("Tahoma", Font.BOLD, 13));
		VersionText.setBounds(146, 159, 128, 16);
		contentPane.add(VersionText);
		
		JLabel lblSunucuKonso = new JLabel("Sunucu Konsolu");
		lblSunucuKonso.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSunucuKonso.setBounds(263, 16, 128, 19);
		contentPane.add(lblSunucuKonso);
		
		
		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setBounds(12, 215, 166, 314);
		contentPane.add(list);
		
		ConsoleBoxM = new DefaultListModel();
		JList ConsoleBox = new JList(ConsoleBoxM);
		ConsoleBox.setBounds(286, 45, 414, 484);
		contentPane.add(ConsoleBox);
		server = new Server(7000,this);
		Thread sthread = new Thread(server);
		sthread.start();
	}
	
	public void addLogToConsoleBox(String log) {
		Date d1 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY-HH:mm");
		String formattedDate = df.format(d1);
		String logtext = "["+formattedDate+"] "+log;
		ConsoleBoxM.addElement(logtext);
		d1 = null;
		df = null;
	}
	
	public void addLogToConsoleBox(String log, int logtype) {
		String [] types;
		types = new String[3];
		types[0] = "HATA";
		types[1] = "BÝLGÝ";
		types[2] = "KULLANICI";
		Date d1 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY-HH:mm");
		String formattedDate = df.format(d1);
		String logtext = "["+formattedDate+"] "+"["+types[logtype]+"] "+log;
		ConsoleBoxM.addElement(logtext);
		d1 = null;
		df = null;
	}
	
	public void exitServer(String hata) {
		JOptionPane.showMessageDialog(null,"Sunucu açýlýrken bir hata oluþtu: "+hata);
		System.exit(0);
	}
}
