package com.dcpiont.service.impl;

import com.dcpiont.dao.PresentDao;
import com.dcpiont.module.Present;
import com.dcpiont.service.IPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Service("PresentService")
public class PresentServiceImpl implements IPresentService {
	@Autowired
	private PresentDao presentDao;
	public List<Present> getAllPresentByEventId(int eventId) {
		return presentDao.getPresentListByEventId(eventId);
	}

	public int addPresentNeed(int userId, int presentId, int eventId) {
		return presentDao.addPresentNeed(eventId,userId,presentId);
	}

	public int addPresent(Present present) {
		return presentDao.addPresent(present);
	}

	public int deletePresent(Present present) {
		return 0;
	}

	public int lottery() {
		return 0;
	}
}
