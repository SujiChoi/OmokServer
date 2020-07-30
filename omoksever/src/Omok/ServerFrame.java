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
	
	JTextArea textArea; //멤버 참조변수
	JTextField tfMsg;
	JButton btnSend;

	private JPanel contentPane;
	JLabel menu1;
	JLabel menu2;
	int trun;
	//바둑알 놓이는 곳
	
	int x;
	int y;
	
	//소켓
	ServerSocket serverSocket;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	public ServerFrame() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 419);
		setTitle("오목 서버");
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
		lblNewLabel.setFont(new Font("궁서체", Font.PLAIN, 28));
		lblNewLabel.setBounds(0, 0, 321, 55);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("다시시작");
		btnNewButton.setBackground(SystemColor.textHighlightText);
		btnNewButton.setFont(new Font("궁서", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regame();
				System.out.println("서버 돌아감");
				}
		});
		btnNewButton.setBounds(536, 347, 91, 23);
		contentPane.add(btnNewButton);
		
		JLabel menu1 = new JLabel("Server");
		//jar로 묶을때 이미지 첨부하는 방법 (이 방법은 동일한 폴더안에 있어야 한다.)
		URL imageURL = getClass().getResource("blackstone.png");
		menu1.setIcon(new ImageIcon(imageURL));
		//
		menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menu1.setFont(new Font("굴림", Font.BOLD, 30));
		menu1.setBounds(349, 53, 128, 61);
		contentPane.add(menu1);
		
		JLabel menu2 = new JLabel("Client");
		//jar로 묶을때 이미지 첨부하는 방법
		URL imageURL1 = getClass().getResource("whitestone.png");
		menu2.setIcon(new ImageIcon(imageURL1));
		//
		//menu2.setIcon(new ImageIcon("img/white-go-stone24.png"));
		menu2.setHorizontalAlignment(SwingConstants.CENTER);
		menu2.setFont(new Font("굴림", Font.BOLD, 30));
		menu2.setBounds(493, 53, 128, 61);
		contentPane.add(menu2);
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea); //스크롤 추가하기
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
//		textArea.setEditable(false); //쓰기 금지
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
		
		
		//send 버튼 클릭에 반응하는 리스너 추가
		btnSend.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		//엔터키 눌렀을 때 반응하기
		tfMsg.addKeyListener(new KeyAdapter() {
			//키보드에서 키 하나를 눌렀을때 자동으로 실행되는 메소드..: 콜백 메소드
			@Override
			public void keyPressed(KeyEvent e) {				
				super.keyPressed(e);
			//입력받은 키가 엔터인지 알아내기, KeyEvent 객체가 키에대한 정보 갖고있음
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

		//상대방이 접속할 수 있도록 서버소켓을 만들고 통신할 수 있는 준비 작업!

		//네트워크 작업을 Main Thread가 하게하면 다른 작업(키보드 입력, 클릭 등..)들을 

		//전혀 할 수 없음, 프로그램이 멈춤, 그래서 Main은 UI작업에 전념하도록 하고, 

		//다른 작업들(오래 걸리는)은  별도의 Thread에게 위임하는 것이 적절함.	

		ServerThread serverThread = new ServerThread();
		serverThread.setDaemon(true); //메인 끝나면 같이 종료
		serverThread.start();

		addWindowListener(new WindowAdapter() {			

			@Override //클라이언트 프레임에 window(창) 관련 리스너 추가

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

	}//생성자 메소드	
	
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
			JOptionPane.showMessageDialog(null, "삼삼");
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
		System.out.println("서버 화면 눌렸다!");
	}

	//이너클래스 : 서버소켓을 생성하고 클라이언트의 연결을 대기하고,

	//연결되면 메시지를 지속적으로 받는 역할 수행

	class ServerThread extends Thread {

		@Override

		public void run() {			

			try {  //서버 소켓 생성 작업

				serverSocket = new ServerSocket(10001);
				textArea.append("서버소켓이 준비됐습니다...\n");
				textArea.append("클라이언트의 접속을 기다립니다.\n");				
				socket = serverSocket.accept();//클라이언트가 접속할때까지 커서(스레드)가 대기
				textArea.append(socket.getInetAddress().getHostAddress() + "님이 접속하셨습니다.\n");				

				//통신을 위한 스트림 생성

				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				//클라이언트 내용
				while(true) {					
					String msg = dis.readUTF();//상대방이 보낼때까지 대기
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
				textArea.append("클라이언트가 나갔습니다.\n");
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

	//오목알 리셋 보내기
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
	

	//메시지 전송하는 기능 메소드
	void sendMessage() {
		String msg = tfMsg.getText(); //TextField에 써있는 글씨를 얻어오기
		//2.상대방(Client)에게 메시지 전송하기
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					if(msg.trim().length()==0) {
					}else {						
					tfMsg.setText(""); //입력 후 빈칸으로
					textArea.append(" [SERVER] : " + msg + "\n");//1.TextArea(채팅창)에 표시
					textArea.setCaretPosition(textArea.getText().length()); //스크롤 따라가게
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

