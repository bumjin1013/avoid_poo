import javax.swing.*;
import java.awt.*;

public class Sward extends GameObject//¯�� ���� object, GameObject ���
{
    public int vely; //¯�� ���� �ӵ�
    public Sward(double x, double y, Image image, int vely) //¯�� ���� x��ǥ, y��ǥ, �̹���, ���ݼӵ�
    {
        super(x, y, image);
        this.vely = vely;
    }

    public void tick() //�� ���ึ�� �ݺ��Ǿ�� �� �Լ�
    {
        y += vely; // y���� vely�� ��ŭ �ö�
        if (y <= 0) // ¯�������� ȭ�� ������ �������
        {
        	
            Avoid_poop.game.removeObject(this); //¯�� ���� �̹��� ����
            Avoid_poop.game.isExitSward = false; 
        }

        for (GameObject obj : Avoid_poop.game.objects) //���� for�� / for(����Ÿ�� : �迭�̸�) https://library1008.tistory.com/66
        {
            if (obj.getBounds().intersects(this.getBounds()) && obj.getClass().getName().equalsIgnoreCase("Huni")) //���̿� �浹��
            {
                Avoid_poop.game.HuniHp -= 5; //���� HP -5��ŭ ����
                Avoid_poop.game.removeObject(this); //¯�� ���� �̹��� ����
                Avoid_poop.game.isExitSward = false; 
            }

        }
    }
}
