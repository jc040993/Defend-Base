package DefendBase;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.PrintStream;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener, MouseListener, MouseMotionListener, Runnable {
  private int now = 0;
  private int before = 0;
  private int Trans = 0;
  private int US3 = 0;
  private int US4 = 0;
  private int US5 = 0;
  private int US6 = 0;
  private int baseX = 280;
  private int baseY = 210;
  private int firerate = 3;
  private boolean fire = false;
  private boolean running = false;
  private long start = System.currentTimeMillis();
  private long bulletTimeS = 0L;
  private long turretTimeS = 0L;
  private int frameW;
  private int frameH;
  private int waveno;
  private int X;
  private int Y;
  private int ex;
  private int ey;
  private int mouseX;
  private int mouseY;
  private int clickX;
  private int clickY;
  private int Speed;
  private int roboHealth;
  private int baseHealth;
  private int TScore;
  private int SScore;
  private int fireN;
  private static int roboX;
  private static int roboY;
  private static int tf;
  private static int Screen;
  private Image close_Img;
  private Image db_img;
  private Image bg_Img;
  private Image base_Img;
  private Image baseupg_Img;
  private Image robo_Img;
  private Image bullet_Img;
  private Image turret_Img;
  private Image rocket_Img;
  private Image rocketupg_Img;
  private Image bulletupg_Img;
  private Image ebullet_Img;
  private Image roboupg_Img;
  private Image enemy_Img;
  private Image enemy_Img1;
  private Image enemy_Img2;
  private Image enemy_Img3;
  private Image enemy_Img4;
  private Image enemy_Img5;
  private Image pop_Img1;
  private Image pop_Img2;
  private Image gameover_Img;
  private Image Coin_Img;
  private Image roboHealth_Img;
  private Image dialogue_Img;
  private Graphics db_g;
  private Button playgame;
  private Button about;
  private Button back;
  private Button exit;
  private LinkedList<bullet> bulletLL;
  private LinkedList<enemy> enemyLL;
  private Thread UTh;
  private Thread ETh;
  private Rectangle roboRec;
  private Rectangle baseRec;
  private Rectangle bulletRec1;
  private Rectangle bulletRec2;
  private Rectangle enemyRec;
  private long bulletTimeN;
  private long turretTimeN;

  public static void main(String[] args) {
    System.out.println("Main");

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Main();
      }
    });
  }

  private Main() {
    System.out.println("constructor");
    setTitle("Defend Base");
    pack();
    requestFocus();
    requestFocusInWindow();
    setSize(640, 480);
    setResizable(false);
    setFocusable(true);
    setEnabled(true);
    setVisible(true);
    setLocation(300, 100);
    setLayout(null);
    setSize(640, 480);
    setDefaultCloseOperation(3);
    setVisible(true);
    preloading();
  }

  public Main(int i) {
  }

  private void preloading() {
    System.out.println("preloading");
    loadimages();
    this.frameW = getWidth();
    this.frameH = getHeight();

    this.playgame = new Button("PlayGame");
    this.about = new Button("About");
    this.back = new Button("Back");
    this.exit = new Button("Exit");
    this.playgame.setBounds(this.frameW / 2 - 43, this.frameH / 2 - 20, 90, 30);
    this.about.setBounds(this.frameW / 2 - 30, this.frameH / 2 + 50, 60, 30);
    this.back.setBounds(this.frameW - 100, 20, 40, 30);
    this.exit.setBounds(570, 400, 40, 30);

    this.playgame.addActionListener(this);
    this.about.addActionListener(this);
    this.back.addActionListener(this);
    this.exit.addActionListener(this);
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    manager.addKeyEventDispatcher(new DisPatcher());
    addMouseMotionListener(this);
    addMouseListener(this);

    mainmenu();
  }

  private void mainmenu() {
    System.out.println("main menu");
    repaint();
    this.Trans = 1;
    Screen = 1;
    add(this.playgame);
    add(this.about);
    add(this.exit);
  }

  private void playgame() {
    System.out.println("inplaygame");
    this.Trans = 3;
    Screen = 3;

    this.running = true;
    this.X = 0;
    this.Y = 0;
    this.start = System.currentTimeMillis();
    this.bulletTimeS = 0L;
    this.turretTimeS = 0L;
    this.SScore = 0;
    this.TScore = 0;
    this.fireN = 0;
    this.firerate = 3;
    this.US3 = 0;
    this.US4 = 0;
    this.US5 = 0;
    this.US6 = 0;
    tf = 2;
    roboX = 206;
    roboY = 213;
    this.Speed = 2;
    bullet.setrS(5);
    this.waveno = 1;
    this.enemyLL = new LinkedList();
    this.bulletLL = new LinkedList();
    this.UTh = new Thread(this);
    enemy er = new enemy();
    this.ETh = new Thread(er);
    new enemy();

    this.roboHealth = 10;
    this.baseHealth = 20;

    this.ETh.start();
    this.UTh.start();
    repaint();
  }

  private void about() {
    System.out.println("inabout");
    this.Trans = 2;
    Screen = 2;
    repaint();
    add(this.back);
  }

  public void run() {
    System.out.println("mainrun");
    long overtime = 0L;
    long period = 10000000L;
    int delays = 0;
    int totaldelays = 10;
    while (this.running) {
      while (Screen == 3) {
        long beforetime = System.nanoTime();
        repaint();
        long aftertime = System.nanoTime();
        long difftime = aftertime - beforetime;
        long sleeptime = period - difftime - overtime;
        if ((sleeptime < period) && (sleeptime > 0L)) {
          try {
            Thread.sleep(sleeptime / 100000L);
            overtime = 0L;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else if (difftime > period) {
          overtime = difftime - period;
        } else {
          delays++;
          if (delays >= 10) {
            Thread.currentThread();
            Thread.yield();
            delays = 0;
            overtime = 0L;
          } else {
            overtime = 0L;
          }
        }
      }
      while (Screen == 4) {
        System.out.println("update run");
        repaint();
        Thsleep(500);
      }
      if (Screen == 5) {
        repaint();
        Thsleep(2000);
        this.bulletLL = null;
        this.enemyLL = null;
        mainmenu();
      }
    }
  }

  private void paintcomponent(Graphics g1) {
    System.out.println("paintcomponent");

    Graphics2D g = (Graphics2D) g1;
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    if ((Screen != 5) || (Screen != 4)) {
      g.drawImage(this.bg_Img, 0, 0, this);
    }
    if (Screen == 3) {
      this.baseRec = new Rectangle(this.baseX, this.baseY, this.base_Img.getWidth(this), this.base_Img.getHeight(this));
      g.drawImage(this.base_Img, this.baseX, this.baseY, this);

      System.out.println("printing enemy");
      this.enemyLL = enemy.getEnemyLL();
      for (int i = 0; i < this.enemyLL.size(); i++) {
        enemy ep = (enemy) this.enemyLL.get(i);
        if (ep.isVisible()) {
          AffineTransform enemyT = new AffineTransform();
          enemyT.translate(ep.getX(), ep.getY());
          double angle = Math.toDegrees(Math.atan2(roboY - ep.getY(), roboX - ep.getX()));
          switch (ep.getType()) {
          case 1:
            this.enemyRec = new Rectangle(ep.getX(), ep.getY(), this.enemy_Img1.getWidth(null),
                this.enemy_Img1.getHeight(null));
            enemyT.rotate(Math.toRadians(angle), this.enemy_Img1.getWidth(this) / 2,
                this.enemy_Img1.getHeight(this) / 2);
            this.enemy_Img = this.enemy_Img1;
            break;
          case 2:
            this.enemyRec = new Rectangle(ep.getX(), ep.getY(), this.enemy_Img2.getWidth(null),
                this.enemy_Img2.getHeight(null));
            enemyT.rotate(Math.toRadians(angle), this.enemy_Img2.getWidth(this) / 2,
                this.enemy_Img2.getHeight(this) / 2);
            this.enemy_Img = this.enemy_Img2;
            break;
          case 3:
            this.enemyRec = new Rectangle(ep.getX(), ep.getY(), this.enemy_Img3.getWidth(null),
                this.enemy_Img3.getHeight(null));
            enemyT.rotate(Math.toRadians(angle), this.enemy_Img3.getWidth(this) / 2,
                this.enemy_Img3.getHeight(this) / 2);
            this.enemy_Img = this.enemy_Img3;
            break;
          case 4:
            this.enemyRec = new Rectangle(ep.getX(), ep.getY(), this.enemy_Img4.getWidth(null),
                this.enemy_Img4.getHeight(null));
            enemyT.rotate(Math.toRadians(angle), this.enemy_Img4.getWidth(this) / 2,
                this.enemy_Img4.getHeight(this) / 2);
            this.enemy_Img = this.enemy_Img4;
            break;
          case 5:
            this.now = i;
            this.ex = ep.getX();
            this.ey = ep.getY();
            fire(4);
            this.enemyRec = new Rectangle(ep.getX(), ep.getY(), this.enemy_Img5.getWidth(null),
                this.enemy_Img5.getHeight(null));
            enemyT.rotate(Math.toRadians(angle), this.enemy_Img5.getWidth(this) / 2,
                this.enemy_Img5.getHeight(this) / 2);
            this.enemy_Img = this.enemy_Img5;
            break;
          }
          g.drawImage(this.enemy_Img, enemyT, this);
          if (this.enemyRec.intersects(this.baseRec)) {
            ep.setVisible(false);
            this.baseHealth -= 1;
          }
          if (this.roboRec.intersects(this.enemyRec)) {
            g.drawImage(this.pop_Img1, ep.getX(), ep.getY(), this);
            ep.setVisible(false);
            switch (ep.getType()) {
            case 1:
              this.roboHealth -= 1;
              break;
            case 2:
              this.roboHealth -= 2;
              break;
            case 3:
              this.roboHealth -= 2;
              break;
            case 4:
              this.roboHealth -= 2;
              break;
            case 5:
              this.roboHealth -= 3;
              break;
            }
          }
        } else if (!ep.isVisible()) {
          this.enemyRec = new Rectangle(ep.getX(), ep.getY(), this.Coin_Img.getWidth(null),
              this.Coin_Img.getHeight(null));
          if (!this.roboRec.intersects(this.enemyRec)) {
            g.drawImage(this.Coin_Img, ep.getX(), ep.getY(), this);
            ep.setSpeed(30);
          } else if (this.roboRec.intersects(this.enemyRec)) {
            this.SScore += ep.getScore();
            this.enemyLL.remove(i);
          }
        }
      }
      if (this.enemyLL.size() == 0) {
        if ((this.waveno > 50) || (this.waveno < 1)) {
          this.waveno = 1;
        }
        enemy en = new enemy();
        en.enemywave(this.waveno);

        this.waveno += 1;
      }
      if (this.US6 != 0) {
        turretupdate();
      }
      System.out.println("painting bullets");
      this.fireN += 1;
      if (this.fireN > this.firerate) {
        fireL();
        this.fireN = 0;
      }
      for (int i = 0; i < this.bulletLL.size(); i++) {
        bullet b = (bullet) this.bulletLL.get(i);
        if (b.getType() == 1) {
          if (b.isVisible()) {
            this.bulletRec1 = new Rectangle(b.getx(), b.gety(), 10, 10);
            this.bulletRec2 = new Rectangle(b.getx() + 25, b.gety() + 25, 10, 10);

            b.update();
            for (int j = 0; j < this.enemyLL.size(); j++) {
              enemy e = (enemy) this.enemyLL.get(j);
              if (e.isVisible()) {
                switch (e.getType()) {
                case 1:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img1.getWidth(this),
                      this.enemy_Img1.getHeight(this));
                  break;
                case 2:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img2.getWidth(this),
                      this.enemy_Img2.getHeight(this));
                  break;
                case 3:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img3.getWidth(this),
                      this.enemy_Img3.getHeight(this));
                  break;
                case 4:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img4.getWidth(this),
                      this.enemy_Img4.getHeight(this));
                  break;
                case 5:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img5.getWidth(this),
                      this.enemy_Img5.getHeight(this));
                  break;
                }
                if ((this.bulletRec1.intersects(this.enemyRec)) || (this.bulletRec2.intersects(this.enemyRec))) {
                  int enemyHealth = e.getEnemyHealth();
                  enemyHealth--;
                  e.setEnemyHealth(enemyHealth);
                  if (e.getEnemyHealth() <= 0) {
                    g.drawImage(this.pop_Img1, e.getX(), e.getY(), this);
                    e.setVisible(false);
                    this.TScore += e.getScore();
                  }
                  b.setVisible(false);
                  break;
                }
              }
            }
            if ((this.bulletRec1.intersects(this.baseRec)) || (this.bulletRec2.intersects(this.baseRec))) {
              b.setVisible(false);
              break;
            }
            g.drawImage(this.bullet_Img, b.getx(), b.gety(), 10, 10, this);
            g.drawImage(this.bullet_Img, b.getx() + 25, b.gety() + 25, 10, 10, this);
          } else {
            g.drawImage(this.pop_Img2, b.getx(), b.gety(), this);
            g.drawImage(this.pop_Img2, b.getx() + 25, b.gety() + 25, this);
            this.bulletLL.remove(i);
          }
        } else if (b.getType() == 2) {
          if (b.isVisible()) {
            this.bulletRec1 = new Rectangle(b.getx(), b.gety(), 60, 60);

            b.update();
            for (int j = 0; j < this.enemyLL.size(); j++) {
              enemy e = (enemy) this.enemyLL.get(j);
              if (e.isVisible()) {
                switch (e.getType()) {
                case 1:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img1.getWidth(this),
                      this.enemy_Img1.getHeight(this));
                  break;
                case 2:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img2.getWidth(this),
                      this.enemy_Img2.getHeight(this));
                  break;
                case 3:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img3.getWidth(this),
                      this.enemy_Img3.getHeight(this));
                  break;
                case 4:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img4.getWidth(this),
                      this.enemy_Img4.getHeight(this));
                  break;
                case 5:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img5.getWidth(this),
                      this.enemy_Img5.getHeight(this));
                  break;
                }
                if (this.bulletRec1.intersects(this.enemyRec)) {
                  e.setEnemyHealth(0);
                  g.drawImage(this.pop_Img1, e.getX(), e.getY(), this);
                  e.setVisible(false);
                  this.TScore += e.getScore();
                  int BD2 = b.getDamage();
                  BD2--;
                  if (BD2 > 0) {
                    b.setDamage(BD2);
                  } else {
                    b.setVisible(false);
                    break;
                  }
                }
              }
            }
            int BD1 = b.getDamage();
            if (this.bulletRec1.intersects(this.baseRec)) {
              if (BD1 > 0) {
                BD1--;
                b.setDamage(BD1);
              } else {
                b.setVisible(false);
                break;
              }
            }
            AffineTransform bulletT = new AffineTransform();
            bulletT.translate(b.getx(), b.gety());
            double angle = Math.toDegrees(Math.atan2(b.getY(), b.getX()));
            bulletT.rotate(Math.toRadians(angle), this.rocket_Img.getWidth(null) / 2,
                this.rocket_Img.getHeight(null) / 2);
            g.drawImage(this.rocket_Img, bulletT, this);
          } else {
            g.drawImage(this.pop_Img2, b.getx() + 25, b.gety() + 25, this);
            this.bulletLL.remove(i);
          }
        } else if (b.getType() == 3) {
          if (b.isVisible()) {
            this.bulletRec1 = new Rectangle(b.getx(), b.gety(), this.ebullet_Img.getWidth(this),
                this.ebullet_Img.getHeight(this));
            b.update();
            if (this.bulletRec1.intersects(this.roboRec)) {
              b.setVisible(false);
              this.roboHealth -= 1;
            } else if (this.bulletRec1.intersects(this.baseRec)) {
              b.setVisible(false);
              this.baseHealth -= 1;
            }
            AffineTransform bulletT = new AffineTransform();
            bulletT.translate(b.getx(), b.gety());
            double angle = Math.toDegrees(Math.atan2(b.getY(), b.getX()));
            bulletT.rotate(Math.toRadians(angle), this.ebullet_Img.getWidth(null) / 2,
                this.ebullet_Img.getHeight(null) / 2);
            g.drawImage(this.ebullet_Img, bulletT, this);
          } else {
            g.drawImage(this.pop_Img2, b.getx(), b.gety(), this);
            this.bulletLL.remove(i);
          }
        } else if (b.getType() == 4) {
          if (b.isVisible()) {
            this.bulletRec1 = new Rectangle(b.getx(), b.gety(), this.bullet_Img.getWidth(this),
                this.bullet_Img.getHeight(this));
            b.update();
            for (int j = 0; j < this.enemyLL.size(); j++) {
              enemy e = (enemy) this.enemyLL.get(j);
              if (e.isVisible()) {
                switch (e.getType()) {
                case 1:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img1.getWidth(this),
                      this.enemy_Img1.getHeight(this));
                  break;
                case 2:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img2.getWidth(this),
                      this.enemy_Img2.getHeight(this));
                  break;
                case 3:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img3.getWidth(this),
                      this.enemy_Img3.getHeight(this));
                  break;
                case 4:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img4.getWidth(this),
                      this.enemy_Img4.getHeight(this));
                  break;
                case 5:
                  this.enemyRec = new Rectangle(e.getX(), e.getY(), this.enemy_Img5.getWidth(this),
                      this.enemy_Img5.getHeight(this));
                  break;
                }
                if (this.bulletRec1.intersects(this.enemyRec)) {
                  int enemyHealth = e.getEnemyHealth();
                  enemyHealth--;
                  e.setEnemyHealth(enemyHealth);
                  if (e.getEnemyHealth() <= 0) {
                    g.drawImage(this.pop_Img1, e.getX(), e.getY(), this);
                    e.setVisible(false);
                    this.TScore += e.getScore();
                  }
                  b.setVisible(false);
                  break;
                }
              }
            }
            AffineTransform bulletT = new AffineTransform();
            bulletT.translate(b.getx(), b.gety());
            double angle = Math.toDegrees(Math.atan2(b.getY(), b.getX()));
            bulletT.rotate(Math.toRadians(angle), this.bullet_Img.getWidth(null) / 2,
                this.bullet_Img.getHeight(null) / 2);
            g.drawImage(this.bullet_Img, bulletT, this);
          } else {
            g.drawImage(this.pop_Img2, b.getx(), b.gety(), this);
            this.bulletLL.remove(i);
          }
        }
      }
      System.out.println("painting robo");
      if ((this.roboHealth > 0) && (this.baseHealth > 0)) {
        this.roboRec = new Rectangle(roboX, roboY, this.robo_Img.getWidth(this), this.robo_Img.getHeight(this));
        int x = this.X;
        int y = this.Y;
        if (!this.roboRec.intersects(this.baseRec)) {
          roboX += x;
          roboY += y;
        }
        if (this.roboRec.intersects(this.baseRec)) {
          roboX -= x;
          roboY -= y;
        }
        if (roboX < 0) {
          roboX = 0;
        }
        if (roboY < 0) {
          roboY = 0;
        }
        if (roboX > 600) {
          roboX = 600;
        }
        if (roboY > 440) {
          roboY = 440;
        }
        AffineTransform roboT = new AffineTransform();
        roboT.translate(roboX, roboY);
        double angle = Math.toDegrees(Math.atan2(this.mouseY - roboY, this.mouseX - roboX));
        roboT.rotate(Math.toRadians(angle), this.robo_Img.getWidth(null) / 2, this.robo_Img.getHeight(null) / 2);
        g.drawImage(this.robo_Img, roboT, this);
      } else if ((this.roboHealth <= 0) || (this.baseHealth <= 0)) {
        System.out.println("GAME OVER");
        this.running = false;
        Screen = 5;
      }
      g.drawImage(this.roboHealth_Img, 470, 40, 30, 30, this);

      Rectangle roboHealthRec = new Rectangle(519, 47, 101, 11);
      Rectangle roboHealthRecNow = new Rectangle(520, 48, 10 * this.roboHealth, 10);
      g.setColor(Color.black);
      g.draw(roboHealthRec);
      g.setColor(Color.red);
      g.fill(roboHealthRecNow);

      Rectangle baseHealthRec = new Rectangle(519, 88, 101, 11);
      Rectangle baseHealthRecNow = new Rectangle(520, 89, 5 * this.baseHealth, 10);
      g.setColor(Color.black);
      g.setFont(new Font("TimesRoman", 0, 25));
      g.drawString("Base:", 460, 100);
      g.draw(baseHealthRec);
      g.setColor(Color.red);
      g.fill(baseHealthRecNow);

      g.drawImage(this.Coin_Img, 468, 112, this);
      String score = String.valueOf(this.SScore);
      g.setColor(Color.black);
      g.drawString(score, 525, 135);
    } else if (Screen == 4) {
      System.out.println("upgrade menu");
      g.setFont(new Font("TimesRoman", 0, 25));
      g.drawImage(this.Coin_Img, 468, 52, this);
      String score = String.valueOf(this.SScore);
      g.setColor(Color.black);
      g.drawString(score, 525, 75);
      g.drawString("Upgrade Menu", 240, 70);
      g.drawLine(240, 78, 392, 78);
      g.drawLine(240, 79, 392, 79);
      if (this.SScore <= 0) {
        g.setColor(Color.red);
        g.drawString("You Have Insufficient Coins", 180, 200);
      } else {
        g.drawImage(this.roboHealth_Img, 180, 110, 60, 60, this);
        g.drawImage(this.baseupg_Img, 280, 110, 60, 60, this);
        g.drawImage(this.bulletupg_Img, 387, 115, 60, 60, this);
        g.drawImage(this.roboupg_Img, 180, 210, 80, 80, this);
        g.drawImage(this.rocketupg_Img, 280, 210, 60, 60, this);
        g.drawImage(this.turret_Img, 380, 210, 60, 60, this);
        if ((this.mouseX > 380) && (this.mouseX < 440) && (this.mouseY > 210) && (this.mouseY < 270)) {
          if (this.US6 == 0) {
            g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
            g.drawImage(this.Coin_Img, 510, 260, this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 12));
            g.drawString("Base Starts Fires", 500, 140);
            g.drawString("at Enemies.", 500, 160);
            g.drawString("300", 540, 280);
          } else if (this.US6 == 1) {
            g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
            g.drawImage(this.Coin_Img, 510, 260, this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 12));
            g.drawString("Base Fires", 500, 140);
            g.drawString("Faster.", 500, 160);
            g.drawString("300", 540, 280);
          } else if (this.US6 == 2) {
            g.drawImage(this.close_Img, 380, 210, 60, 60, this);
            g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 12));
            g.drawString("Upgrade Over", 500, 140);
          }
        }
        if ((this.mouseX > 180) && (this.mouseX < 240) && (this.mouseY > 210) && (this.mouseY < 270)) {
          if (this.US4 == 0) {
            g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
            g.drawImage(this.Coin_Img, 510, 260, this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 12));
            g.drawString("Robo moves Faster.", 500, 140);
            g.drawString("300", 540, 280);
          } else if (this.US4 == 1) {
            g.drawImage(this.close_Img, 180, 210, 80, 80, this);
            g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 12));
            g.drawString("Upgrade Over", 500, 140);
          }
        }
        if ((this.mouseX > 280) && (this.mouseX < 340) && (this.mouseY > 210) && (this.mouseY < 270)) {
          g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
          g.drawImage(this.Coin_Img, 510, 260, this);
          g.setColor(Color.black);
          g.setFont(new Font("Arial", 0, 12));
          g.drawString("Get 3 Rockets.", 500, 140);
          g.setColor(Color.red);
          g.drawString("(Shoot Rockets by", 500, 160);
          g.drawString("Right Click.)", 500, 180);
          g.setColor(Color.black);
          g.drawString("300", 540, 280);
        }
        if ((this.mouseX > 380) && (this.mouseX < 440) && (this.mouseY > 110) && (this.mouseY < 170)) {
          g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
          g.drawImage(this.Coin_Img, 510, 260, this);
          g.setColor(Color.black);
          g.setFont(new Font("Arial", 0, 12));
          switch (this.US3) {
          case 0:
            g.drawString("Robo fires Faster.", 500, 140);
            g.drawString("100", 540, 280);
            break;
          case 1:
            g.drawString("Robo fires Faster.", 500, 140);
            g.drawString("200", 540, 280);
            break;
          case 2:
            g.drawString("Robo fires Faster.", 500, 140);
            g.drawString("300", 540, 280);
            break;
          case 3:
            g.drawString("Robo fires Faster.", 500, 140);
            g.drawString("400", 540, 280);
            break;
          case 4:
            g.drawString("Robo fires Faster.", 500, 140);
            g.drawString("400", 540, 280);
            break;
          default:
            g.drawImage(this.close_Img, 387, 115, 60, 60, this);
            g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", 0, 12));
            g.drawString("Upgrade Over", 500, 140);
          }
        }
        if ((this.mouseX > 280) && (this.mouseX < 340) && (this.mouseY > 110) && (this.mouseY < 170)) {
          g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
          g.drawImage(this.Coin_Img, 510, 260, this);
          g.setColor(Color.black);
          g.setFont(new Font("Arial", 0, 12));
          g.drawString("Base regains Health.", 493, 140);
          g.drawString("200", 540, 280);
        }
        if ((this.mouseX > 180) && (this.mouseX < 240) && (this.mouseY > 110) && (this.mouseY < 170)) {
          g.drawImage(this.dialogue_Img, 490, 110, 120, 180, this);
          g.drawImage(this.Coin_Img, 510, 260, this);
          g.setColor(Color.black);
          g.setFont(new Font("Arial", 0, 12));
          g.drawString("Robo regains Health.", 493, 140);
          g.drawString("100", 540, 280);
        }
        if ((this.clickX > 380) && (this.clickX < 440) && (this.clickY > 210) && (this.clickY < 270)) {
          if (this.US6 == 0) {
            if (this.SScore >= 300) {
              this.SScore -= 300;
              this.US6 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
          } else if (this.US6 == 1) {
            if (this.SScore >= 300) {
              this.SScore -= 300;
              this.US6 += 1;
              tf -= 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
          }
        }
        if ((this.clickX > 180) && (this.clickX < 240) && (this.clickY > 210) && (this.clickY < 270)) {
          if (this.US4 == 0) {
            if (this.SScore >= 300) {
              this.SScore -= 300;
              this.Speed = 4;
              this.US4 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
          }
        }
        if ((this.clickX > 280) && (this.clickX < 340) && (this.clickY > 210) && (this.clickY < 270)) {
          if (this.SScore >= 300) {
            this.SScore -= 300;
            this.US5 += 3;
          } else {
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", 0, 20));
            g.drawString("You Have Insufficient Coins", 210, 330);
            this.clickX = 0;
            this.clickY = 0;
          }
        }
        if ((this.clickX > 380) && (this.clickX < 440) && (this.clickY > 110) && (this.clickY < 170)) {
          switch (this.US3) {
          case 0:
            if (this.SScore >= 100) {
              this.SScore -= 100;
              new bullet(5);
              this.clickX = 0;
              this.clickY = 0;
              this.US3 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
            break;
          case 1:
            if (this.SScore >= 200) {
              this.SScore -= 200;
              this.firerate -= 1;
              this.clickX = 0;
              this.clickY = 0;
              this.US3 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
          case 2:
            if (this.SScore >= 300) {
              this.SScore -= 300;
              new bullet(5);
              this.clickX = 0;
              this.clickY = 0;
              this.US3 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
            break;
          case 3:
            if (this.SScore >= 400) {
              this.SScore -= 400;
              this.firerate -= 1;
              this.clickX = 0;
              this.clickY = 0;
              this.US3 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
            break;
          case 4:
            if (this.SScore >= 400) {
              this.SScore -= 400;
              new bullet(5);
              this.firerate -= 1;
              this.clickX = 0;
              this.clickY = 0;
              this.US3 += 1;
            } else {
              g.setColor(Color.red);
              g.setFont(new Font("TimesRoman", 0, 20));
              g.drawString("You Have Insufficient Coins", 210, 330);
              this.clickX = 0;
              this.clickY = 0;
            }
            break;
          }
        }
        if ((this.clickX > 280) && (this.clickX < 340) && (this.clickY > 110) && (this.clickY < 170)) {
          if (this.SScore >= 200) {
            this.SScore -= 200;
            this.baseHealth = 20;
            this.clickX = 0;
            this.clickY = 0;
          } else {
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", 0, 20));
            g.drawString("You Have Insufficient Coins", 210, 330);
            this.clickX = 0;
            this.clickY = 0;
          }
        }
        if ((this.clickX > 180) && (this.clickX < 240) && (this.clickY > 110) && (this.clickY < 170)) {
          if (this.SScore >= 100) {
            this.SScore -= 100;
            this.roboHealth = 10;
            this.clickX = 0;
            this.clickY = 0;
          } else {
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", 0, 20));
            g.drawString("You Have Insufficient Coins", 210, 330);
            this.clickX = 0;
            this.clickY = 0;
          }
        }
      }
      g.setColor(Color.black);
      Rectangle ok = new Rectangle(255, 355, 140, 30);
      g.draw(ok);
      g.drawRect(254, 354, 142, 32);
      g.setFont(new Font("TimesRoman", 0, 20));
      g.drawString("Return To Game", 260, 375);
      if ((this.clickX > ok.getX()) && (this.clickX < ok.getX() + ok.getWidth()) && (this.clickY > ok.getY())
          && (this.clickY < ok.getY() + ok.getHeight())) {
        Screen = 3;
        this.ETh.resume();
        this.clickX = 0;
        this.clickY = 0;
      }
    } else if (Screen == 5) {
      this.Trans = 4;
      this.running = false;
      g.drawImage(this.gameover_Img, 0, 0, this);
      g.setColor(Color.WHITE);
      g.setFont(new Font("TimesRoman", 3, 80));
      g.drawString("Score " + this.TScore, 170, 300);
    } else if (Screen == 1) {
      g.setFont(new Font("TimesRoman", 3, 80));
      g.setColor(Color.BLACK);
      g.drawString("Defend Base", 120, 180);
    } else if (Screen == 2) {
      System.out.println("painting about");
      g.setFont(new Font("TimesRoman", 3, 80));
      g.setColor(Color.BLACK);
      g.drawString("Defend Base", 120, 120);
    }
  }

  public void paint(Graphics g) {
    System.out.println("paintU");
    if (this.db_img == null) {
      this.db_img = createImage(getWidth(), getHeight());
      this.db_g = this.db_img.getGraphics();
    }
    this.db_g.setColor(getBackground());
    this.db_g.fillRect(0, 0, getWidth(), getHeight());
    this.db_g.setColor(getForeground());
    paintcomponent(this.db_g);
    Thread.currentThread().setPriority(10);
    long overtime = 0L;
    long period = 150000L;
    int delays = 0;
    int totaldelays = 10;
    switch (this.Trans) {
    case 0:
      g.drawImage(this.db_img, 0, 0, this);
      break;
    case 1:
      int left = 64896;
      while (left != 0) {
        left++;
        long beforetime = System.nanoTime();

        g.drawImage(this.db_img, left, 0, this);
        long aftertime = System.nanoTime();
        long difftime = aftertime - beforetime;
        long sleeptime = period - difftime - overtime;
        if ((sleeptime < period) && (sleeptime > 0L)) {
          try {
            Thread.sleep(sleeptime / 100000L);
            overtime = 0L;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else if (difftime > period) {
          overtime = difftime - period;
        } else {
          delays++;
          if (delays >= 10) {
            Thread.currentThread();
            Thread.yield();
            delays = 0;
            overtime = 0L;
          } else {
            overtime = 0L;
          }
        }
      }
      this.Trans = 0;
      break;
    case 2:
      int right = 640;
      while (right != 0) {
        right--;
        long beforetime = System.nanoTime();

        g.drawImage(this.db_img, right, 0, this);
        long aftertime = System.nanoTime();
        long difftime = aftertime - beforetime;
        long sleeptime = period - difftime - overtime;
        if ((sleeptime < period) && (sleeptime > 0L)) {
          try {
            Thread.sleep(sleeptime / 100000L);
            overtime = 0L;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else if (difftime > period) {
          overtime = difftime - period;
        } else {
          delays++;
          if (delays >= 10) {
            Thread.currentThread();
            Thread.yield();
            delays = 0;
            overtime = 0L;
          } else {
            overtime = 0L;
          }
        }
      }
      this.Trans = 0;
      break;
    case 3:
      int top = 65056;
      while (top != 0) {
        top++;
        long beforetime = System.nanoTime();

        g.drawImage(this.db_img, 0, top, this);
        long aftertime = System.nanoTime();
        long difftime = aftertime - beforetime;
        long sleeptime = period - difftime - overtime;
        if ((sleeptime < period) && (sleeptime > 0L)) {
          try {
            Thread.sleep(sleeptime / 100000L);
            overtime = 0L;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else if (difftime > period) {
          overtime = difftime - period;
        } else {
          delays++;
          if (delays >= 10) {
            Thread.currentThread();
            Thread.yield();
            delays = 0;
            overtime = 0L;
          } else {
            overtime = 0L;
          }
        }
      }
      this.Trans = 0;
      break;
    case 4:
      int temp = 20;
      int x = 200;
      int y = 200;
      int width = 240;
      int height = 80;
      period = 2000000L;
      while (temp != 0) {
        temp--;
        long beforetime = System.nanoTime();

        x -= 10;
        y -= 10;
        width += 20;
        height += 20;
        g.drawImage(this.db_img, x, y, width, height, this);
        long aftertime = System.nanoTime();
        long difftime = aftertime - beforetime;
        long sleeptime = period - difftime - overtime;
        if ((sleeptime < period) && (sleeptime > 0L)) {
          try {
            Thread.sleep(sleeptime / 100000L);
            overtime = 0L;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else if (difftime > period) {
          overtime = difftime - period;
        } else {
          delays++;
          if (delays >= 10) {
            Thread.currentThread();
            Thread.yield();
            delays = 0;
            overtime = 0L;
          } else {
            overtime = 0L;
          }
        }
      }
      this.Trans = 0;
      break;
    default:
      g.drawImage(this.db_img, 0, 0, this);
    }
  }

  private void turretupdate() {
    this.enemyLL = enemy.getEnemyLL();

    this.enemyLL = enemy.getEnemyLL();
    enemy minenemy = null;
    enemy et = (enemy) this.enemyLL.get(0);
    minenemy = et;
    int TurretX = this.baseX + 20;
    int TurretY = this.baseY + 20;
    int db = (int) Math.sqrt(Math.pow(TurretX - et.getX(), 2.0D) + Math.pow(TurretY - et.getY(), 2.0D));
    for (int j = 1; j < this.enemyLL.size(); j++) {
      et = (enemy) this.enemyLL.get(j);
      int dn = (int) Math.sqrt(Math.pow(TurretX - et.getX(), 2.0D) + Math.pow(TurretY - et.getY(), 2.0D));
      if (db > dn) {
        db = dn;

        minenemy = et;
      }
    }
    if ((minenemy.isVisible()) && (db <= 250)) {
      turret(TurretX, TurretY, minenemy.getX(), minenemy.getY(), tf);
    }
  }

  private void turret(int tx, int ty, int Tx, int Ty, int tf) {
    this.turretTimeN = (System.currentTimeMillis() - this.start);
    if (this.turretTimeN - this.turretTimeS > tf * 500) {
      bullet b = new bullet(tx, ty, Tx - tx, Ty - ty, 4);
      this.bulletLL.add(b);
      this.turretTimeS = this.turretTimeN;
    }
  }

  private void fire(int t) {
    this.bulletTimeN = (System.currentTimeMillis() - this.start);
    System.out.println("nooooooooo-------------------------------" + (this.bulletTimeN - this.bulletTimeS) + ","
        + this.before + "," + this.now);
    if ((this.bulletTimeN - this.bulletTimeS > t * 1000) && ((this.now != this.before) || (this.enemyLL.size() == 1))) {
      this.bulletTimeS = this.bulletTimeN;
      bullet b = new bullet(this.ex, this.ey, roboX - this.ex, roboY - this.ey, 3);
      this.bulletLL.add(b);
      this.before = this.now;
    }
  }

  private void fireL() {
    if (this.fire) {
      System.out.println("fireL");
      bullet b = new bullet(roboX, roboY, this.mouseX - roboX, this.mouseY - roboY, 1);
      this.bulletLL.add(b);
    }
  }

  private void fireR() {
    System.out.println("fireR");
    if (this.US5 > 0) {
      bullet b = new bullet(roboX, roboY, this.mouseX - roboX, this.mouseY - roboY, 2);
      this.bulletLL.add(b);
      this.US5 -= 1;
    }
  }

  private void loadimages() {
    this.bg_Img = new ImageIcon("resourses/images/background.jpg").getImage();
    this.base_Img = new ImageIcon("resourses/images/base.png").getImage();
    this.baseupg_Img = new ImageIcon("resourses/images/baseupg.png").getImage();
    this.robo_Img = new ImageIcon("resourses/images/robo.png").getImage();
    this.bullet_Img = new ImageIcon("resourses/images/bullet.png").getImage();
    this.ebullet_Img = new ImageIcon("resourses/images/ebullet.png").getImage();
    this.rocket_Img = new ImageIcon("resourses/images/rocket.png").getImage();
    this.rocketupg_Img = new ImageIcon("resourses/images/rocketupg.png").getImage();
    this.bulletupg_Img = new ImageIcon("resourses/images/bulletupg.jpg").getImage();
    this.turret_Img = new ImageIcon("resourses/images/turret.png").getImage();
    this.roboupg_Img = new ImageIcon("resourses/images/roboupg.png").getImage();
    this.enemy_Img1 = new ImageIcon("resourses/images/enemy1.png").getImage();
    this.enemy_Img2 = new ImageIcon("resourses/images/enemy2.png").getImage();
    this.enemy_Img3 = new ImageIcon("resourses/images/enemy3.png").getImage();
    this.enemy_Img4 = new ImageIcon("resourses/images/enemy4.png").getImage();
    this.enemy_Img5 = new ImageIcon("resourses/images/enemy5.png").getImage();
    this.pop_Img1 = new ImageIcon("resourses/images/pop1.png").getImage();
    this.pop_Img2 = new ImageIcon("resourses/images/pop2.png").getImage();
    this.gameover_Img = new ImageIcon("resourses/images/gameover.png").getImage();
    this.roboHealth_Img = new ImageIcon("resourses/images/heart.png").getImage();
    this.Coin_Img = new ImageIcon("resourses/images/coin.png").getImage();
    this.dialogue_Img = new ImageIcon("resourses/images/dialogue.png").getImage();
    this.close_Img = new ImageIcon("resourses/images/close.png").getImage();
  }

  public void Thsleep(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void actionPerformed(ActionEvent e) {
    remove(this.playgame);
    remove(this.about);
    remove(this.back);
    remove(this.exit);
    if (e.getSource() == this.playgame) {
      playgame();
    } else if (e.getSource() == this.about) {
      about();
    } else if (e.getSource() == this.back) {
      mainmenu();
    } else if (e.getSource() == this.exit) {
      exit();
    }
  }

  private void exit() {
    System.out.print("in exit");
    System.exit(0);
  }

  private class DisPatcher implements KeyEventDispatcher {
    private DisPatcher() {
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
      if (e.getID() == 401) {
        switch (e.getKeyCode()) {
        case 87:
          Main.this.Y = (-Main.this.Speed);
          break;
        case 83:
          Main.this.Y = Main.this.Speed;
          break;
        case 65:
          Main.this.X = (-Main.this.Speed);
          break;
        case 68:
          Main.this.X = Main.this.Speed;
          break;
        case 32:
          Main.this.fire = true;
          break;
        case 27:
          switch (Main.Screen) {
          case 1:
            Main.this.exit();
            break;
          case 2:
            Main.this.mainmenu();
            break;
          case 3:
            Main.this.ETh.suspend();
            Main.Screen = 4;
            break;
          case 4:
            Main.Screen = 5;
            break;
          }
          break;
        }
      } else if (e.getID() == 402) {
        switch (e.getKeyCode()) {
        case 87:
          Main.this.Y = 0;
          break;
        case 83:
          Main.this.Y = 0;
          break;
        case 65:
          Main.this.X = 0;
          break;
        case 68:
          Main.this.X = 0;
          break;
        case 32:
          Main.this.fire = false;
          break;
        }
      }
      return false;
    }
  }

  public void mouseClicked(MouseEvent e) {
    this.clickX = e.getX();
    this.clickY = e.getY();
    if (Screen == 3) {
      if ((this.clickX > this.baseX) && (this.clickX < this.baseX + this.base_Img.getWidth(this))
          && (this.clickY > this.baseY) && (this.clickY < this.baseY + this.base_Img.getHeight(this))) {
        System.out.println("clicked on base");
        Screen = 4;
        this.clickX = 0;
        this.clickY = 0;
        this.ETh.suspend();
      } else if (e.getButton() == 3) {
        fireR();
      }
    }
  }

  public void mousePressed(MouseEvent e) {
    if (e.getButton() == 1) {
      this.fire = true;
    }
  }

  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == 1) {
      this.fire = false;
    }
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseDragged(MouseEvent e) {
    this.mouseX = e.getX();
    this.mouseY = e.getY();
  }

  public void mouseMoved(MouseEvent e) {
    this.mouseX = e.getX();
    this.mouseY = e.getY();
  }

  public int getRoboX() {
    return roboX;
  }

  public int getRoboY() {
    return roboY;
  }

  public int getMouseX() {
    return this.mouseX;
  }

  public int getMouseY() {
    return this.mouseY;
  }

  public Image getEnemy() {
    return this.enemy_Img1;
  }

  public LinkedList<enemy> getEnemyLL() {
    return this.enemyLL;
  }

  public void setEnemyLL(LinkedList<enemy> enemyLL) {
    this.enemyLL = enemyLL;
  }

  public Rectangle getEnemyRec() {
    return this.enemyRec;
  }

  public int getScreen() {
    return Screen;
  }
}
