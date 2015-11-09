package com.gcs.aol.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.aol.entity.Imageads;
import com.gcs.aol.entity.WeatherInfo;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.IDeviceManager;
import com.gcs.aol.service.IImageadsManager;
import com.gcs.aol.service.IMeasureManager;
import com.gcs.aol.service.IOrganiseManager;
import com.gcs.aol.service.IWeatherInfoManager;
import com.gcs.aol.utils.WeatherUtils;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.utils.DateUtil;

@Controller
@RequestMapping("/management/home")
public class HomeController {
	@Autowired
	IImageadsManager adsManager;
	@Autowired
	IWeatherInfoManager weatherInfoManager;
	@Autowired
	IAolUserManager iuserManager;
	@Autowired
	IOrganiseManager organiseManager;
	@Autowired
	IDeviceManager deviceManager;
	@Autowired
	IMeasureManager measureManager;
	
	private static final String HOME_PAGE = "management/index/home";
	private static final String ORG_HOME_PAGE = "management/index/org_home";
	
	private static final String WEATHER_PAGE = "management/aol/weatherMgr/weather";
	
	/**
	 * 跳转到首页面（总公司和代理商）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orgHomePage", method=RequestMethod.GET)
	public String orgHomePage(HttpServletRequest request) {
		/**
		 * 1.如果是总公司显示下面所有的用户
		 * 2.如果是代理商就显示下面新增的用户
		 */
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String organiseid = loginuser.getOrganiseId();
		//查询新增代理商数量
		String newAddOrgCount = "0";
		//查询代理商数量
		String countOrg = "0";
		if(StringUtils.isNotBlank(organiseid)){
			newAddOrgCount = organiseManager.findCountOrg(organiseid,com.gcs.aol.utils.DateUtil.getNewStartTime());
			countOrg = organiseManager.findCountOrg(organiseid,"");
		}
		request.setAttribute("newAddOrgCount", newAddOrgCount);
		request.setAttribute("countOrg", countOrg);
		
