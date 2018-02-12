package com.dcpiont.module;

/**
 * Created by DcPiont on 2018/2/12.
 */
public class NeedBO extends User{
	private int eventId;
	private int presentId;
	private Result result;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getPresentId() {
		return presentId;
	}

	public void setPresentId(int presentId) {
		this.presentId = presentId;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
