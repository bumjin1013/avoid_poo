import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Avoid_poop extends JFrame{
	public static ReentrantLock lock = new ReentrantLock();
	//Game�̶� MainThread �ΰ��� ������ ������ �߻�
	//https://eskeptor.tistory.com/83


	private Image screenImage;
	private Graphics screenGraphic;
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("./images/menubar.png")));
	
	
	private ImageIcon quitbtn = new ImageIcon(Main.class.getResource("./images/quitbtn.png"));
	private ImageIcon quitbtnentered = new ImageIcon(Main.class.getResource("./images/quitbtnentered.png"));
	private ImageIcon playbtnImg = new ImageIcon(Main.class.getResource("./images/playbtn.png"));
	private ImageIcon playbtnenteredImg = new ImageIcon(Main.class.getResource("./images/playbtnentered.png"));
	private ImageIcon exitbtnImg = new ImageIcon(Main.class.getResource("./images/exitbtn.png"));
	private ImageIcon exitbtnenteredImg = new ImageIcon(Main.class.getResource("./images/exitbtnentered.png"));
	private ImageIcon helpbtnImg = new ImageIcon(Main.class.getResource("./images/helpbtn.png"));
	private ImageIcon helpbtnenteredImg = new ImageIcon(Main.class.getResource("./images/helpbtnentered.png"));
	private ImageIcon backbtnImg = new ImageIcon(Main.class.getResource("./images/backbtn.png"));
	private ImageIcon backbtnenteredImg = new ImageIcon(Main.class.getResource("./images/backbtnentered.png"));
	private ImageIcon gamebackbtnImg = new ImageIcon(Main.class.getResource("./images/backbtn.png"));
	private ImageIcon gamebackbtnenteredImg = new ImageIcon(Main.class.getResource("./images/backbtnentered.png"));
	
	private JButton playbtn = new JButton(playbtnImg); //Play Button ����
	private JButton helpbtn = new JButton(helpbtnImg); //Help Button ����
	private JButton exitbtn = new JButton(exitbtnImg); //Exit Button ����
	private JButton quitButton = new JButton(quitbtn); //�޴��� Quit Button ����
	private JButton backbtn = new JButton(backbtnImg); //back Button ����
	private JButton gamebackbtn = new JButton(backbtnImg);
	
	private int mouseX, mouseY; //�޴��� ���콺 ��ǥ 
	
	private Image background = new ImageIcon(Main.class.getResource("./images/Background_title.jpg")).getImage();
	
	
	public static boolean isGameScreen = false;
	public static boolean isMainScreen = false;
	public static boolean isHelpScreen = false;
	
	
	public static Game game = new Game(); //play button �� ������ ���ӽ���
	

	public static int x = 500; //¯�� x��ǥ
	//��Ʈ�� ���� �߰�
	Music introMusic = new Music("Introsound1.mp3", false);
	//���� ���� �߰�
	Music GameIntroMusic = new Music("ingamesound.mp3", true);
	
	
	public Avoid_poop() {
		
	
		introMusic.start(); //��Ʈ�� ���� ����
		
		setFocusable(true); //��Ŀ�� ����
		
		addKeyListener(new KeyListener()); //¯���� ����Ű, �����̽��� �߰�
		
		setUndecorated(true);
		setTitle("¯���� �����ϱ�");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //â ���� ���� 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true); 
		setLayout(null);

		backbtn.setBounds(20, 35, 60, 70); //��ư ��ġ ũ�� ����
		backbtn.setBorderPainted(false);
		backbtn.setContentAreaFilled(false); //��ư �Ѽ� ����
		backbtn.setFocusPainted(false); //��ư��� �׵θ� ����
		backbtn.addMouseListener(new MouseAdapter() { //Back��ư ���콺 ������
			@Override
			public void mouseEntered(MouseEvent e) { //Back��ư�� ���콺 �ö󰥽�
				backbtn.setIcon(backbtnenteredImg); 
				backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Hand Cursor�� ����
				Music buttonEnteredMusic = new Music("btnenteredsound.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) { //Back��ư���� ���콺 ������ �� 
				backbtn.setIcon(backbtnImg); //�̹�������
				backbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�Ϲ� Cursor�� ����
				
			}
			@Override
			public void mousePressed(MouseEvent e) { //Back��ư�� ���콺 �ö󰥽�
				Music buttonPressedMusic = new Music("btnclickedsound.mp3", false);
				buttonPressedMusic.start(); 
				backMain();	//Main ȭ������ ���ư�
				backbtn.setVisible(false); //Back��ư�� �Ⱥ��̰�
			}
		});
		add(backbtn);
		backbtn.setVisible(false);
		
		//game -> main gameback button
				gamebackbtn.setBounds(20, 35, 60, 70);
				gamebackbtn.setBorderPainted(false);
				gamebackbtn.setContentAreaFilled(false);
				gamebackbtn.setFocusPainted(false);
				gamebackbtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						gamebackbtn.setIcon(gamebackbtnenteredImg);
						gamebackbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("btnenteredsound.mp3", false);
						buttonEnteredMusic.start();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						gamebackbtn.setIcon(gamebackbtnImg);
						gamebackbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						
					}
					@Override
					public void mousePressed(MouseEvent e) {
						Music buttonPressedMusic = new Music("btnclickedsound.mp3", false);
						buttonPressedMusic.start();
						GameIntroMusic.close();
						backMain();	
						gamebackbtn.setVisible(false);
					}
				});
				add(gamebackbtn);
				gamebackbtn.setVisible(false);
		
		
		//play button
		playbtn.setBounds(440, 550, 220, 70);
		playbtn.setBorderPainted(false);
		playbtn.setContentAreaFilled(false);
		playbtn.setFocusPainted(false);
		playbtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				playbtn.setIcon(playbtnenteredImg);
				playbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("btnenteredsound.mp3", false);
				buttonEnteredMusic.start();
		
				
			}
			public void mouseExited(MouseEvent e) {
				playbtn.setIcon(playbtnImg);
				playbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				playbtn.setVisible(false);
				helpbtn.setVisible(false);
				exitbtn.setVisible(false);
				background = new ImageIcon(Main.class.getResource("./images/GameBackground.png")).getImage();
				Music buttonPressedMusic = new Music("btnclickedsound.mp3", false);
				buttonPressedMusic.start();
				introMusic.close();
				GameIntroMusic.start();
				gamebackbtn.setVisible(true);
				isGameScreen = true; //play button ������ GameScreenDraw
				isMainScreen = false;
				game.addObject(new JJanggu(Avoid_poop.x,652, game.JJangguImg));
				game.addObject(new Huni(500, 58, game.HuniImg));
				new Thread(game).start();
			}
		});
		add(playbtn);
		
		
		//help button
		
		helpbtn.setBounds(20, 40, 60, 70);
		helpbtn.setBorderPainted(false);
		helpbtn.setContentAreaFilled(false);
		helpbtn.setFocusPainted(false);
		helpbtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				helpbtn.setIcon(helpbtnenteredImg);
				helpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("btnenteredsound.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e) {
				helpbtn.setIcon(helpbtnImg);
				helpbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				playbtn.setVisible(false);
				helpbtn.setVisible(false);
				exitbtn.setVisible(false);
				Music buttonPressedMusic = new Music("btnclickedsound.mp3", false);
				buttonPressedMusic.start();
				background = new ImageIcon(Main.class.getResource("./images/helpBackground.jpg")).getImage();
				isMainScreen = false;
				isGameScreen = false;
				backbtn.setVisible(true);
			}
		});
		add(helpbtn);
		
		
		//exit button
		exitbtn.setBounds(440, 610, 220, 100);
		exitbtn.setBorderPainted(false);
		exitbtn.setContentAreaFilled(false);
		exitbtn.setFocusPainted(false);
		exitbtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exitbtn.setIcon(exitbtnenteredImg);
				exitbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("btnenteredsound.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e) {
				exitbtn.setIcon(exitbtnImg);
				exitbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("btnclickedsound.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				
			}
		});	
		add(exitbtn);
		
		//MenuBar Quit Button
				quitButton.setBounds(1070, -1, 30, 30);
				quitButton.setBorderPainted(false);
				quitButton.setContentAreaFilled(false);
				quitButton.setFocusPainted(false);
				quitButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						quitButton.setIcon(quitbtnentered);
						quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						quitButton.setIcon(quitbtn);
						quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
						System.exit(0);
					}
				});
				add(quitButton);
		
		//MenuBar
		menuBar.setBounds(0, -2, 1100, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		if(isGameScreen) {
			game.start(); //Game Ŭ���� thread ����
		}
		
	}
	
	//Mainȭ������ ���ư��� 
	public void backMain() { 
		lock.lock(); //Thread�� ���ÿ� ���ư��� ������ ���� ���� 
		GameIntroMusic = new Music("ingamesound.mp3", true); //��Ʈ�� ���� ����
		playbtn.setVisible(true); //Play, Help, Exit Button ���̰� ����
		helpbtn.setVisible(true);
		exitbtn.setVisible(true);
		gamebackbtn.setVisible(false); //Main ȭ���̹Ƿ� BackButton�� �Ⱥ��̰� ����
		background = new ImageIcon(Main.class.getResource("./images/Background_title.jpg")).getImage();
		
		game.JJangguHp = 100;
		game.HuniHp = 100;
		game.objects.clear(); //GameObject���� �������
		game.removeList.clear();
		game.addList.clear();
		isGameScreen = false;
		isMainScreen = true;

		lock.unlock();
	}

	
	//���� ���۸�
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		lock.lock();
		g.drawImage(background, 0, 0 , Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, null);
		paintComponents(g);
		if(isGameScreen)
		{
			game.ScreenDraw(g);
		}
		this.repaint();
		lock.unlock();
	}
}