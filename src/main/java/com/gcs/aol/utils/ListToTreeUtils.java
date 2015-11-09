package com.gcs.aol.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gcs.aol.vo.DeciveListVO;
import com.gcs.aol.vo.TreeChildNodeVO;
import com.gcs.aol.vo.TreeListVo;
import com.gcs.aol.vo.TreeNodeVO;

public class ListToTreeUtils {
	
	public static List<TreeListVo> getTree(List<DeciveListVO> DeciveList){
		 List<TreeListVo> treelist = new ArrayList<TreeListVo>();
		//支持2级节点，先把2级节点算出
		 String str = "";
		 int i = 1;
		for (DeciveListVO deciveListVO : DeciveList) {
			if("1".equals(deciveListVO.getDeviceType())){
				deciveListVO.setDeviceType("血压");
			}
			if("2".equals(deciveListVO.getDeviceType())){
				deciveListVO.setDeviceType("血糖");
			}
			if("3".equals(deciveListVO.getDeviceType())){
				deciveListVO.setDeviceType("体温");
			}
			deciveListVO.setXhid(String.valueOf(i));//添加序号ID
			if(!StringUtils.isNotBlank(deciveListVO.getUserName())){
				deciveListVO.setUserName("未知");
			}
			if(!str.equals(deciveListVO.getUserId())){
				i++;
				TreeListVo treevo = new TreeListVo();
				treevo.setXhid(String.valueOf(i));
				treevo.setUserId(deciveListVO.getUserId());
				treevo.setUserName(deciveListVO.getUserName());
				treelist.add(treevo);
				str = deciveListVO.getUserId();
			}
			i++;
		}
		//一级节点建立
		for (TreeListVo treeListVo : treelist) {
			List<DeciveListVO> children = new ArrayList<DeciveListVO>();
			for (DeciveListVO deciveListVO : DeciveList) {
				if(treeListVo.getUserId().equals(deciveListVO.getUserId())){
					children.add(deciveListVO);
				}
			}
			treeListVo.setChildren(children);
		}
		return treelist;
	}
	
	
	public static List<TreeNodeVO> getTreeNode(List<DeciveListVO> DeciveList){
		 List<TreeNodeVO> treelist = new ArrayList<TreeNodeVO>();
		//支持2级节点，先把2级节点算出
		 String str = "";
		 int i = 1;
		for (DeciveListVO deciveListVO : DeciveList) {
			if("1".equals(deciveListVO.getDeviceType())){
				deciveListVO.setDeviceType("血压");
			}
			if("2".equals(deciveListVO.getDeviceType())){
				deciveListVO.setDeviceType("血糖");
			}
			if("3".equals(deciveListVO.getDeviceType())){
				deciveListVO.setDeviceType("体温");
			}
			deciveListVO.setXhid(String.valueOf(i));//添加序号ID
			if(!StringUtils.isNotBlank(deciveListVO.getUserName())){
				deciveListVO.setUserName("未知");
			}
			if(!str.equals(deciveListVO.getUserId())){
				i++;
				TreeNodeVO treevo = new TreeNodeVO();
				treevo.setId(deciveListVO.getUserId());
				treevo.setText("<strong>"+deciveListVO.getUserName()+"</strong>");
				treevo.setState("closed");
				treevo.setIconCls("");
				treelist.add(treevo);	
				str = deciveListVO.getUserId();
			}
			i++;
		}
		//一级节点建立
		for (TreeNodeVO treenodevO : treelist) {
			List<Object> childrenNodeList = new ArrayList<Object>();
			for (DeciveListVO deciveListVO : DeciveList) {
				if(treenodevO.getId().equals(deciveListVO.getUserId())){
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", deciveListVO.getUserId());
					childrenNodeList.add(new TreeChildNodeVO(deciveListVO.getDevice_id(), deciveListVO.getDeviceSerial(),"open", "",false,map));
				}
			}
			treenodevO.setChildren(childrenNodeList);
		}
		return treelist;
	}
	
}
