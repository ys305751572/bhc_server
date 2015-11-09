
package com.gcs.sysmgr.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.beanvalidator.BeanValidators;

import com.gcs.sysmgr.entity.main.Role;
import com.gcs.sysmgr.entity.main.RolePermission;
import com.gcs.sysmgr.log.Log;
import com.gcs.sysmgr.log.LogMessageObject;
import com.gcs.sysmgr.log.impl.LogUitl;
import com.gcs.sysmgr.service.ModuleService;
import com.gcs.sysmgr.service.RoleService;
import com.gcs.sysmgr.util.dwz.AjaxObject;
import com.gcs.sysmgr.util.dwz.Page;
import com.google.common.collect.Lists;


@Controller
@RequestMapping("/management/security/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private Validator validator;
	
	private static final String CREATE = "management/security/role/create";
	private static final String UPDATE = "management/security/role/update";
	private static final String LIST = "management/security/role/list";
	private static final String VIEW = "management/security/role/view";
	
	@RequiresPermissions("Role:save")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
		map.put("module", moduleService.getTree());
		return CREATE;
	}
	
	@Log(message="添加了{0}角色。")
	@RequiresPermissions("Role:save")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody String create(Role role) {
		//调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, role);
		
		List<RolePermission> rolePermissions = Lists.newArrayList();
		for (RolePermission rolePermission : role.getRolePermissions()) {
			if (rolePermission.getPermission() != null && rolePermission.getPermission().getId() != null) {
				rolePermissions.add(rolePermission);
			}
		}
		
		for (RolePermission rolePermission : rolePermissions) {
			rolePermission.setRole(role);
		}
		
		roleService.save(role);
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{role.getName()}));
		return AjaxObject.newOk("添加角色成功！").toString();
	}
	
	@RequiresPermissions("Role:edit")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		Role role = roleService.get(id);
		
		map.put("module", moduleService.getTree());
		map.put("role", role);
		return UPDATE;
	}
	
	@Log(message="修改了{0}角色的信息。")
	@RequiresPermissions("Role:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(Role role) {
		BeanValidators.validateWithException(validator, role);
		
		Role oldRole = roleService.get(role.getId());
		oldRole.setName(role.getName());
		oldRole.setDescription(role.getDescription());
		
		for (RolePermission rolePermission : role.getRolePermissions()) {
			if (rolePermission.getPermission() != null && rolePermission.getPermission().getId() != null) {
				if (rolePermission.getId() == null) {
					rolePermission.setRole(oldRole);
					oldRole.getRolePermissions().add(rolePermission);
					
				}
			} else {
				if (rolePermission.getId() != null) {
					for (RolePermission oldRolePermission : oldRole.getRolePermissions()) {
						if (oldRolePermission.getId().equals(rolePermission.getId())) {
							oldRolePermission.setRole(null);
							rolePermission = oldRolePermission;
							
							break;
						}
					}
					oldRole.getRolePermissions().remove(rolePermission);
				}
			}
		}
		
		roleService.update(oldRole);
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{oldRole.getName()}));
		return AjaxObject.newOk("修改角色成功！").toString();
	}
	
	@Log(message="删除了{0}角色。")
	@RequiresPermissions("Role:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
		Role role = roleService.get(id);
		roleService.delete(role.getId());

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{role.getName()}));
		return AjaxObject.newOk("删除角色成功！").setCallbackType("").toString();
	}
	
	@RequiresPermissions("Role:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {
		List<Role> roles = null;
		if (StringUtils.isNotBlank(keywords)) {
			roles = roleService.find(page, keywords);
		} else {
			roles = roleService.findAll(page);
		}

		map.put("page", page);
		map.put("roles", roles);
		map.put("keywords", keywords);
		return LIST;
	}
	
	@RequiresPermissions("Role:view")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		Role role = roleService.get(id);
		
		map.put("module", moduleService.getTree());
		map.put("role", role);
		return VIEW;
	}

}
