package entity;

import org.springframework.stereotype.Repository;
@Repository("entity")
public class entity {
	
	private String answer;
	
	private String question;
	
	private String classify;
	
	private int id;
	
	private String url;

	

	@Override
	public String toString() {
		return "entity [answer=" + answer + ", question=" + question + ", classify=" + classify + ", id=" + id
				+ ", url=" + url + "]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
}