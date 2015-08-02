package com.huiting.manage.action.common;

import java.util.List;

import javax.annotation.Resource;

import com.huiting.manage.dto.common.CcUserDto;
import com.huiting.manage.dto.common.CdRiskKindDto;
import com.huiting.manage.dto.common.SearchDto;
import com.huiting.manage.services.common.PublicJSPConditionsService;
import com.huiting.manage.system.util.Constants;
import com.huiting.manage.system.util.DateUtil;

/**
 * @ClassName: PublicJSPConditionsAction
 * @Description: 页面查选条件
 * @author Zou_ZhuoQi
 * @date 2013-12-9 下午5:27:03
 * 
 */

public class PublicJSPConditionsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/*** 页面初始化条件 **/
	@Resource(name = "publicJSPConditionsServiceImpl")
	private PublicJSPConditionsService publicJSPConditionsService;
	SearchDto searchDto;

	/**
	 * @Description: 风险管理
	 */
	public String getRiskKind() {
		List<CdRiskKindDto> cdRiskKindBaseDtoList = publicJSPConditionsService
				.getCdRiskKindList(searchDto);
		renderJson(cdRiskKindBaseDtoList);
		return null;
	}

	/**
	 * @Description: 邮件类型
	 */
	public String getTemplateCatno() {
		CcUserDto ccUserDto=(CcUserDto) session.get(Constants.USERLOGIN);
		if(searchDto==null){
			searchDto = new SearchDto();
		}
		searchDto.setComCode(ccUserDto.getComcode());
		List<Object> cdTemplateCatnoList = publicJSPConditionsService
				.getCdTemplateCatnoList(searchDto);
		renderJson(cdTemplateCatnoList);
		return null;
	}

	/**
	 * @Description: 公司信息
	 */
	public String getCompany() {
		renderJson(publicJSPConditionsService.getCompany(searchDto));
		return null;
	}

	/**
	 * @Description: 部门信息
	 */
	public String getDep() {
		if (searchDto == null) {
			searchDto = new SearchDto();
		}
		searchDto.setValidstate("1");
		renderJson(publicJSPConditionsService.getDepList(searchDto));
		return null;
	}
	/**
	 * 根据时间期次查询有那些部门可以填报
	 * @return
	 */
	public String getInputDep(){
		if (searchDto == null) {
			searchDto = new SearchDto();
		}
		searchDto.setFetchrate(DateUtil.getFetchrate(searchDto.getStatDate()));//设置频率
		renderJson(publicJSPConditionsService.getInputDepList(searchDto));
		return null;

		
	}
	/**
	 * @Description: 部门岗位信息
	 */
	public String getDepPos() {
		if (searchDto == null) {
			searchDto = new SearchDto();
		}
		renderJson(publicJSPConditionsService.getDepPositionList(searchDto));
		return null;
	}
	/**
	 * 
	* @Title: getDataSoure 
	* @Description: 数据来源
	* @param @return    
	* @return String  
	* @throws
	 */
	public String getDataSoure(){
		if (searchDto == null) {
			searchDto = new SearchDto();
		}
		renderJson(publicJSPConditionsService.getDataSoureList(searchDto));
		return null;
	}
	/**
	  * @Description: 因子大类名称
	  * @param @return    
	  * @return String   
	  * @throws
	 */
	public String getIdxCatgory(){
		if (searchDto == null) {
			searchDto = new SearchDto();
		}
		renderJson(publicJSPConditionsService.getIdxCatgoryList(searchDto));
		return null;
	}
	/**
	 * 获得报送报表的类型
	 * @return
	 */
	public String getReportType(){
		if (searchDto == null) {
			searchDto = new SearchDto();
		}
		renderJson(publicJSPConditionsService.getReportType(searchDto));
		return null;
	}
	
	
	public SearchDto getSearchDto() {
		return searchDto;
	}

	public void setSearchDto(SearchDto searchDto) {
		this.searchDto = searchDto;
	}

}
