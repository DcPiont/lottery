package com.dcpiont.service.impl;

import com.dcpiont.dao.PresentDao;
import com.dcpiont.dao.UserDao;
import com.dcpiont.module.NeedBO;
import com.dcpiont.module.Present;
import com.dcpiont.module.Result;
import com.dcpiont.service.IPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Service("PresentService")
public class PresentServiceImpl implements IPresentService {
	@Autowired
	private PresentDao presentDao;
	@Autowired
	private UserDao userDao;

	public List<Present> getAllPresentByEventId(int eventId) {
		return presentDao.getPresentListByEventId(eventId);
	}

	public int addPresentNeed(int userId, int presentId, int eventId) {
		int res = presentDao.addPresentNeed(eventId, userId, presentId);
		if (res > 0) {
			return updatePresentWeight(presentId);
		} else {
			return 0;
		}
	}

	public int addPresent(Present present) {
		return presentDao.addPresent(present);
	}

	public int deletePresent(Present present) {
		return 0;
	}

	public int lottery(int eventId) {
		int res = 0;
		//获取当前抽奖事件的所有奖品
		List<Present> presentList = presentDao.getPresentListByEventId(eventId);
		//获取当前事件所有参与人员
		List<NeedBO> userList = userDao.getJoinUserList(eventId);
		for (Present present : presentList) {
			NeedBO winner = null;
			List<Result> resultList = new ArrayList<Result>();
			for (NeedBO needBO : userList) {
				//若礼物的来源者id与当前用户id相等，则跳过
				if (needBO.getId() == present.getUserId()) {
					continue;
				}
				//记录结果
				Result result = new Result();
				result.setPresentId(needBO.getPresentId());
				result.setUserId(needBO.getId());
				result.setResult((int) Math.random() * 100 + 1);//1-100内的随机整数
				if (winner == null) {
					winner = needBO;
				}
				while (winner.getResult().getResult() == result.getResult()) {
					//若存在结果与当前最大值相等，则重新计算结果值
					result.setResult((int) Math.random() * 100 + 1);
				}
				if (result.getResult() > winner.getResult().getResult()) {
					//若当前用户结果值大于当前最大值，则替换当前最大值
					needBO.setResult(result);
					winner = needBO;
				}
				resultList.add(result);
			}
			//将当前礼物的最终结果持久化
			res = presentDao.addResult(resultList);
			//将中奖用户从列表中移除
			userList.remove(winner);
			//移除已开奖奖品
			presentList.remove(present);
		}
		return res;
	}

	public int updatePresentWeight(int presentId) {
		return presentDao.updatePresentWeight(presentId);
	}
}
