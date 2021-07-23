import java.awt.*;

public class Poop extends GameObject //GameObject 상속
{
    public int vely; //똥 속도
    public Poop(double x, double y, Image image, int vely) //똥 x좌표, y좌표, 이미지, 속도
    {
        super(x, y, image);
        this.vely = vely;
    }
    public void tick()
    {
        y += vely;
        if (y >= 800) //똥이 화면밖을 넘어가면
        {
            Avoid_poop.game.removeObject(this); //똥 삭제
        }
        
        for (GameObject obj : Avoid_poop.game.objects) //향상된 for문 / for(변수타입 : 배열이름) https://library1008.tistory.com/66
        {
            if (obj.getBounds().intersects(this.getBounds()) && obj.getClass().getName().equalsIgnoreCase("JJanggu")) //짱구와 충돌시
            	// intersects (double x, double y, double w, double h)
                // Shape 내부 영역이, 지정된 구형 영역의 내부 영역과 교차할지 어떨지를 판정합니다.
            {
                Avoid_poop.game.JJangguHp -= 5; //짱구의 HP를 -5만큼 깎음
                Avoid_poop.game.removeObject(this); //똥 이미지 삭제
            }
            if (obj.getBounds().intersects(this.getBounds()) && obj.getClass().getName().equalsIgnoreCase("Sward")) //무기와 충돌시
            {
                Avoid_poop.game.removeObject(this); //똥이미지 삭제
                Avoid_poop.game.removeObject(obj); //
                Avoid_poop.game.isExitSward = false; 
            }
        }
    }
}
