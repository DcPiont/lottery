package com.dcpiont.module;

/**
 * Created by DcPiont on 2018/2/11.
 */
public class Event {
	private int id;
	private String eventName;
	private boolean isEnd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean end) {
		isEnd = end;
	}
}
