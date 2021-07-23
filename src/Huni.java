import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Huni extends GameObject
{
    public Huni(double x, double y, Image image)
    {
        super(x, y, image);
    }
    int remainTick = 50;
    int moveTime = 10; //랜덤하게 움직이게 할건데, 매 tick마다 방향을 바꾸면 엄청 떨리므로
    int velx = 10; //처음 시작하면 오른쪽으로 이동
    public void tick()
    {
        x+=velx; //velx값 만큼 x값 이동
        //훈이 위치의 끝조절
        x = Math.max(250, x);
        x = Math.min(x, 750);
        moveTime--;
        if (moveTime == 0) //moveTime 이 0이면
        {
            moveTime = 10; //moveTime을 0으로 바꿔주고
            if (new Random().nextInt() % 2 == 0) //랜덤 숫자가 짝수이면 
                velx = 10; //velx값 +10
            else
                velx = -10; //홀수이면 velx 값 -10
        }
        remainTick--; 
        if (remainTick == 0) //remainTick이 0이면
        {
            remainTick = 30; //후니가 똥싸는 속도
            Avoid_poop.game.addObject(new Poop(x, y, new ImageIcon(Main.class.getResource("./images/Poop.png")).getImage(), 10));
        }
    }
}
