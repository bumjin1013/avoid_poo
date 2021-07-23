import java.awt.*;

//GameObject ������ ���� Ŭ����
public class GameObject
{
    public double x;
    public double y; 
    public double WIDTH;
    public double HEIGHT;
    public Image image;
    public void tick()
    {
        //override �ʿ�
    }
    public GameObject(double x, double y, Image image)
    {
        this(x, y, image.getWidth(null), image.getHeight(null));
        this.image = image;
    }

    public GameObject(double x, double y, int WIDTH, int HEIGHT)
    {
        this.x = x;
        this.y = y;

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, (int)WIDTH, (int)HEIGHT);
    }
}
