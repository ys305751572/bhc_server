package com.gcs.aol.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcs.aol.entity.Adspublish;
import com.gcs.aol.entity.Attach;
import com.gcs.aol.entity.Imageads;
import com.gcs.aol.service.IAdspublishManager;
import com.gcs.aol.service.impl.AolUserManagerImpl;
import com.gcs.aol.service.impl.ImageadsManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.aol.vo.MsgJsonReturn;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.util.ServiceLocator;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/imageads")
public class ImageadsController extends GenericEntityController<Imageads, Imageads, ImageadsManagerImpl> {
	@Autowired
	IAdspublishManager adsPublishManager;
	
	private static final String ADSLIST = "management/aol/adsMgr/adsList";
	private static final String ADDADS = "management/aol/adsMgr/editAds";
	private static final String PUBLISHADS = "management/aol/adsMgr/publishAds";
	
	/**
	 * 跳转到广告管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adslist", method=RequestMethod.GET)
	public String adslist(HttpServletRequest request) {	
		return ADSLIST;
	}
	
	/**
	 * 获取广告列表数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getAdsDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getAdsDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String _title = paramMap.get("_title");
		String adsType = paramMap.get("adsType");
		String adsState = paramMap.get("adsState");
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryAdsDataList(pp, _title, adsType, adsState);
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		//2015年1月21日  加入代理商的名称
		//偷偷懒，以后效率不好了再改
		AolUserManagerImpl aolUserManagerImpl = (AolUserManagerImpl)ServiceLocator.lookup(AolUserManagerImpl.class);
		for (Object obj : tmpList) {
			Imageads img = (Imageads)obj;
			String userId = img.getCreateUser();
			String userName = aolUserManagerImpl.findByUserId(userId).getName();
			//查询代理商的名称
			img.setBak4(userName);
		}
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 * 跳转到广告新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addads", method=RequestMethod.GET)
	public String addads(HttpServletRequest request) {	
		return ADDADS;
	}
	
	/**
	 * 跳转到广告编辑页面
	 * @param request
	 * @param imageadsId
	 * @return
	 */
	@RequestMapping(value="/editads", method=RequestMethod.GET)
	public String editads(HttpServletRequest request, String imageadsId) {	
		Imageads ads = this.getEntityManager().queryByPK(imageadsId);
		request.setAttribute("ads", ads);
		return ADDADS;
	}
	
	/**
	 * 广告信息保存
	 * @param ads
	 * @param request
	 * @param session
	 * @param imageFile
	 * @return
	 */
	@RequestMapping(value = "/saveAds", method = RequestMethod.POST)
	public String saveAds(Imageads ads, HttpServletRequest request, HttpSession session, MultipartFile imageFile){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		//新增修改后数据状态都为0，即未发布
		ads.setAdsState("0");
		
		if(imageFile!=null&&imageFile.getSize()>0){
			String webRoot = request.getSession().getServletContext().getRealPath("");
			Attach attach  = CommonUtils.uploadAttach(imageFile, webRoot, "//upload//ads//", loginuser.getUserId());
			if(StringUtils.isNotBlank(attach.getAttachId()))
				ads.setImageUrl("//upload//ads//"+attach.getAttachName());
		}
		
		ads.setCreateTime(new Timestamp(System.currentTimeMillis()));
		ads.setCreateUser(loginuser.getUserId());
		ads.setCreateOrg(loginuser.getOrganiseId());
		this.getEntityManager().save(ads);
		
		request.setAttribute("result", "{success:'true', msg:'保存成功'}");
		return ADDADS;
	}
	
	/**
	 * 删除广，一并删除该广告的发布记录
	 * @param imageadsId
	 * @return
	 */
	@RequestMapping(value = "/deleteads", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn deleteads(String imageadsId){
		try {
			if(StringUtils.isNotBlank(imageadsId)){
				this.getEntityManager().deleteByPK(imageadsId);
				adsPublishManager.deleteAdsById(imageadsId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgJsonReturn(false, "删除失败");
		}
		
		return new MsgJsonReturn(true, "删除成功");
	}
	
	/**
	 * 跳转到广告发布页面
	 * @param request
	 * @param imageadsId
	 * @return
	 */
	@RequestMapping(value="/publishads", method=RequestMethod.GET)
	public String publishads(HttpServletRequest request, String imageadsId) {	
		Imageads ads = this.getEntityManager().queryByPK(imageadsId);
		request.setAttribute("ads", ads);
		return PUBLISHADS;
	}
	
	/**
	 * 发布广告
	 * @param request
	 * @param session
	 * @param orgChoose
	 * @param imageadsId
	 * @return
	 */
	@RequestMapping(value = "/doPublishAds", method = RequestMethod.POST)
	public String doPublishAds(HttpServletRequest request, HttpSession session, String orgChoose, String imageadsId){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		Imageads ads = this.getEntityManager().queryByPK(imageadsId);
		Timestamp publish_time = new Timestamp(System.currentTimeMillis());
		
		String[] chooseids = orgChoose.split(",");
		for(int i=0; i<chooseids.length; i++){
			String chooseid = chooseids[i];
			
			chooseid = chooseid.replaceAll("U_", "").replaceAll("O_", "");
			
			Adspublish adsP = new Adspublish();
			adsP.setImageadsId(ads.getImageadsId());
			adsP.setReceiveId(chooseid);
			adsP.setPublishTime(publish_time);
			adsP.setPublishUser(loginuser.getUserId());
			adsP.setPublishOrg(loginuser.getOrganiseId());
			
			adsPublishManager.save(adsP);
		}
		ads.setAdsState("1");
		this.getEntityManager().save(ads);
		
		request.setAttribute("result", "{success:'true', msg:'发布成功'}");
		return PUBLISHADS;
	}
	
}
