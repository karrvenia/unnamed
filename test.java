package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;


class Character{
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

class Skill extends Character{  //스킬 클래스
	String name;
	String team;
	public Skill() {}
	public Skill(String name, String team) {
		this.name = name;
		this.team = team;
	}
}

class playerSkill extends Skill{  //아군 스킬 클래스
	//int hp;
	//int attackPower;
	//int defensePower;
	//int speed;
	//public playerSkill() {}
	//public void defineStat(int hp, int attackPower, int defensePower, int speed) {
		//this.hp = hp;
		//this.attackPower = attackPower;
		//this.defensePower = defensePower;
		//this.speed = speed;
	//}
}

class enemySkill extends Skill{  //적군 스킬 클래스
	public enemySkill() {}
}

class attackerSkill extends playerSkill{  //딜러 스킬 클래스
	public void attackerSkill1(Character target){
		System.out.println("딜러 스킬1 사용");
		int skillAttackPower = this.attackPower;
		int finalDamage = skillAttackPower - target.defensePower;
		if(finalDamage > 0) {
			target.hp = target.hp - finalDamage;
		}
		else {
			target.hp = target.hp - 1;
		}
		System.out.println(target.roleSet + "의 남은 체력은" + target.hp);
	}
	public void attackerSkill2(){}
}

class tankerSkill extends playerSkill{  //탱커 스킬 클래스
	public void tankerSkill1(){}
	public void tankerSkill2(){}
}

class healerSkill extends playerSkill{  //힐러 스킬 클래스
	public void healerSkill1(){}
	public void healerSkill2(){}
}

class supporterSkill extends playerSkill{  //서포터 스킬 클래스
	public void supporterSkill1(){}
	public void supporterSkill2(){}
}

class commonEnemySkill extends enemySkill{  //일반 적 스킬 클래스
	public void commonEnemySkill1() {}
	public void commomEnemySkill2() {}
}

class middleEnemySkill extends enemySkill{  //중간 적 스킬 클래스
	public void middleEnemySkill1() {}
	public void middleEnemySkill2() {}
}

class eliteEnemySkill extends enemySkill{  //엘리트 적 스킬 클래스
	public void eliteEnemySkill1() {}
	public void eliteEnemySkill2() {}
}

class Stat{
	//int hp;
	//int attackPower;
	//int defensePower;
	//int speed;
	//public void defineStat(int hp, int attackPower, int defensePower, int speed) {
		//this.hp = hp;
		//this.attackPower = attackPower;
		//this.defensePower = defensePower;
		//this.speed = speed;
	//}
}

class damageSystem{
	public static int damage(Character a, Character b) {
		int finalDamage = a.attackPower - b.defensePower;
		//b.hp = b.hp - finalDemage;
		if(finalDamage > 0) {
			b.hp = b.hp - finalDamage;
			return b.hp;
		}
		else {
			System.out.println("음수의 데미지가 발생해 데미지가 1로 들어갑니다");  
			b.hp = b.hp - 1;
			return b.hp;
		}
	}
}

public class test {                        //메인
	public static void main(String[] args) {
		System.out.println("스킬 시스템");
		Character attacker = new Character("attacker", 2000, 700, 100, 50);
		Character tanker = new Character("tanker", 3000, 300, 200, 30);
		Character healer = new Character("healer", 1500, 150, 50, 34);
		Character supporter = new Character("supporter", 2400, 250, 150, 34);
		System.out.println("attack의 체력 " + attacker.hp);
		System.out.println("tanker의 공격력 " + tanker.attackPower);
		System.out.println("healer의 방어력 " + healer.defensePower);
		System.out.println("서폿의 속도 " + supporter.speed);
		damageSystem.damage(healer, supporter);
		System.out.println("남은 체력: " + supporter.hp);
	}
}