		//查询新增用户数量
		String newAddUseCount = "0";
		//查询用户数量
		String countUser = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			newAddUseCount = iuserManager.findAllUser(com.gcs.aol.utils.DateUtil.getNewStartTime());
			countUser = iuserManager.findAllUser("");
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				newAddUseCount = iuserManager.queryCountUsersByOrgid(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
				countUser = iuserManager.queryCountUsersByOrgid(organiseid, "");
			}
		}
		request.setAttribute("newAddUseCount", newAddUseCount);
		request.setAttribute("countUser", countUser);
		
		//查询新增血压计总数
		String newAddxyCount = "0";
		String xyCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			newAddxyCount = deviceManager.fingAllDevice(com.gcs.aol.utils.DateUtil.getNewStartTime(),"1");
			xyCount = deviceManager.fingAllDevice("","1");
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				newAddxyCount = deviceManager.queryDevicesByOrgId(organiseid, "1", com.gcs.aol.utils.DateUtil.getNewStartTime());
				xyCount = deviceManager.queryDevicesByOrgId(organiseid, "1", "");
			}
		}
		request.setAttribute("newAddxyCount", newAddxyCount);
		request.setAttribute("xyCount", xyCount);
		//查询新增血糖计总数
		String newAddxtCount = "0";
		String xtCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			newAddxtCount = deviceManager.fingAllDevice(com.gcs.aol.utils.DateUtil.getNewStartTime(),"2");
			xtCount = deviceManager.fingAllDevice("","2");
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				newAddxtCount = deviceManager.queryDevicesByOrgId(organiseid, "2", com.gcs.aol.utils.DateUtil.getNewStartTime());
				xtCount = deviceManager.queryDevicesByOrgId(organiseid, "2", "");
			}
		}
		request.setAttribute("newAddxtCount", newAddxtCount);
		request.setAttribute("xtCount", xtCount);
		/*//查询新增设备数量
		String newAddDiviceCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			newAddDiviceCount = deviceManager.fingAllDevice(com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				newAddDiviceCount = deviceManager.queryDevicesByOrgId(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("newAddDiviceCount", newAddDiviceCount);*/
		
		//查询高血压偏高
		String highToUpCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			highToUpCount = measureManager.findHighBloodToUp(loginuser.getUserId(), com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				highToUpCount = measureManager.findHighBloodToUp(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("highToUpCount", highToUpCount);
		
		//查询高血压偏低
		String highToDownCount = "11";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			highToDownCount = measureManager.findHighBloodToDown(loginuser.getUserId(), com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				highToDownCount = measureManager.findHighBloodToDown(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("highToDownCount", highToDownCount);
		
		//查询低血压偏低
		String lowToDownCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			lowToDownCount = measureManager.findLowBloodToDown(loginuser.getUserId(), com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				lowToDownCount = measureManager.findLowBloodToDown(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("lowToDownCount", lowToDownCount);
		
		//查询低血压偏高
		String lowToUpCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			lowToUpCount = measureManager.findLowBloodToUp(loginuser.getUserId(), com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				lowToUpCount = measureManager.findLowBloodToUp(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("lowToUpCount", lowToUpCount);
		
		//查询血糖偏高
		String sugarToUpCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			sugarToUpCount = measureManager.findBloodSugarToUp(loginuser.getUserId(), com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				sugarToUpCount = measureManager.findBloodSugarToUp(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("sugarToUpCount", sugarToUpCount);
		
		//查询血糖偏低
		String sugarToDownCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			sugarToDownCount = measureManager.findBloodSugarToDown(loginuser.getUserId(), com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				sugarToDownCount = measureManager.findBloodSugarToDown(organiseid, com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("sugarToDownCount", sugarToDownCount);
		
		//统计血糖计设备
		String bloodsugarDiviceCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			bloodsugarDiviceCount = deviceManager.queryDevicesByOrgId("", "2", com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				bloodsugarDiviceCount =  deviceManager.queryDevicesByOrgId(organiseid, "12", com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("bloodsugarDiviceCount", bloodsugarDiviceCount);
		
		//统计血压设备
		String bloodPressureDiviceCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			bloodPressureDiviceCount = deviceManager.queryDevicesByOrgId("", "1", com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				bloodPressureDiviceCount =  deviceManager.queryDevicesByOrgId(organiseid, "1", com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("bloodPressureDiviceCount", bloodPressureDiviceCount);
		
		//统计体温设备
		String temperatureDiviceCount = "0";
		if("99".equals(loginuser.getUserType())){
			//如果是总公司查询所有用户
			temperatureDiviceCount = deviceManager.queryDevicesByOrgId("", "3", com.gcs.aol.utils.DateUtil.getNewStartTime());
		}else if("1".equals(loginuser.getUserType())){
			//如果是代理商
			if(StringUtils.isNotBlank(organiseid)){
				temperatureDiviceCount =  deviceManager.queryDevicesByOrgId(organiseid, "3", com.gcs.aol.utils.DateUtil.getNewStartTime());
			}
		}
		request.setAttribute("loginUser", request.getSession().getAttribute(SecurityConstants.LOGIN_USER));
		request.setAttribute("temperatureDiviceCount", temperatureDiviceCount);
		request.setAttribute("starttime", com.gcs.aol.utils.DateUtil.getNewStartTime());
		return ORG_HOME_PAGE;
	}
	
	/**
	 * 跳转到首页面（普通用户）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/homePage", method=RequestMethod.GET)
	public String homePage(HttpServletRequest request) {
		List<Imageads> adsList = new ArrayList<Imageads>();
		//查找到登陆用户的用户ID
		LoginUserVO loginuser = (LoginUserVO)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("0".equals(loginuser.getUserType())){
			adsList = adsManager.adsListByLoginuser(loginuser.getUserId());
		} else {
			adsList = adsManager.adsListByLoginuser(loginuser.getOrganiseId());
		}
		if(adsList != null && adsList.size() > 0){
			request.setAttribute("adsList", adsList);
		} else {
			request.setAttribute("adsList", null);
		}
		
		return HOME_PAGE;
	}
	
	/**
	 * 跳转到天气展示页面
	 * @return
	 */
	@RequestMapping(value="/weatherPage", method=RequestMethod.GET)
	public String weatherPage() {
		return WEATHER_PAGE;
	}
	
	/**
	 * 提示页面：正在建设中......
	 */
	private static final String BUILD_PAGE = "module/page1";
	@RequestMapping(value="/buildPage", method=RequestMethod.GET)
	public String buildPage() {
		return BUILD_PAGE;
	}
	
	@RequestMapping(value="/loadWeather", method=RequestMethod.POST)
	public void loadWeather(HttpServletRequest request, HttpServletResponse response, String cityCode){
		Boolean isSuccess = false;
		String resultStr = "";
		//当前时间（yyyy-MM-dd）
		String dayStr = DateUtil.getYearMonthDay();
		int dayState = DateUtil.getDataState();
		String weatherStrXml = "";
		
		if(StringUtils.isNotBlank(cityCode)){
			StringBuilder sb = new StringBuilder();
			String[] cityStr = cityCode.split("@@");
			try {
				WeatherInfo weatherinfo = weatherInfoManager.queryUniqueBy("cityCode", cityStr[0]);
				if(weatherinfo != null){
					if(dayStr.equals(weatherinfo.getDateTime()) && dayState == weatherinfo.getDataState()){
						weatherStrXml = weatherinfo.getWeatherInfo();
					} else {
						weatherStrXml = WeatherUtils.getWeaterMessage(cityStr[1]);
					}
				} else {
					weatherStrXml = WeatherUtils.getWeaterMessage(cityStr[1]);
				}
				
				Map<String, String> weatherMap = WeatherUtils.getWeaterOfXml(weatherStrXml);
				if(!"true".equals(weatherMap.get("isOk"))){
					isSuccess = false;
					
					sb.append("<div class='area'>");
					sb.append("  <a href='javascript:void(0)' onclick='fSwitchSet();return false;' title='切换地点'>" + cityStr[1] + "<b class='ico ico-arr-d'></b></a>");
					sb.append("</div>");
					sb.append("<div class='area2'>");
					sb.append("  <div class='area2-tomorrow'>");
					sb.append("    <span>获取天气太过频繁，请稍后再试......</span>");
					sb.append("  </div>");
					sb.append("</div>");
					sb.append("<div class='area2' style='margin-top: 5px;text-align: right;'>");
					sb.append("  <div style='right: 0px;top: 0;'>");
					sb.append("    <a href='http://www.weather.com.cn/weather/" + cityStr[0] + ".shtml' target='_blank' title='查看未来7天天气'>[查看未来7天天气]</a>");
					sb.append("  </div>");
					sb.append("</div>");
				} else {
					isSuccess = true;
					if(weatherinfo != null){
						if(dayStr.equals(weatherinfo.getDateTime()) && dayState == weatherinfo.getDataState()){
						} else {
							weatherinfo.setCityCode(cityStr[0]);
							weatherinfo.setDateTime(dayStr);
							weatherinfo.setWeatherInfo(weatherStrXml);
							weatherinfo.setDataState(dayState);
							weatherInfoManager.save(weatherinfo);
						}
					} else {
						weatherinfo = new WeatherInfo();
						weatherinfo.setCityCode(cityStr[0]);
						weatherinfo.setDateTime(dayStr);
						weatherinfo.setWeatherInfo(weatherStrXml);
						weatherinfo.setDataState(dayState);
						weatherInfoManager.save(weatherinfo);
					}
					
					sb.append("<div class='area'>");
					sb.append("  <a href='javascript:void(0)' onclick='fSwitchSet();return false;' title='切换地点'>" + weatherMap.get("city_name") + "<b class='ico ico-arr-d'></b></a>");
					sb.append("    <div class='area2-future'>");
					
					if(StringUtils.isNotBlank(weatherMap.get("pm25"))){
						int pm25 = Integer.parseInt(weatherMap.get("pm25"));
						if(pm25>=0 && pm25<50){
							sb.append("      空气质量:" + weatherMap.get("pm25") + "&nbsp;&nbsp;<span style='color:#9cf215;'>优</span>");
						} else if(pm25>=50 && pm25<=100){
							sb.append("      空气质量:" + weatherMap.get("pm25") + "&nbsp;&nbsp;<span style='color:#fffb51;'>良</span>");
						} else if(pm25>=101 && pm25<=150){
							sb.append("      空气质量:" + weatherMap.get("pm25") + "&nbsp;&nbsp;<span style='color:#fbb237;'>轻度污染</span>");
						} else if(pm25>=151 && pm25<=200){
							sb.append("      空气质量:" + weatherMap.get("pm25") + "&nbsp;&nbsp;<span style='color:#f7000c;'>中度污染</span>");
						} else if(pm25>=201 && pm25<=300){
							sb.append("      空气质量:" + weatherMap.get("pm25") + "&nbsp;&nbsp;<span style='color:#a9012c;'>重度污染</span>");
						} else if(pm25>300){
							sb.append("      空气质量:" + weatherMap.get("pm25") + "&nbsp;&nbsp;<span style='color:#600031;'>严重污染</span>");
						}
					}
					
					sb.append("    </div>");
					sb.append("</div>");
					sb.append("<div class='area2'>");
					sb.append("  <div class='area2-tomorrow'>");
					sb.append("    <span title='今天'>今天：" + weatherMap.get("week_today") + "</span>");
					sb.append("  </div>");
					sb.append("  <div class='area2-tomorrow' style='padding-top: 10px;'>");
					sb.append("    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + weatherMap.get("weather_today") + "&nbsp;&nbsp;" + weatherMap.get("temperature_today") + "</span>");
					sb.append("  </div>");
					sb.append("  <div class='area2-tomorrow' style='padding-top: 10px;'>");
					sb.append("    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + weatherMap.get("wind_today") + "</span>");
					sb.append("  </div>");
					sb.append("</div>");
					sb.append("<div class='area2'>");
					sb.append("  <div class='area2-tomorrow'>");
					sb.append("    <span title='明天'>明天：" + weatherMap.get("week_tomorrow") + "&nbsp;" + DateUtil.getMonthAndDay(weatherMap.get("date_time")) + "</span>");
					sb.append("  </div>");
					sb.append("  <div class='area2-tomorrow' style='padding-top: 10px;'>");
					sb.append("    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + weatherMap.get("weather_tomorrow") + "&nbsp;&nbsp;" + weatherMap.get("temperature_tomorrow") + "</span>");
					sb.append("  </div>");
					sb.append("  <div class='area2-tomorrow' style='padding-top: 10px;'>");
					sb.append("    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + weatherMap.get("wind_tomorrow") + "</span>");
					sb.append("  </div>");
					sb.append("</div>");
					sb.append("<div class='area2' style='margin-top: 5px;text-align: right;'>");
					sb.append("  <div style='right: 0px;top: 0;'>");
					sb.append("    <a href='http://www.weather.com.cn/weather/" + cityStr[0] + ".shtml' target='_blank' title='查看未来7天天气'>[查看未来7天天气]</a>");
					sb.append("  </div>");
					sb.append("</div>");
				}
				
				resultStr = sb.toString();
			} catch (Exception e) {
				isSuccess = false;
			}
		} else {
			isSuccess = false;
		}
		
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write("{\"isSuccess\":" + isSuccess + ", \"resultdata\":\"" + resultStr + "\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
