import java.awt.*;

public class Poop extends GameObject //GameObject ���
{
    public int vely; //�� �ӵ�
    public Poop(double x, double y, Image image, int vely) //�� x��ǥ, y��ǥ, �̹���, �ӵ�
    {
        super(x, y, image);
        this.vely = vely;
    }
    public void tick()
    {
        y += vely;
        if (y >= 800) //���� ȭ����� �Ѿ��
        {
            Avoid_poop.game.removeObject(this); //�� ����
        }
        
        for (GameObject obj : Avoid_poop.game.objects) //���� for�� / for(����Ÿ�� : �迭�̸�) https://library1008.tistory.com/66
        {
            if (obj.getBounds().intersects(this.getBounds()) && obj.getClass().getName().equalsIgnoreCase("JJanggu")) //¯���� �浹��
            	// intersects (double x, double y, double w, double h)
                // Shape ���� ������, ������ ���� ������ ���� ������ �������� ����� �����մϴ�.
            {
                Avoid_poop.game.JJangguHp -= 5; //¯���� HP�� -5��ŭ ����
                Avoid_poop.game.removeObject(this); //�� �̹��� ����
            }
            if (obj.getBounds().intersects(this.getBounds()) && obj.getClass().getName().equalsIgnoreCase("Sward")) //����� �浹��
            {
                Avoid_poop.game.removeObject(this); //���̹��� ����
                Avoid_poop.game.removeObject(obj); //
                Avoid_poop.game.isExitSward = false; 
            }
        }
    }
}
