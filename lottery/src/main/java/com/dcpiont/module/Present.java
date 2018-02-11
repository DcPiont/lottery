package com.dcpiont.module;

/**
 * Created by DcPiont on 2018/2/11.
 */
public class Present {
	private int id;
	private int userId;
	private int eventId;
	private int weight;
	private String presentName;
	private int winnerUserId;

	public int getWinnerUserId() {
		return winnerUserId;
	}

	public void setWinnerUserId(int winnerUserId) {
		this.winnerUserId = winnerUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getPresentName() {
		return presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}
}
