<%@page import="com.gcs.sysmgr.entity.main.RolePermission"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="com.gcs.sysmgr.entity.main.Module"%>
<%@page import="com.gcs.sysmgr.entity.main.Role"%>
<%@page import="com.gcs.sysmgr.entity.main.Permission"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%! 
	public String role_tree(List<Module> children, Role role, String treeStyle, Integer[] moduleIndex) {
		StringBuilder builder = new StringBuilder("");
		if (!children.isEmpty()) {
			builder.append("<ul>");
			for(int index = 0; index < children.size(); index++) {
				Module child = children.get(index);
				if (index + 1 == children.size()) {
					builder.append("<li class=\"last\">");
				} else {
					builder.append("<li>");
				}
				
				builder.append("<div class=\"\">");
				builder.append(treeStyle);
				
				if (child.getChildren().size() > 0 && index != children.size() - 1) {
					treeStyle = treeStyle + "<div class=\"line\"></div>";
					builder.append("<div class=\"collapsable\"></div>");
				} else {
	        		treeStyle = treeStyle + "<div class=\"indent\"></div>";
	        		builder.append("<div class=\"node\"></div>");
				}
				builder.append("<span style=\"float:left;\">");
				builder.append("<a href=\"javascript:void(0);\" class=\"permissionList\">");
				builder.append(child.getName());
				builder.append("</a>");
				builder.append("</span>");
				builder.append("<span class=\"inputValueRole\">");
				
				int hit = 0;
				for(Permission permission : child.getPermissions()) {
					builder.append(permission.getName() + "<input type=\"checkbox\" onclick=\"return false;\" name=\"rolePermissions[" +  moduleIndex[0] + "].permission.id\" value=\"" + permission.getId() + "\" ");
					boolean isFind = false;
					for(RolePermission rolePermission : role.getRolePermissions()) {
						if (rolePermission.getPermission().getId().equals(permission.getId())) {
							builder.append("checked=\"checked\"/>");
							builder.append("<input type=\"hidden\" onclick=\"return false;\" name=\"rolePermissions[" + moduleIndex[0] + "].id\" value=\"" + rolePermission.getId() + "\"/>");
							isFind = true;
							hit++;
							break;
						}
					}
					
					if (isFind == false) {
						builder.append("/>");
					}
					moduleIndex[0] = moduleIndex[0] + 1;
				}
				
				if (hit == child.getPermissions().size()) {
					builder.append("全选<input type=\"checkbox\" onclick=\"return false;\" class=\"setAll\" checked=\"checked\"/>");
				} else {
					builder.append("全选<input type=\"checkbox\" onclick=\"return false;\" class=\"setAll\"/>");
				}
				
				builder.append("</span>");
				builder.append("</div>");
				
				builder.append(role_tree(child.getChildren(), role, treeStyle, moduleIndex));
				treeStyle = treeStyle.substring(0, treeStyle.lastIndexOf("<div"));
				builder.append("</li>");
			}
			builder.append("</ul>");			
		}
	
		return builder.toString();
	}
%>
<%
	Module module = (Module)request.getAttribute("module");
	Role role2 = (Role)request.getAttribute("role");
%>
<div class="pageContent">
<form action="#">
	<div class="pageFormContent" layoutH="58">
	<dl>
		<dt>名称：</dt>
		<dd>
			<input type="text" name="name" class="required" size="30" maxlength="32" alt="请输入角色名称" value="${role.name }" readonly="readonly"/>
		</dd>
	</dl>
	<dl>
		<dt>名称：</dt>
		<dd>
			<input type="text" name="description" size="30" maxlength="255" alt="请输入描述" value="${role.description }" readonly="readonly"/>
		</dd>
	</dl>	
	<div class="divider"></div>
	<ul class="treeCustom">
		<li>
			<div class=""><div class="end_collapsable"></div>
			<a href="javascript:void(0);" class="permissionList">
				${module.name }
			</a>
			</div>
			<%=role_tree(module.getChildren(), role2, "<div class=\"indent\"></div>", new Integer[]{0}) %>
		</li>
	</ul>	
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>