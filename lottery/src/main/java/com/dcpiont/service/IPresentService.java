package com.dcpiont.service;

import com.dcpiont.module.Present;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface IPresentService {
	List<Present> getAllPresentByEventId(int eventId);

	int addPresentNeed(int userId,int presentId,int eventId);

	int addPresent(Present present);

	int deletePresent(Present present);

	int lottery();
}
