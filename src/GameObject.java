import java.awt.*;

//GameObject 관리를 위한 클래스
public class GameObject
{
    public double x;
    public double y; 
    public double WIDTH;
    public double HEIGHT;
    public Image image;
    public void tick()
    {
        //override 필요
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
