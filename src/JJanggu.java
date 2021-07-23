import java.awt.*;

public class JJanggu extends GameObject
{
    public JJanggu(double x, double y, Image image)
    {
        super(x, y, image);
    }
    public void tick()
    {
        x=Avoid_poop.x;

    }
    public Rectangle getBounds()
    {
        return new Rectangle((int) Avoid_poop.x, (int) 652, (int)WIDTH, (int)HEIGHT);
    }
}
