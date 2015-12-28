package com.faithbj.shop.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faithbj.shop.utils.PropertyUtils;

/**
 * 后台Action类 - 后台管理、管理员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Controller
@RequestMapping("/faith")
public class AdminController extends BaseAdminController {

	private static final long serialVersionUID = 6874927315861247958L;



//	public static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";// Spring security 最后登录异常Session名称
//
//	private String loginUsername;
//	
//	private Admin admin;
//	private List<Roles> allRole;
//	private List<Roles> roleList;
//
//	@Resource
//	private AdminService adminService;
//	@Resource
//	private RoleService roleService;
//	@Resource
//	private OrderService orderService;
//	@Resource
//	private MessageService messageService;
//	@Resource
//	private ProductService productService;
//	@Resource
//	private MemberService memberService;
//	@Resource
//	private ArticleService articleService;
//	@Resource
//	private ServletContext servletContext;
//	
	// 登录页面
	@RequestMapping(value="loginview",method = RequestMethod.GET)
	public String login() {
		
		return "admin/login";
	}
	
	/**
	 * 后台首页主框架入口
	 * @return
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(ModelMap data) {
		return "admin/main";
	}	
	
	/**
	 * 后台中间主页面
	 * @return
	 */
	@RequestMapping(value="main",method = RequestMethod.GET)
	public String main() {
		return "admin/index";
	}
	
	/**
	 *  后台Header
	 * @return
	 */
	@RequestMapping(value="header",method = RequestMethod.GET)
	public String header() {
		return "admin/header";
	}
	
	/**
	 * 后台右侧菜单
	 * @return
	 */
	@RequestMapping(value="menu",method = RequestMethod.GET)
	public String menu() {
		return "admin/menu_common";
	}
	
	/**
	 * 后台左侧菜单
	 * @return
	 */
	@RequestMapping(value="product",method = RequestMethod.GET)
	public String product() {
		return "admin/menu_product";
	}	
	
	
	
	
	
/**
 * 后台中间(显示/隐藏菜单)
 * @return
 */
    @RequestMapping(value="middle",method = RequestMethod.GET)
	public String middle() {
		return "admin/middle";
	}
//	

	
	
