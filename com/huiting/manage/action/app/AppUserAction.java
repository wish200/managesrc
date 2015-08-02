package com.huiting.manage.action.app;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.huiting.manage.action.common.BaseAction;
import com.huiting.manage.dto.base.AppUserBaseDto;
import com.huiting.manage.dto.common.CcUserDto;
import com.huiting.manage.dto.common.SearchDto;
import com.huiting.manage.services.app.AppUserService;
import com.huiting.manage.system.tag.Page;
import com.huiting.manage.system.util.Constants;

public class AppUserAction extends BaseAction{


	private static final long serialVersionUID = 1L;
	private AppUserBaseDto appUserBaseDto;
	private List<AppUserBaseDto> appUserBaseDtos;
	@Resource(name = "appUserServiceImpl")
	private AppUserService appUserService;
	private SearchDto searchDto;
	private String UserID;
	private Page page;
	
	
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
			page.setTotalCount(appUserService.getUserCount(searchDto));
			
		}
		searchDto.setPage(page);
		appUserBaseDtos=appUserService.getUserList(searchDto);
		return LIST;
	};
	/**
	 * 
	* @Title: addUser 
	* @Description: 添加公告信息
	* @param @return    
	* @return String  
	* @throws
	 */
	public String add(){
		if(appUserBaseDto!=null){
			//String systemCode=appUserService.getMaxUserCode();//最大Eventno
			//String newUser=appUserService.getNewUserCode(systemCode);//获得新的Eventno
			//appUserBaseDto.setUserid(newUser);
			appUserService.add(appUserBaseDto);
			renderText(appUserBaseDto.getUserid());
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
		//appUserBaseDto.setValidstatus("1");
		appUserService.update(appUserBaseDto);
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
		appUserService.update(appUserBaseDto);
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
		appUserService.delete(appUserBaseDto);
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
		
		appUserBaseDto=appUserService.getOneUser(searchDto);
		if(searchDto.getDoing().equals("editor")){
			return "editor";
		}else{
			return "check";
		}
		
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
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public AppUserBaseDto getAppUserBaseDto() {
		return appUserBaseDto;
	}
	public void setAppUserBaseDto(AppUserBaseDto appUserBaseDto) {
		this.appUserBaseDto = appUserBaseDto;
	}
	public List<AppUserBaseDto> getAppUserBaseDtos() {
		return appUserBaseDtos;
	}
	public void setAppUserBaseDtos(
			List<AppUserBaseDto> appUserBaseDtos) {
		this.appUserBaseDtos = appUserBaseDtos;
	}

}
