package test;

public class Character {
    String roleSet;
    int hp;
    int attackPower;
    int defensePower;
    int speed;

    public Character() {}
    public Character(String roleSet, int hp, int attackPower, int defensePower, int speed) {
        this.roleSet = roleSet;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.speed = speed;
    }
}

class Skill extends Character {
    String name;
    String team;
    public Skill() {}
    public Skill(String name, String team) {
        this.name = name;
        this.team = team;
    }
}

class playerSkill extends Skill {}

class enemySkill extends Skill {}

class attackerSkill extends playerSkill {
    public void attackerSkill1(Character target) {
        System.out.println("딜러 스킬1 사용");
        int skillAttackPower = this.attackPower;
        int finalDamage = skillAttackPower - target.defensePower;
        target.hp -= Math.max(finalDamage, 1);
        System.out.println(target.roleSet + "의 남은 체력은 " + target.hp);
    }
    public void attackerSkill2() {}
}

class tankerSkill extends playerSkill {
    public void tankerSkill1() {}
    public void tankerSkill2() {}
}

class healerSkill extends playerSkill {
    public void healerSkill1() {}
    public void healerSkill2() {}
}

class supporterSkill extends playerSkill {
    public void supporterSkill1() {}
    public void supporterSkill2() {}
}

class commonEnemySkill extends enemySkill {
    public void commonEnemySkill1() {}
    public void commonEnemySkill2() {}
}
