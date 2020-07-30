package Omok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Omok.ClientFrame.MyPanel;



public class ServerFrame extends JFrame {

	MyPanel frame1;
	
	JTextArea textArea; //��� ��������
	JTextField tfMsg;
	JButton btnSend;

	private JPanel contentPane;
	JLabel menu1;
	JLabel menu2;
	int trun;
	//�ٵϾ� ���̴� ��
	
	int x;
	int y;
	
	//����
	ServerSocket serverSocket;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	public ServerFrame() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 419);
		setTitle("���� ����");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 53, 321, 327);
		panel.setLayout(null);
		contentPane.add(panel);
		
		frame1 = new MyPanel();
		frame1.setBounds(0, 0, 321, 327);
		frame1.setBackground(Color.ORANGE); // 
		panel.add(frame1); //
		
		JLabel lblNewLabel = new JLabel("\uC624\uBAA9\uAC8C\uC784");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("�ü�ü", Font.PLAIN, 28));
		lblNewLabel.setBounds(0, 0, 321, 55);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("�ٽý���");
		btnNewButton.setBackground(SystemColor.textHighlightText);
		btnNewButton.setFont(new Font("�ü�", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regame();
				System.out.println("���� ���ư�");
				}
		});
		btnNewButton.setBounds(536, 347, 91, 23);
		contentPane.add(btnNewButton);
		
		JLabel menu1 = new JLabel("Server");
		//jar�� ������ �̹��� ÷���ϴ� ��� (�� ����� ������ �����ȿ� �־�� �Ѵ�.)
		URL imageURL = getClass().getResource("blackstone.png");
		menu1.setIcon(new ImageIcon(imageURL));
		//
		menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menu1.setFont(new Font("����", Font.BOLD, 30));
		menu1.setBounds(349, 53, 128, 61);
		contentPane.add(menu1);
		
		JLabel menu2 = new JLabel("Client");
		//jar�� ������ �̹��� ÷���ϴ� ���
		URL imageURL1 = getClass().getResource("whitestone.png");
		menu2.setIcon(new ImageIcon(imageURL1));
		//
		//menu2.setIcon(new ImageIcon("img/white-go-stone24.png"));
		menu2.setHorizontalAlignment(SwingConstants.CENTER);
		menu2.setFont(new Font("����", Font.BOLD, 30));
		menu2.setBounds(493, 53, 128, 61);
		contentPane.add(menu2);
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea); //��ũ�� �߰��ϱ�
		scrollPane.setBounds(342, 124, 279, 180);
		contentPane.add(scrollPane);
		textArea.setColumns(10);
		
		tfMsg = new JTextField("");
		tfMsg.setBounds(342, 314, 197, 21);
		contentPane.add(tfMsg);
		tfMsg.setColumns(10);
		
		JButton btnSend = new JButton("send");
		btnSend.setBounds(551, 314, 67, 23);
		contentPane.add(btnSend);
		
