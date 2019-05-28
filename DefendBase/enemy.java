package DefendBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

public class enemy implements Runnable {
  private int x;
  private int y;
  private int type;
  private int Health;
  private int Speed;
  private int Score;
  private Main m = new Main(1);
  private boolean visible = true;
  private static LinkedList<enemy> enemyLL;
  private enemy eu;

  public void enemywave(int waveno) {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("resourses/other/enemy.jc"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("FileNotFoundException");
    }
    String line = null;
    try {
      int lineno = 1;
      while ((line = br.readLine()) != null) {
        if (lineno != waveno) {
          lineno++;
        } else {
          String[] parts = line.split("\\s");
          for (int i = 0; i < parts.length; i++) {
            String[] part = parts[i].split("\\,");
            String[] temp = new String[3];
            for (int j = 0; j < 3; j++) {
              temp[j] = part[j];
            }
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);
            enemy e = new enemy();
            e.createenemy(a, b, c);
            enemyLL.add(e);
          }
          break;
        }
      }
    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

  private void createenemy(int x, int y, int type) {
    this.x = x;
    this.y = y;
    this.type = type;
    switch (type) {
    case 1:
      this.Health = 4;
      this.Speed = 1;
      this.Score = 10;
      break;
    case 2:
      this.Health = 6;
      this.Speed = 1;
      this.Score = 20;
      break;
    case 3:
      this.Health = 10;
      this.Speed = 2;
      this.Score = 30;
      break;
    case 4:
      this.Health = 12;
      this.Speed = 2;
      this.Score = 40;
      break;
    case 5:
      this.Health = 8;
      this.Speed = 2;
      this.Score = 50;
      break;
    }
  }

  public void run() {
    enemyLL = new LinkedList();
    this.eu = new enemy();

    long overtime = 0L;
    long period = 10000000L;
    int delays = 0;
    int totaldelays = 10;
    int Screen;
    do {
      long beforetime = System.nanoTime();
      dogameupdate();
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
      Screen = this.m.getScreen();
      System.out.println("enemyrun");
    } while (Screen == 3);
    System.out.println("enemyrun exeting-------------------");
  }

  private void dogameupdate() {
    System.out.println("enemydgm");
    int size = getEnemyLL().size();
    for (int i = 0; i < size; i++) {
      enemy e = (enemy) enemyLL.get(i);

      int X = e.getX();
      int Y = e.getY();
      if (X < this.m.getRoboX()) {
        X += e.getSpeed();
      } else if (X > this.m.getRoboX()) {
        X -= e.getSpeed();
      }
      if (Y < this.m.getRoboY()) {
        Y += e.getSpeed();
      } else if (Y > this.m.getRoboY()) {
        Y -= e.getSpeed();
      }
      e.setX(X);
      e.setY(Y);
    }
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getType() {
    return this.type;
  }

  public static LinkedList<enemy> getEnemyLL() {
    return enemyLL;
  }

  public boolean isVisible() {
    return this.visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public enemy getEu() {
    return this.eu;
  }

  public int getEnemyHealth() {
    return this.Health;
  }

  public void setEnemyHealth(int enemyHealth) {
    this.Health = enemyHealth;
  }

  public int getSpeed() {
    return this.Speed;
  }

  public void setSpeed(int speed) {
    this.Speed = speed;
  }

  public int getScore() {
    return this.Score;
  }

  public void setScore(int Score) {
    this.Score = Score;
  }
}
