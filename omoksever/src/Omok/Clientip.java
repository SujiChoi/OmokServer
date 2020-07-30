package Omok;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Clientip {

	private JFrame frame;
	private JTextField ip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientip window = new Clientip();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Clientip() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("¿À¸ñ Á¢¼ÓÇÏ±â");
		frame.setBounds(100, 100, 488, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 470, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel clientip = new JLabel("\uC624\uBAA9 \uC11C\uBC84\uC5D0 \uC811\uC18D\uD558\uAE30");
		clientip.setFont(new Font("±¼¸²", Font.PLAIN, 32));
		clientip.setBounds(73, 51, 326, 68);
		panel.add(clientip);
		
		JLabel labelip = new JLabel("\uC11C\uBC84 IP \uC785\uB825");
		labelip.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		labelip.setBounds(43, 149, 113, 33);
		panel.add(labelip);
		
		ip = new JTextField("192.168.0.13");
		ip.setBounds(141, 154, 200, 26);
		panel.add(ip);
		ip.setColumns(10);
		
		JButton connect = new JButton("\uC811\uC18D");
		connect.setBackground(Color.WHITE);
		connect.setBounds(350, 155, 72, 23);
		panel.add(connect);
		
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ipaddress = ip.getText();
				ClientFrame frameA = new ClientFrame(ipaddress);
				frame.setVisible(false);
			}
		});
		
		frame.setVisible(true);
	}

}
