package DefendBase;

public class bullet {
  private int x;
  private int y;
  private int speed;
  private int type;
  private int damage = 3;
  private static int rS = 5;
  private boolean visible;
  private double X;
  private double Y;

  public bullet(int startX, int startY, double X, double Y, int i) {
    this.x = startX;
    this.y = startY;
    this.X = X;
    this.Y = Y;
    this.type = i;
    if (i == 1) {
      this.speed = rS;
    } else {
      this.speed = 10;
    }
    this.visible = true;
  }

  public bullet(int rs) {
    rS += rs;
  }

  public bullet() {
  }

  public void update() {
    this.x = ((int) (this.x + this.speed * this.X / Math.sqrt(this.X * this.X + this.Y * this.Y)));
    this.y = ((int) (this.y + this.speed * this.Y / Math.sqrt(this.X * this.X + this.Y * this.Y)));
    if ((this.x > 625) || (this.y > 465) || (this.x < 0) || (this.y < 0)) {
      this.visible = false;
    }
  }

  public int getx() {
    return this.x;
  }

  public int gety() {
    return this.y;
  }

  public double getX() {
    return this.X;
  }

  public double getY() {
    return this.Y;
  }

  public int getSpeed() {
    return this.speed;
  }

  public static void setrS(int rS) {
    rS = rS;
  }

  public boolean isVisible() {
    return this.visible;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public int getType() {
    return this.type;
  }

  public int getDamage() {
    return this.damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }
}
