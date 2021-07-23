import javax.swing.*;
import java.awt.*;

public class Sward extends GameObject//짱구 공격 object, GameObject 상속
{
    public int vely; //짱구 공격 속도
    public Sward(double x, double y, Image image, int vely) //짱구 공격 x좌표, y좌표, 이미지, 공격속도
    {
        super(x, y, image);
        this.vely = vely;
    }

    public void tick() //매 실행마다 반복되어야 할 함수
    {
        y += vely; // y값이 vely값 만큼 올라감
        if (y <= 0) // 짱구공격이 화면 밖으로 나갈경우
        {
        	
            Avoid_poop.game.removeObject(this); //짱구 공격 이미지 삭제
            Avoid_poop.game.isExitSward = false; 
        }

        for (GameObject obj : Avoid_poop.game.objects) //향상된 for문 / for(변수타입 : 배열이름) https://library1008.tistory.com/66
        {
            if (obj.getBounds().intersects(this.getBounds()) && obj.getClass().getName().equalsIgnoreCase("Huni")) //훈이와 충돌시
            {
                Avoid_poop.game.HuniHp -= 5; //훈이 HP -5만큼 감소
                Avoid_poop.game.removeObject(this); //짱구 공격 이미지 삭제
                Avoid_poop.game.isExitSward = false; 
            }

        }
    }
}
