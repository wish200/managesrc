package com.huiting.manage.action.app;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.huiting.manage.action.common.BaseAction;
import com.huiting.manage.dto.base.AppFlowerBaseDto;
import com.huiting.manage.dto.base.AppPicbookBaseDto;
import com.huiting.manage.dto.common.CcUserDto;
import com.huiting.manage.dto.common.SearchDto;
import com.huiting.manage.services.app.AppPicbookService;
import com.huiting.manage.system.tag.Page;
import com.huiting.manage.system.util.Constants;

public class AppPicbookAction extends BaseAction{


	private static final long serialVersionUID = 1L;
	private AppPicbookBaseDto appPicbookBaseDto;
	private List<AppPicbookBaseDto> appPicbookBaseDtos;
	@Resource(name = "appPicbookServiceImpl")
	private AppPicbookService appPicbookService;
	private SearchDto searchDto;
	private String PicbookID;
	private Page page;
	private String userid;
	
	/**
	 * 
	* @Title: addSysPost 
	* @Description: 公告信息list
	* @param @return    
	* @return String  
	* @throws
	 */ 
	public String getList(){
		CcUserDto ccUserDto=(CcUserDto) session.get(Constants.USERLOGIN);
		if(searchDto==null){
			searchDto=new SearchDto();
		}
		
		/*if("1".equals(ccUserDto.getCcDepPositionBaseDto().getDeppostype())&&"1".equals(ccUserDto.getCcDepPositionBaseDto().getImportlev())){
		}else {
			searchDto.setValidstate("1");
		}*/
		if(page==null){
			page=new Page();
			page.setTotalCount(appPicbookService.getPicbookCount(searchDto));
			
		}
		searchDto.setPage(page);
		appPicbookBaseDtos=appPicbookService.getPicbookList(searchDto);
		return LIST;
	};
	/**
	 * 
	* @Title: addPicbook 
	* @Description: 添加公告信息
	* @param @return    
	* @return String  
	* @throws
	 */
	public String add(){
		if(appPicbookBaseDto!=null){
			String systemCode=appPicbookService.getMaxPicbookCode();//最大Eventno
			String newPicbook=appPicbookService.getNewPicbookCode(systemCode);//获得新的Eventno
			appPicbookBaseDto.setPicbookid(newPicbook);
			appPicbookService.add(appPicbookBaseDto);
			renderText(newPicbook);
		}
		return null;
	}
	/**
	 * 
	* @Title: updateSysPost 
	* @Description: 发布公告信息
	* @param @return    
	* @return String  
	* @throws
	 */
	public String send(){
		appPicbookBaseDto.setPicbookstatus("1");
		appPicbookService.update(appPicbookBaseDto);
		return "returnlist";
	}
	/**
	 * 
	* @Title: updateSysPost 
	* @Description: TODO
	* @param @return    
	* @return String  
	* @throws
	 */
	public String update(){
		appPicbookService.update(appPicbookBaseDto);
		return "update";
	}
	/**
	 * 
	* @Title: delete 
	* @Description: TODO
	* @param @return    
	* @return String  
	* @throws
	 */
	public String delete(){
		appPicbookService.delete(appPicbookBaseDto);
		return "returnlist";
	}
	/**
	 * 
	* @Title: getOneSysPost 
	* @Description: TODO
	* @param @return    
	* @return String  
	* @throws
	 */
	public String check(){
		
		appPicbookBaseDto=appPicbookService.getOnePicbook(searchDto);
		if(searchDto.getDoing().equals("editor")){
			return "editor";
		}else{
			return "check";
		}
		
	}
	
	public void changeuser(){
		if(searchDto==null){
			searchDto=new SearchDto();
		}
		searchDto.setUserid(userid);
		appPicbookBaseDto=appPicbookService.getOneUser(searchDto); 
		if(appPicbookBaseDto==null){
			appPicbookBaseDto = new AppPicbookBaseDto();
		}
		renderJson(appPicbookBaseDto);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public SearchDto getSearchDto() {
		return searchDto;
	}
	public void setSearchDto(SearchDto searchDto) {
		this.searchDto = searchDto;
	}
	
	public String getPicbookID() {
		return PicbookID;
	}
	public void setPicbookID(String PicbookID) {
		this.PicbookID = PicbookID;
	}
	public AppPicbookBaseDto getAppPicbookBaseDto() {
		return appPicbookBaseDto;
	}
	public void setAppPicbookBaseDto(AppPicbookBaseDto appPicbookBaseDto) {
		this.appPicbookBaseDto = appPicbookBaseDto;
	}
	public List<AppPicbookBaseDto> getAppPicbookBaseDtos() {
		return appPicbookBaseDtos;
	}
	public void setAppPicbookBaseDtos(
			List<AppPicbookBaseDto> appPicbookBaseDtos) {
		this.appPicbookBaseDtos = appPicbookBaseDtos;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
