package com.huiting.manage.dto.sysconfig;

import org.apache.ibatis.type.Alias;

import com.huiting.manage.dto.base.CdChannelBaseDto;
@Alias("cdChannelDto")
public class CdChannelDto extends CdChannelBaseDto{

	private static final long serialVersionUID = 1L;
	/**是否勾选过*/
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
}
