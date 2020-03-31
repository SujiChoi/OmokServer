package Omok;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;



public class MainFrame extends JFrame {

	
	public MainFrame() {

		setTitle("Swing TCP Chatting Test");
		setBounds(10, 50, 400, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		JButton btnServer = new JButton("Server");
		JButton btnClient = new JButton("Client");

		

		//��ưŬ�� �׼ǿ� �����ϱ� ���� ������ ��ü ���� �� �߰�

		btnServer.addActionListener(new ActionListener() {			

			@Override

			public void actionPerformed(ActionEvent e) {

				//ServerFrame ��ü ����

				ServerFrame frame = new ServerFrame();

			}

		});

		btnClient.addActionListener(new ActionListener() {			

			@Override

			public void actionPerformed(ActionEvent e) {

				//ClientFrame ��ü ����

				ClientFrame frame = new ClientFrame();

			}

		});

		

		panel.add(btnServer);

		panel.add(btnClient);

		

		add(panel, BorderLayout.CENTER);

		

		setVisible(true);

	}



	public static void main(String[] args) {

		new MainFrame();

	}

}