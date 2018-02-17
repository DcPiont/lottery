package com.dcpiont.service;

import com.dcpiont.module.NeedBO;
import com.dcpiont.module.Present;
import com.dcpiont.module.PresentVO;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface IPresentService {
	List<PresentVO> getAllPresentByEventId(int eventId);

	int addPresentNeed(int userId,int presentId,int eventId);

	int addPresent(Present present);

	int deletePresent(Present present);

	int lottery(int eventId);

	int updatePresentWeight(int presentId);

	NeedBO getUserNeedPresent(int eventId, int userId);

	List<PresentVO> getPresentWinner(int eventId);
}
