package shootingspaceship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class TurnBasedGame extends JPanel implements ActionListener {
    private Timer timer;
    private int turn = 0;
    private BattleCharacter player;
    private ArrayList<BattleCharacter> enemies;
    private boolean gameOver = false;
    private String gameMessage = "";
    private JButton skillButton;

    public TurnBasedGame() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
        setBackground(Color.BLACK);

        player = new BattleCharacter("타이탄 우주선", 150, 20, 10, "우주선");
        enemies = new ArrayList<>();
        enemies.add(new BattleCharacter("이그니스", 80, 25, 5, "화염 행성"));
        enemies.add(new BattleCharacter("프로스트", 100, 18, 8, "얼음 행성"));
        enemies.add(new BattleCharacter("베놈", 90, 22, 6, "독성 행성"));

        skillButton = new JButton("스킬 사용");
        skillButton.setBounds(600, 500, 150, 40);
        skillButton.addActionListener(e -> {
            if (!enemies.isEmpty() && !gameOver) {
                BattleCharacter target = enemies.get(0);
                int skillDamage = player.attack * 2;
                target.takeDamage(skillDamage);
                gameMessage = player.name + "의 스킬 발동! → " + target.name + "에게 " + skillDamage + " 데미지";
                if (!target.isAlive()) {
                    enemies.remove(target);
                    gameMessage += " (파괴됨)";
                }
                turn++;
                repaint();
            }
        });
        add(skillButton);

        timer = new Timer(3000, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        if (turn % 2 == 1) {
            if (!enemies.isEmpty()) {
                BattleCharacter attacker = enemies.get(new Random().nextInt(enemies.size()));
                player.takeDamage(attacker.dealDamage());
                gameMessage = attacker.name + "의 공격! → " + player.name + "이(가) 피해를 입음";
                if (!player.isAlive()) {
                    gameOver = true;
                    gameMessage = "패배! 우주선이 파괴되었습니다.";
                }
            }
        }

        if (enemies.isEmpty() && !gameOver) {
            gameOver = true;
            gameMessage = "승리! 모든 행성을 파괴했습니다.";
        }

        turn++;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("턴: " + turn, 20, 20);
        g.drawString("우주선 체력: " + player.hp, 20, 50);

        for (int i = 0; i < enemies.size(); i++) {
            BattleCharacter enemy = enemies.get(i);
            g.setColor(Color.ORANGE);
            g.drawString(enemy.name + " 체력: " + enemy.hp, 20, 100 + i * 30);
        }

        g.setColor(Color.YELLOW);
        g.drawString(gameMessage, 20, 500);

        if (gameOver) {
            g.setColor(Color.CYAN);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("게임 종료", 300, 300);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("스킬 버튼 있는 턴제 게임");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TurnBasedGame game = new TurnBasedGame();
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}

class BattleCharacter {
    String name;
    int hp;
    int attack;
    int defense;
    String type;

    public BattleCharacter(String name, int hp, int attack, int defense, String type) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
    }

    public int dealDamage() {
        return attack;
    }

    public void takeDamage(int dmg) {
        int actual = dmg - defense;
        if (actual < 1) actual = 1;
        hp -= actual;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
