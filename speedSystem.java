package test;

import java.util.*;

class characterAct extends Character{
	String roleSet;
	int speed;
	int actionGauge = 100;
	boolean isEnemy;
	boolean isAlive = true;
	public void act() {
		System.out.println(roleSet + "의 턴 (속도: " + speed + ")");
			actionGauge = 0;
	}
}

public class speedSystem{
	static List<Character> Characters = new ArrayList<>();
	static int totalActionsThisRound = 0;
	static int currentRound = 1;
	static final int MAX_ACTIONS_PER_ROUND = 10;
	static final int MAX_ROUNDS = 10;
	
	public static void main(String[] args) {
		//캐릭터 생성
		Character attacker = new Character("attacker", 2000, 700, 100, 50, false);
		Character tanker = new Character("tanker", 3000, 300, 200, 30, false);
		Character healer = new Character("healer", 1500, 150, 50, 34, false);
		Character supporter = new Character("supporter", 2400, 250, 150, 34, false);
		
		Characters.add(attacker);
		Characters.add(tanker);
		Characters.add(healer);
		Characters.add(supporter);
		
		while(true) {
			Character next = getNextCharacter();
			if(next == null) {
				break;
			}
			next.act();
			totalActionsThisRound++;
			
			//게이지 증가
			for(Character u : Characters) {
				if(u != next && u.isAlive) {
					u.actionGauge += u.speed;
				}
			}
			
			checkVictoryConditions();
			
			if(totalActionsThisRound >= MAX_ACTIONS_PER_ROUND) {
				currentRound++;
				totalActionsThisRound = 0;
				System.out.println("--" + currentRound + " 라운드 시작 --");
			}
			
			if(currentRound > MAX_ROUNDS) {
				System.out.println("패배: 라운드 초과");
				break;
			}
		}
	}
	
	static Character getNextCharacter() {
		return Characters.stream().filter(u -> u.isAlive && u.actionGauge >= 100).sorted((a, b) -> {
			return 0;
		})
		.findFirst()
		.orElse(null);
	}
	
	static void checkVictoryConditions() {
		boolean allEnemiesDead = Characters.stream().allMatch(u -> !u.isEnemy || !u.isAlive);
		boolean allAlliesDead = Characters.stream().noneMatch(u -> !u.isEnemy && u.isAlive);
		
		if(allEnemiesDead) {
			System.out.println("승리: 적 전멸");
		}
		else if(allAlliesDead) {
			System.out.println("패배: 아군 전멸");
			System.exit(0);
		}
	}
}