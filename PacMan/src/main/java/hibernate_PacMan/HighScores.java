package hibernate_PacMan;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "HighScores")
public class HighScores implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int Id;

	@Column(name = "submission_date")
	private String submission_date;

	@Column(name = "Score")
	private int score;

	@Column(name = "playerName")
	private String playername;

	@ManyToOne
	@JoinColumn(name = "mapName", nullable = false)
	private MapsTable mapName;

	public HighScores(String submission_date, int score, String playername, MapsTable mapName) {
		this.submission_date = submission_date;
		this.score = score;
		this.playername = playername;
		this.mapName = mapName;
	}

	public HighScores() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(String submission_date) {
		this.submission_date = submission_date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public MapsTable getMapName() {
		return mapName;
	}

	public void setMapName(MapsTable mapName) {
		this.mapName = mapName;
	}

	public String toString() {
		return "HighScores [Id=" + Id + ", submission_date=" + submission_date + ", score=" + score + ", playername=" + playername + ", mapName=" + mapName + "]";
	}

}
