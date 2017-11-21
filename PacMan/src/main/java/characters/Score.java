package characters;

public class Score {
	int score;

	public Score(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	public void dot(){
		score = score +10;

	}
	public void LargeDot(){
		score = score + 20;
	}
	public void ghost(){
		score = score +100;
	}

	public void setScore(int score) {
		this.score = score;
	}


}
