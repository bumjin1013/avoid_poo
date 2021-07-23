import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


//똥이 여러개 떨어질 텐데 그때마다 한개씩 객체를 생성하면 그 많은 객채를 관리하기란 벅차므로 set같은 자료구로를 잘 사용하여 해결!
public class Game extends Thread
{

	//object를 관리하기 위한 자료들
	//Set은 순서를 신경쓰지 않고 데이터가 존재하냐 안하냐만이 중요

	public HashSet<GameObject> objects = new HashSet<>();
	public Queue<GameObject> addList = new LinkedList<>();
	public Queue<GameObject> removeList = new LinkedList<>();
	
	//[Queue참고] http://tcpschool.com/java/java_collectionFramework_stackQueue 



	public Image JJangguImg = new ImageIcon(Main.class.getResource("./images/JJanggu.png")).getImage(); //짱구이미지
	public Image HuniImg = new ImageIcon(Main.class.getResource("./images/Huni.png")).getImage();  //훈이이미지
	public Image AttackImg = new ImageIcon(Main.class.getResource("./images/JJangguAttack.png")).getImage(); //짱구공격
	public int JJangguHp = 100; //짱구 Hp = 100
	public int HuniHp = 100; //훈이 Hp = 100
	public int PressedSpace = 0; //짱구 space 게이지  초기값 = 0
	public boolean isExitSward = false; 

	public void tick() //매 반복마다 실행되어야 할 내용들
	{
		if (Avoid_poop.game != null)
		{
			if (KeyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
				Avoid_poop.x -= 5; //왼쪽으로 5만큼 이동
				Avoid_poop.x = Math.max(250, Avoid_poop.x); // Math.max 함수는 숫자 집합중에 가장 큰 수를 리턴하는 함수 (좌, 우 움직임의 한계점 계산)​
				Avoid_poop.x = Math.min(Avoid_poop.x, 750); // Math.min 함수는 숫자 집합중에서 가장 작은 수를 리턴하는 함수  (좌, 우 움직임의 한계점 계산)
				JJangguImg = new ImageIcon(Main.class.getResource("./images/JJanggu.png")).getImage();
			}
			if (KeyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
				Avoid_poop.x += 5; //오른쪽으로 5만큼 이동
				Avoid_poop.x = Math.max(250, Avoid_poop.x); //위와같음
				Avoid_poop.x = Math.min(Avoid_poop.x, 750); //위와같음
				JJangguImg = new ImageIcon(Main.class.getResource("./images/JJanggu.png")).getImage();
			}
			if (KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) //스페이스바 - 짱구의 공격
			{
				PressedSpace = Math.min(30, PressedSpace+1); //게이지바 크기 증가
				
				if (isExitSward) {
				
					PressedSpace = 0; //space바를 떼면 다시 게이지바 크기 0
				}
			}
			else if (!KeyListener.isKeyPressed(KeyEvent.VK_SPACE) && PressedSpace != 0 && isExitSward == false)
			{
				addList.add(new Sward(Avoid_poop.x, 600, AttackImg, -PressedSpace)); //짱구의 Attack관리를 위해 Queue에 추가
				PressedSpace = 0; //게이지를 다시 0으로 초기화
				isExitSward = true; 
			}
		}

		objects.forEach(i->i.tick()); // set에 들어있는 모든 원소들의 tick함수 출력
		while (!addList.isEmpty()) 
			objects.add(addList.poll());
		while (!removeList.isEmpty()) 
			objects.remove(removeList.poll());
		//E poll()	
		//해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환하고, 해당 요소를 큐에서 제거
		//만약 큐가 비어있으면 null을 반환
	}
	public void addObject(GameObject object) //addList에 Object 추가
	{
		this.addList.add(object); 
	}

	public void removeObject(GameObject object) //removeList에 Object추가
	{
		this.removeList.add(object);
	}

	public void run() {

		PressedSpace = 0;
		isExitSward = false;
		while(Avoid_poop.isGameScreen) { //Play버튼을 누를때만 동작
			try {
				Thread.sleep(20);
				Avoid_poop.lock.lock(); //Thread가 동시에 돌아가는거 방지
				tick(); 
				Avoid_poop.lock.unlock();
			}
			catch(InterruptedException e ) {
				return;
			}
		}

	}

	public void ScreenDraw(Graphics g)
	{
		if (HuniHp <= 0) //훈이의 피가 다달면
		{
			JJangguHp = Integer.MAX_VALUE; //짱구가 이긴 상태 직후에 후니똥을 맞고 huniwin으로 바뀌는 경우가 있어서 짱구가 이긴 직후의 짱구의 Hp를 엄~청 큰값으로 변경

			g.drawImage(new ImageIcon(Main.class.getResource("./images/JJangguWin.png")).getImage(), 262, 27 , 560, 790, null);
		}
		else if (JJangguHp <= 0)
		{
			HuniHp = Integer.MAX_VALUE; //위와 같은 이유로 무한으로 변경
			g.drawImage(new ImageIcon(Main.class.getResource("./images/JJangguLose.png")).getImage(), 262, 27 , 560, 790, null);
		}
		else {
			g.setColor(Color.RED);
			g.fillRect(612, 34, HuniHp * 2, 20);
			g.fillRect(612, 774, JJangguHp * 2, 20);
			objects.forEach(i -> g.drawImage(i.image, (int) i.x, (int) i.y, null));
			//짱구 공격 게이지바
			if (PressedSpace != 0)
			{
				g.fillRect(Avoid_poop.x + 35, 642, PressedSpace, 5);  //짱구의 x좌표값, 머리위에, 누르는동안 , 5씩 게이지 증가
			}
		}
	}
}

