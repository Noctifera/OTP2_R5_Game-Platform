package modal;

public class Score {
	int score;

	public Score(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void diamond(){
		score = score +10;

	}
	public void bigDiamond(){
		score = score + 20;
	}
	public void ghost(){
		score = score +100;
	}


}