//		setTitle("Server");
//
//		setBounds(450, 50, 500, 350);
//
//		
//
//		textArea = new JTextArea();		
//
//		textArea.setEditable(false); //���� ����
//
//		JScrollPane scrollPane = new JScrollPane(textArea);
//
//		add(scrollPane,BorderLayout.CENTER);
//
//				
//
//		JPanel msgPanel = new JPanel();
//
//		msgPanel.setLayout(new BorderLayout());
//
//		tfMsg = new JTextField();
//
//		btnSend = new JButton("send");
//
//		msgPanel.add(tfMsg, BorderLayout.CENTER);
//
//		msgPanel.add(btnSend, BorderLayout.EAST);
//
//		
//
//		add(msgPanel,BorderLayout.SOUTH);
		
		Omokfunction.Omokarray(); 
		
		frame1.addMouseListener(new MouseAdapter() { 
			@Override
			public void mousePressed(MouseEvent e) { 
				Omokfunction Omok = new Omokfunction();
				
				x = (int)(Math.round(e.getX()/(double)20)-1); 
				y = (int)(Math.round(e.getY()/(double)20)-1);
				
				Omokstart(x,y);				
				sendOmok(x,y);
			}
		});
		
		
		//send ��ư Ŭ���� �����ϴ� ������ �߰�
		btnSend.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		//����Ű ������ �� �����ϱ�
		tfMsg.addKeyListener(new KeyAdapter() {
			//Ű���忡�� Ű �ϳ��� �������� �ڵ����� ����Ǵ� �޼ҵ�..: �ݹ� �޼ҵ�
			@Override
			public void keyPressed(KeyEvent e) {				
				super.keyPressed(e);
			//�Է¹��� Ű�� �������� �˾Ƴ���, KeyEvent ��ü�� Ű������ ���� ��������
				int keyCode = e.getKeyCode();
				switch(keyCode) {
				case KeyEvent.VK_ENTER:
					sendMessage();
					break;
				}
			}
		});		

		

		setVisible(true);

		tfMsg.requestFocus();

		//������ ������ �� �ֵ��� ���������� ����� ����� �� �ִ� �غ� �۾�!

		//��Ʈ��ũ �۾��� Main Thread�� �ϰ��ϸ� �ٸ� �۾�(Ű���� �Է�, Ŭ�� ��..)���� 

		//���� �� �� ����, ���α׷��� ����, �׷��� Main�� UI�۾��� �����ϵ��� �ϰ�, 

		//�ٸ� �۾���(���� �ɸ���)��  ������ Thread���� �����ϴ� ���� ������.	

		ServerThread serverThread = new ServerThread();
		serverThread.setDaemon(true); //���� ������ ���� ����
		serverThread.start();

		addWindowListener(new WindowAdapter() {			

			@Override //Ŭ���̾�Ʈ �����ӿ� window(â) ���� ������ �߰�

			public void windowClosing(WindowEvent e) {				

				super.windowClosing(e);

				try {

					if(dos != null) dos.close();
					if(dis != null) dis.close();
					if(socket != null) socket.close();
					if(serverSocket != null) serverSocket.close();

				} catch (IOException e1) {					

					e1.printStackTrace();

				}

			}			

		});

	}//������ �޼ҵ�	
	
	public void Omokstart(int x,int y) {
		Omokfunction Omok = new Omokfunction();
		
		if(x<0 || x>14||y<0||y>14) {
			return;
		}
		
		if(Omok.getXY(x, y) == Omok.getBlack() ||
				Omok.getXY(x, y) == Omok.getWhite()) { 
			return;}
		Omok.CheckXY(x,y); 

		int sam = Omok.samsam(x,y);

		if(sam >= 2) {
			Omokfunction.ChangeXY(x,y);
			JOptionPane.showMessageDialog(null, "���");
			return;}
		
					
		frame1.repaint(); 
		
		Omok.turnchange(); 
						
		if(Omok.winCheck(x, y)) {
			if(Omok.getCheck() == true) {
				new whitewin().setVisible(true);
			} else {
				new blackwin().setVisible(true);
			}
		}
		System.out.println("���� ȭ�� ���ȴ�!");
	}

	//�̳�Ŭ���� : ���������� �����ϰ� Ŭ���̾�Ʈ�� ������ ����ϰ�,

	//����Ǹ� �޽����� ���������� �޴� ���� ����

	class ServerThread extends Thread {

		@Override

		public void run() {			

			try {  //���� ���� ���� �۾�

				serverSocket = new ServerSocket(10001);
				textArea.append("���������� �غ�ƽ��ϴ�...\n");
				textArea.append("Ŭ���̾�Ʈ�� ������ ��ٸ��ϴ�.\n");				
				socket = serverSocket.accept();//Ŭ���̾�Ʈ�� �����Ҷ����� Ŀ��(������)�� ���
				textArea.append(socket.getInetAddress().getHostAddress() + "���� �����ϼ̽��ϴ�.\n");				

				//����� ���� ��Ʈ�� ����

				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				//Ŭ���̾�Ʈ ����
				while(true) {					
					String msg = dis.readUTF();//������ ���������� ���
					System.out.println(msg);
					if(msg.substring(0,2).equals("OM")) {
						StringTokenizer st = new StringTokenizer(msg," ");
						st.nextToken();
						x = Integer.parseInt(st.nextToken());
						y = Integer.parseInt(st.nextToken());
						System.out.println(x+" "+y);
						Omokstart(x,y);
					}else if(msg.substring(0,2).equals("RE")){
						Omokfunction.Omokarray();
						frame1.repaint();
						Omokfunction.startblack();
					}else {
					msg = msg.substring(2);
					textArea.append(" [Clinent] : " + msg + "\n");
					textArea.setCaretPosition(textArea.getText().length());
					}
				}				

			} catch (IOException e) {
				textArea.append("Ŭ���̾�Ʈ�� �������ϴ�.\n");
			}

		}

	}
	
	void regame() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					dos.writeUTF("RE");
					dos.flush();
					}
				 catch (IOException e) {
					e.printStackTrace();
				}
			}
		};		
		t.start();
	}

	//����� ���� ������
	void sendOmok(int x,int y) {
		String ax = x+" "+y;
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					dos.writeUTF("OM "+ax);
					dos.flush();
					}
				 catch (IOException e) {
					e.printStackTrace();
				}
			}
		};		
		t.start();
	}	
	

	//�޽��� �����ϴ� ��� �޼ҵ�
	void sendMessage() {
		String msg = tfMsg.getText(); //TextField�� ���ִ� �۾��� ������
		//2.����(Client)���� �޽��� �����ϱ�
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					if(msg.trim().length()==0) {
					}else {						
					tfMsg.setText(""); //�Է� �� ��ĭ����
					textArea.append(" [SERVER] : " + msg + "\n");//1.TextArea(ä��â)�� ǥ��
					textArea.setCaretPosition(textArea.getText().length()); //��ũ�� ���󰡰�
					dos.writeUTF("AA"+msg);
					dos.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};		
		t.start();
	}	

	class MyPanel extends JPanel{ 
		@Override
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        
	        g.setColor(Color.black); 
	        for(int i = 20; i<320;) { 
	        	g.drawLine(20, i, 300, i);
	        	i += 20;
	        }
	        for(int i = 20; i<320;) {
	        	g.drawLine(i, 20, i, 300);
	        	i += 20;
	        }
	       g.fillOval(156, 156, 8, 8);
	       g.fillOval(236, 76, 8, 8);
	       g.fillOval(76, 236, 8, 8);
	       g.fillOval(236, 236, 8, 8);
	       g.fillOval(76, 76, 8, 8);
	       
	       drawStone(g);
	    }
		
	   	public void drawStone(Graphics g) {
			for(int y=0;y<15;y++){
				for(int x=0;x<15;x++){
					if(Omokfunction.getXY(x,y)==Omokfunction.getBlack()) {
						//System.out.println(x+" "+y);
						drawBlack(g,x,y);
						repaint();
					}
					else if(Omokfunction.getXY(x,y)==Omokfunction.getWhite())
						drawWhite(g, x, y);
						repaint(); 
				}
			}
		}

	    public void drawBlack(Graphics g,int x, int y) {
	    	g.setColor(Color.black);
	    	g.fillOval((x+1)*20-8, (y+1)*20-8, 16, 16);
	    }
	    public void drawWhite(Graphics g,int x, int y) {
	    	g.setColor(Color.WHITE);
	    	g.fillOval((x+1)*20-8, (y+1)*20-8, 16, 16);
	    }
	}
}//class

