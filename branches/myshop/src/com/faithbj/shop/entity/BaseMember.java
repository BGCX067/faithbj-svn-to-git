package com.faithbj.shop.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;

import com.faithbj.shop.util.SystemConfigUtil;

/**
 * 实体类 - 会员基类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-12
 * @version 1.0
 */

public class BaseMember extends BaseEntity
{

	private static final long serialVersionUID = 1533130686714725835L;
	
	public static final String LOGIN_MEMBER_ID_SESSION_NAME = "loginMemberId";// 保存登录会员ID的Session名称
	public static final String LOGIN_MEMBER_USERNAME_COOKIE_NAME = "loginMemberUsername";// 保存登录会员用户名的Cookie名称
	public static final String LOGIN_REDIRECTION_URL_SESSION_NAME = "redirectionUrl";// 保存登录来源URL的Session名称
	public static final String PASSWORD_RECOVER_KEY_SEPARATOR = "_";// 密码找回Key分隔符
	public static final int PASSWORD_RECOVER_KEY_PERIOD = 10080;// 密码找回Key有效时间（单位：分钟）
	
	private String username;// 用户名
	private String password;// 密码
	private String email;// E-mail
	private String safeQuestion;// 密码保护问题
	private String safeAnswer;// 密码保护问题答案
	private Integer point;// 积分
	private BigDecimal deposit;// 预存款
	private Boolean isAccountEnabled;// 账号是否启用
	private Boolean isAccountLocked;// 账号是否锁定
	private Integer loginFailureCount;// 连续登录失败的次数
	private Date lockedDate;// 账号锁定日期
	private String registerIp;// 注册IP
	private String loginIp;// 最后登录IP
	private Date loginDate;// 最后登录日期
	private String passwordRecoverKey;// 密码找回Key
	
	private MemberRank memberRank;// 会员等级
	private Map<MemberAttribute, String> memberAttributeMapStore;// 会员注册项储存
	private Set<Receiver> receiverSet;// 收货地址
	private Set<Product> favoriteProductSet;// 收藏夹商品
	private Set<CartItem> cartItemSet;// 购物车项
	private Set<Message> inboxMessageSet;// 收件箱消息
	private Set<Message> outboxMessageSet;// 发件箱消息
	private Set<Order> orderSet;// 订单
	private Set<Deposit> depositSet;// 预存款

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSafeQuestion() {
		return safeQuestion;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}

	public String getSafeAnswer() {
		return safeAnswer;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}
	
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	// 精度处理
	public void setDeposit(BigDecimal deposit) {
		this.deposit = SystemConfigUtil.getOrderScaleBigDecimal(deposit);
	}
	
	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	
	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	
	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	public String getPasswordRecoverKey() {
		return passwordRecoverKey;
	}

	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}

	public MemberRank getMemberRank() {
		return memberRank;
	}

	public void setMemberRank(MemberRank memberRank) {
		this.memberRank = memberRank;
	}
	
	public Map<MemberAttribute, String> getMemberAttributeMapStore() {
		return memberAttributeMapStore;
	}

	public void setMemberAttributeMapStore(Map<MemberAttribute, String> memberAttributeMapStore) {
		this.memberAttributeMapStore = memberAttributeMapStore;
	}

	public Set<Receiver> getReceiverSet() {
		return receiverSet;
	}

	public void setReceiverSet(Set<Receiver> receiverSet) {
		this.receiverSet = receiverSet;
	}
	
	public Set<Product> getFavoriteProductSet() {
		return favoriteProductSet;
	}

	public void setFavoriteProductSet(Set<Product> favoriteProductSet) {
		this.favoriteProductSet = favoriteProductSet;
	}

	public Set<CartItem> getCartItemSet() {
		return cartItemSet;
	}

	public void setCartItemSet(Set<CartItem> cartItemSet) {
		this.cartItemSet = cartItemSet;
	}

	public Set<Message> getInboxMessageSet() {
		return inboxMessageSet;
	}

	public void setInboxMessageSet(Set<Message> inboxMessageSet) {
		this.inboxMessageSet = inboxMessageSet;
	}

	public Set<Message> getOutboxMessageSet() {
		return outboxMessageSet;
	}

	public void setOutboxMessageSet(Set<Message> outboxMessageSet) {
		this.outboxMessageSet = outboxMessageSet;
	}

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	public Set<Deposit> getDepositSet() {
		return depositSet;
	}

	public void setDepositSet(Set<Deposit> depositSet) {
		this.depositSet = depositSet;
	}

	// 获取会员注册项
	public Map<MemberAttribute, List<String>> getMemberAttributeMap() {
		if (memberAttributeMapStore == null || memberAttributeMapStore.size() == 0) {
			return null;
		}
		Map<MemberAttribute, List<String>> memberAttributeMap = new HashMap<MemberAttribute, List<String>>();
		for (MemberAttribute memberAttribute : memberAttributeMapStore.keySet()) {
			String memberAttributeValueStore = memberAttributeMapStore.get(memberAttribute);
			if (StringUtils.isNotEmpty(memberAttributeValueStore)) {
				JSONArray jsonArray = JSONArray.fromObject(memberAttributeMapStore.get(memberAttribute));
				memberAttributeMap.put(memberAttribute, (List<String>) JSONSerializer.toJava(jsonArray));
			} else {
				memberAttributeMap.put(memberAttribute, null);
			}
		}
		return memberAttributeMap;
	}

	// 设置会员注册项
	public void setMemberAttributeMap(Map<MemberAttribute, List<String>> memberAttributeMap) {
		if (memberAttributeMap == null || memberAttributeMap.size() == 0) {
			memberAttributeMapStore = null;
			return;
		}
		Map<MemberAttribute, String> memberAttributeMapStore = new HashMap<MemberAttribute, String>();
		for (MemberAttribute memberAttribute : memberAttributeMap.keySet()) {
			List<String> memberAttributeValueList = memberAttributeMap.get(memberAttribute);
			if (memberAttributeValueList != null && memberAttributeValueList.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(memberAttributeValueList);
				memberAttributeMapStore.put(memberAttribute, jsonArray.toString());
			} else {
				memberAttributeMapStore.put(memberAttribute, "");
			}
		}
		this.memberAttributeMapStore = memberAttributeMapStore;
	}

}