//
//	// 是否已存在 ajax验证
//	public String checkUsername() {
//		String username = admin.getUsername();
//		if (adminService.isExistByUsername(username)) {
//			return "false";
//		} else {
//			return "true";
//		}
//	}
//
////	// 添加
////	public String add() {
////		return INPUT;
////	}
////
////	// 编辑
////	public String edit() {
////		admin = adminService.load(id);
////		return INPUT;
////	}
//
//	// 列表
//	public String list() {
//		pager = adminService.findByPager(pager);
//		return LIST;
//	}
//
//	// 删除
//	public String delete() {
//		adminService.delete(ids);
//		return "删除成功！";
//	}
//
////	// 保存
////	@Validations(
////		requiredStrings = {
////			@RequiredStringValidator(fieldName = "admin.username", message = "用户名不允许为空!"),
////			@RequiredStringValidator(fieldName = "admin.password", message = "密码不允许为空!"),
////			@RequiredStringValidator(fieldName = "admin.email", message = "E-mail不允许为空!")
////		},
////		requiredFields = {
////			@RequiredFieldValidator(fieldName = "admin.isAccountEnabled", message = "是否启用不允许为空!")
////		},
////		stringLengthFields = {
////			@StringLengthFieldValidator(fieldName = "admin.username", minLength = "2", maxLength = "20", message = "用户名长度必须在${minLength}到${maxLength}之间!"),
////			@StringLengthFieldValidator(fieldName = "admin.password", minLength = "4", maxLength = "20", message = "密码长度必须在${minLength}到${maxLength}之间!")
////		},
////		emails = {
////			@EmailValidator(fieldName = "admin.email", message = "E-mail格式错误!")
////		},
////		regexFields = {
////			@RegexFieldValidator(fieldName = "admin.username", expression = "^[0-9a-z_A-Z\u4e00-\u9fa5]+$", message = "用户名只允许包含中文、英文、数字和下划线!") 
////		}
////	)
////	@InputConfig(resultName = "error")
////	public String save() {
////		if (roleList == null || roleList.size() == 0) {
////			return ERROR;
////		}
////		admin.setUsername(admin.getUsername().toLowerCase());
////		admin.setLoginFailureCount(0);
////		admin.setIsAccountLocked(false);
////		admin.setIsAccountExpired(false);
////		admin.setIsCredentialsExpired(false);
////		admin.setRoleSet(new HashSet<Roles>(roleList));
////		String passwordMd5 = DigestUtils.md5Hex(admin.getPassword());
////		admin.setPassword(passwordMd5);
////		adminService.save(admin);
////		redirectionUrl = "admin!list.action";
////		return SUCCESS;
////	}
////
////	// 更新
////	@Validations(
////		requiredStrings = {
////			@RequiredStringValidator(fieldName = "admin.username", message = "用户名不允许为空!"),
////			@RequiredStringValidator(fieldName = "admin.email", message = "E-mail不允许为空!")
////		},
////		requiredFields = {
////			@RequiredFieldValidator(fieldName = "admin.isAccountEnabled", message = "是否启用不允许为空!")
////		},
////		stringLengthFields = {
////			@StringLengthFieldValidator(fieldName = "admin.username", minLength = "2", maxLength = "20", message = "用户名长度必须在${minLength}到${maxLength}之间!"),
////			@StringLengthFieldValidator(fieldName = "admin.password", minLength = "4", maxLength = "20", message = "密码长度必须在${minLength}到${maxLength}之间!") },
////		emails = {
////			@EmailValidator(fieldName = "admin.email", message = "E-mail格式错误!")
////		},
////		regexFields = {
////			@RegexFieldValidator(fieldName = "admin.username", expression = "^[0-9a-z_A-Z\u4e00-\u9fa5]+$", message = "用户名只允许包含中文、英文、数字和下划线!") 
////		}
////	)
////	@InputConfig(resultName = "error")
////	public String update() {
////		Admin persistent = adminService.load(id);
////		if (roleList == null && roleList.size() == 0) {
////			addActionError("请至少选择一个角色!");
////			return ERROR;
////		}
////		admin.setRoleSet(new HashSet<Roles>(roleList));
////		if (StringUtils.isNotEmpty(admin.getPassword())) {
////			String passwordMd5 = DigestUtils.md5Hex(admin.getPassword());
////			persistent.setPassword(passwordMd5);
////		}
////		BeanUtils.copyProperties(admin, persistent, new String[] {"id", "createDate", "modifyDate", "username", "password", "isAccountLocked", "isAccountExpired", "isCredentialsExpired", "loginFailureCount", "lockedDate", "loginDate", "loginIp", "authorities"});
////		adminService.update(persistent);
////		redirectionUrl = "admin!list.action";
////		return SUCCESS;
////	}
//	
//	// 获取未处理订单数
//	public Long getUnprocessedOrderCount() {
//		return orderService.getUnprocessedOrderCount();
//	}
//	
//	// 获取已支付未发货订单数
//	public Long getPaidUnshippedOrderCount() {
//		return orderService.getPaidUnshippedOrderCount();
//	}
//	
//	// 获取未读消息数
//	public Long getUnreadMessageCount() {
//		return messageService.getUnreadMessageCount();
//	}
//	
//	// 获取商品库存报警数
//	public Long getStoreAlertCount() {
//		return productService.getStoreAlertCount();
//	}
//	
//	// 获取已上架商品数
//	public Long getMarketableProductCount() {
//		return productService.getMarketableProductCount();
//	}
//	
//	// 获取已下架商品数
//	public Long getUnMarketableProductCount() {
//		return productService.getUnMarketableProductCount();
//	}
//	
//	// 获取会员总数
//	public Long getMemberTotalCount() {
//		return memberService.getTotalCount();
//	}
//	
//	// 获取文章总数
//	public Long getArticleTotalCount() {
//		return articleService.getTotalCount();
//	}
//	
//	// freemarker静态方法调用
//	public TemplateHashModel getStatics() {
//		return BeansWrapper.getDefaultInstance().getStaticModels();
//	}
//	
//	public String getLoginUsername() {
//		return loginUsername;
//	}
//
//	public void setLoginUsername(String loginUsername) {
//		this.loginUsername = loginUsername;
//	}
//
//	public Admin getAdmin() {
//		return admin;
//	}
//
//	public void setAdmin(Admin admin) {
//		this.admin = admin;
//	}
//
//	public List<Roles> getAllRole() {
//		allRole = roleService.getAll();
//		return allRole;
//	}
//
//	public void setAllRole(List<Roles> allRole) {
//		this.allRole = allRole;
//	}
//
//	public List<Roles> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<Roles> roleList) {
//		this.roleList = roleList;
//	}
}
