package com.huiting.manage.system.convert;


import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @ClassName: BigDecimalConverter
 * @Description: BigDecimal转换
 * @author Zou_ZhuoQi
 * @date 2014-1-3 下午5:07:17
 */

public class BigDecimalConverter extends StrutsTypeConverter {
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		BigDecimal bd = null;
		if (BigDecimal.class == toClass) {
			String bdStr = values[0];
			if (bdStr != null && !"".equals(bdStr)) {
				bd = new BigDecimal(bdStr);
			} else {
//				 bd = BigDecimal.valueOf(-1);
			}
			return bd;
		}
		return BigDecimal.ZERO;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof BigDecimal) {
			BigDecimal b = new BigDecimal(o.toString()).setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
			return b.toString();

		}
		return o.toString();
	}
}