//@Entity
//public class Member extends BaseMember {
//
//	//--------------------------------------------------------------------
//	//original code, don't modify
//	//--------------------------------------------------------------------
//	private static final long serialVersionUID = -5222673112266471971L;
//
//	@Column(updatable = false, nullable = false, unique = true)
//	public String getUsername() {
//		return super.getUsername();
//	}
//
//	@Column(nullable = false)
//	public String getPassword() {
//		return super.getPassword();
//	}
//	
//	@Column(nullable = false)
//	public String getEmail() {
//		return super.getEmail();
//	}
//	
//	@Column(nullable = false)
//	public Integer getPoint() {
//		return super.getPoint();
//	}
//
//
//	@Column(precision = 15, scale = 5, nullable = false)
//	public BigDecimal getDeposit() {
//		return super.getDeposit();
//	}
//
//	@Column(nullable = false)
//	public Boolean getIsAccountEnabled() {
//		return super.getIsAccountEnabled();
//	}
//
//	@Column(nullable = false)
//	public Boolean getIsAccountLocked() {
//		return super.getIsAccountLocked();
//	}
//
//	@Column(nullable = false)
//	public Integer getLoginFailureCount() {
//		return super.getLoginFailureCount();
//	}
//	
//	@Column(nullable = false, updatable = false)
//	public String getRegisterIp() {
//		return super.getRegisterIp();
//	}
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable = false)
//	public MemberRank getMemberRank() {
//		return super.getMemberRank();
//	}
//	
//	@CollectionOfElements
//	@MapKey(targetElement = MemberAttribute.class)
//	@LazyCollection(LazyCollectionOption.TRUE)
//	@Cascade(value = { CascadeType.DELETE })
//	public Map<MemberAttribute, String> getMemberAttributeMapStore() {
//		return super.getMemberAttributeMapStore();
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
//	@Cascade(value = { CascadeType.DELETE })
//	@OrderBy("createDate desc")
//	public Set<Receiver> getReceiverSet() {
//		return super.getReceiverSet();
//	}
//	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@OrderBy("name desc")
//	public Set<Product> getFavoriteProductSet() {
//		return super.getFavoriteProductSet();
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
//	@Cascade(value = { CascadeType.DELETE })
//	@OrderBy("createDate desc")
//	public Set<CartItem> getCartItemSet() {
//		return super.getCartItemSet();
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "toMember")
//	@Cascade(value = { CascadeType.DELETE })
//	@OrderBy("createDate desc")
//	public Set<Message> getInboxMessageSet() {
//		return super.getInboxMessageSet();
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fromMember")
//	@Cascade(value = { CascadeType.DELETE })
//	@OrderBy("createDate desc")
//	public Set<Message> getOutboxMessageSet() {
//		return super.getOutboxMessageSet();
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
//	@OrderBy("createDate desc")
//	public Set<Order> getOrderSet() {
//		return super.getOrderSet();
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
//	@Cascade(value = { CascadeType.DELETE })
//	@OrderBy("createDate desc")
//	public Set<Deposit> getDepositSet() {
//		return super.getDepositSet();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Transient
//	public Map<MemberAttribute, List<String>> getMemberAttributeMap() {
//		return super.getMemberAttributeMap();
//	}
//
//	@Transient
//	public void setMemberAttributeMap(Map<MemberAttribute, List<String>> memberAttributeMap) {
//		super.setMemberAttributeMap(memberAttributeMap);
//	}
//
//}
