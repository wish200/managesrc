package com.huiting.manage.action.app;

import java.util.List;

import javax.annotation.Resource;

import com.huiting.manage.action.common.BaseAction;
import com.huiting.manage.dto.base.AppAudioBaseDto;
import com.huiting.manage.dto.base.AppPicbookBaseDto;
import com.huiting.manage.dto.common.CcUserDto;
import com.huiting.manage.dto.common.SearchDto;
import com.huiting.manage.services.app.AppAudioService;
import com.huiting.manage.system.tag.Page;
import com.huiting.manage.system.util.Constants;

public class AppAudioAction extends BaseAction{


	private static final long serialVersionUID = 1L;
	private AppAudioBaseDto appAudioBaseDto;
	private List<AppAudioBaseDto> appAudioBaseDtos;
	@Resource(name = "appAudioServiceImpl")
	private AppAudioService appAudioService;
	private SearchDto searchDto;
	private String AudioID;
	private Page page;
	private String userid;
	private String programid;
	
	
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
			page.setTotalCount(appAudioService.getAudioCount(searchDto));
			
		}
		searchDto.setPage(page);
		appAudioBaseDtos=appAudioService.getAudioList(searchDto);
		return LIST;
	};
	/**
	 * 
	* @Title: addAudio 
	* @Description: 添加公告信息
	* @param @return    
	* @return String  
	* @throws
	 */
	public String add(){
		if(appAudioBaseDto!=null){
			String systemCode=appAudioService.getMaxAudioCode();//最大Eventno
			String newAudio=appAudioService.getNewAudioCode(systemCode);//获得新的Eventno
			appAudioBaseDto.setAudioid(newAudio);
			appAudioService.add(appAudioBaseDto);
			renderText(newAudio);
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
		appAudioBaseDto.setAudiostatus("1");
		appAudioService.update(appAudioBaseDto);
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
		appAudioService.update(appAudioBaseDto);
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
		appAudioService.delete(appAudioBaseDto);
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
		
		appAudioBaseDto=appAudioService.getOneAudio(searchDto);
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
		appAudioBaseDto=appAudioService.getOneUser(searchDto); 
		if(appAudioBaseDto==null){
			appAudioBaseDto = new AppAudioBaseDto();
		}
		renderJson(appAudioBaseDto);
		
	}
	
	public void changeprogram(){
		if(searchDto==null){
			searchDto=new SearchDto();
		}
		searchDto.setProgramid(programid);
		appAudioBaseDto=appAudioService.getOneObject(searchDto); 
		if(appAudioBaseDto==null){
			appAudioBaseDto = new AppAudioBaseDto();
		}
		renderJson(appAudioBaseDto);
		
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
	
	public String getAudioID() {
		return AudioID;
	}
	public void setAudioID(String AudioID) {
		this.AudioID = AudioID;
	}
	public AppAudioBaseDto getAppAudioBaseDto() {
		return appAudioBaseDto;
	}
	public void setAppAudioBaseDto(AppAudioBaseDto appAudioBaseDto) {
		this.appAudioBaseDto = appAudioBaseDto;
	}
	public List<AppAudioBaseDto> getAppAudioBaseDtos() {
		return appAudioBaseDtos;
	}
	public void setAppAudioBaseDtos(
			List<AppAudioBaseDto> appAudioBaseDtos) {
		this.appAudioBaseDtos = appAudioBaseDtos;
	}
	public AppAudioService getAppAudioService() {
		return appAudioService;
	}
	public void setAppAudioService(AppAudioService appAudioService) {
		this.appAudioService = appAudioService;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProgramid() {
		return programid;
	}
	public void setProgramid(String programid) {
		this.programid = programid;
	}

	
}
