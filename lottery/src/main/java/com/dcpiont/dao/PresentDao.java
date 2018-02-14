package com.dcpiont.dao;

import com.dcpiont.module.NeedBO;
import com.dcpiont.module.Present;
import com.dcpiont.module.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface PresentDao {
	int addPresent(Present present);

	List<Present> getPresentListByEventId(int eventId);

	List<NeedBO> getNeedList(int eventId);

	int updatePresentWeight(@Param("presentId") int id);

	int addPresentNeed(@Param("eventId") int eventId, @Param("userId") int userId, @Param("presentId") int presentId);

	int addResult(@Param("list") List<Result> list);

	NeedBO getUserNeedPresent(@Param("eventId") int eventId, @Param("userId") int userId);

	int updatePresentWinner(@Param("presentId") int presentId, @Param("userId") int userId);
}
