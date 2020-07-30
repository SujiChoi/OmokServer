package Omok;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ø¿∏Ò Ω√¿€«œ±‚");
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 575, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uCD5C\uAC15\uC790\uB97C \uAC00\uB824\uB77C");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("±√º≠√º", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(216, 139, 125, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnsever = new JButton("\uC11C\uBC84\uB85C \uC811\uC18D\uD558\uAE30");
		btnsever.setBackground(Color.WHITE);
		btnsever.setFont(new Font("±º∏≤", Font.PLAIN, 13));
		btnsever.setBounds(66, 201, 181, 59);
		frame.getContentPane().add(btnsever);
		
		btnsever.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ServerFrame frameA = new ServerFrame();
				//frame.setVisible(false);
			}
		});
		
		JButton btnclient = new JButton("\uD074\uB77C\uC774\uC5B8\uD2B8\uB85C \uC811\uC18D");
		btnclient.setBackground(Color.WHITE);
		btnclient.setFont(new Font("±º∏≤", Font.PLAIN, 13));
		btnclient.setBounds(330, 201, 181, 59);
		frame.getContentPane().add(btnclient);
		
		btnclient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Clientip frameA = new Clientip();
				frame.setVisible(false);
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 559, 318);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC624   \uBAA9");
		lblNewLabel.setBounds(188, 61, 175, 58);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("±√º≠√º", Font.PLAIN, 50));
		
		frame.setVisible(true);
	}
}
