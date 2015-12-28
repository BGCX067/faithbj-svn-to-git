package com.faithbj.shop.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.ForeignKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 实体类 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * @date 	2012-01-12
 * @version 1.0
 */

@Entity
public class Members extends BaseEntity implements UserDetails {
	private static final long serialVersionUID = -5222673112266471971L;
	
	
	public static final String LOGIN_MEMBER_ID_SESSION_NAME = "loginMemberId";// 保存登录会员ID的Session名称
	public static final String LOGIN_MEMBER_USERNAME_COOKIE_NAME = "loginMemberUsername";// 保存登录会员用户名的Cookie名称
	public static final String LOGIN_REDIRECTION_URL_SESSION_NAME = "redirectUrl";// 保存登录来源URL的Session名称
	public static final String PASSWORD_RECOVER_KEY_SEPARATOR = "_";// 密码找回Key分隔符
	public static final int PASSWORD_RECOVER_KEY_PERIOD = 10080;// 密码找回Key有效时间（单位：分钟）
	
	protected String username;// 用户名

	protected String password;// 密码

	protected String email;// E-mail

	protected String telephone;// 手机号

	protected String safeQuestion;// 密码保护问题

	protected String safeAnswer;// 密码保护问题答案

	protected Integer point=0;// 积分

	protected Boolean enabled=true;// 账号是否启用

	protected Boolean accountNonLocked=true;// 账号是否锁定

	protected Integer loginFailureCount=0;// 连续登录失败的次数

	protected Boolean credentialsNonExpired=true;// 凭证是否过期
	
	protected Set<Roles> roleSet;// 管理角色
	
	protected Collection<? extends GrantedAuthority> authorities;// 授权信息
	
	protected Date lockedDate;// 账号锁定日期

	protected String registerIp;// 注册IP

	protected String loginIp;// 最后登录IP

	protected Date loginDate;// 最后登录日期

	protected String passwordRecoverKey;// 密码找回Key

	protected MemberRank memberRank;// 会员等级
	
	protected Map<MemberAttribute, String> memberAttributeMapStore;// 会员注册项储存
	
	protected BigDecimal deposit;// 预存款
	
	protected Set<Receiver> receiverSet;// 收货地址
	
	protected Set<Product> favoriteProductSet;// 收藏夹商品
	
	protected Set<Product> favorProductSet;// 喜好商品
	
	protected Set<Product> hateProductSet;// 忌讳商品
	
	protected Set<CartItem> cartItemSet;// 通用购物车项

	protected Set<Message> inboxMessageSet;// 收件箱消息

	protected Set<Message> outboxMessageSet;// 发件箱消息

	protected Set<Order> orderSet;// 订单
	  
	protected Set<Deposit> deposits;// 预存款
	
	protected Set<Accounts> accounts;//会员的收获记录
	
	
	  
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	public Set<Accounts> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Accounts> accounts) {
		this.accounts = accounts;
	}
	
	
	@Column(updatable = false, nullable = false, unique = true)
	public String getUsername() {
		return username;
	}
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}
	@Column(nullable = false)	
	public String getEmail() {
		return email;
	}
	@Column(nullable = false)
	public String getTelephone() {
		return telephone;
	}
	@Column(nullable = true)
	public String getSafeQuestion() {
		return safeQuestion;
	}
	@Column(nullable = true)
	public String getSafeAnswer() {
		return safeAnswer;
	}
	@Column(nullable = false)
	public Integer getPoint() {
		return point;
	}
	@Column(nullable = false)	
	public Boolean getEnabled() {
		return enabled;
	}
	@Column
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	@Column(nullable = false)
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}
	@Column(nullable = false)	
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	@OrderBy("name asc")
	@ForeignKey(name="fk_member_role_set")
	public Set<Roles> getRoleSet() {
		return roleSet;
	}
	
	
	@Column(nullable = false, precision=15, scale=5)
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	@Column
	public Date getLockedDate() {
		return lockedDate;
	}
	@Column(nullable = false, updatable = false)
	public String getRegisterIp() {
		return registerIp;
	}	
	@Column(nullable = false)
	public String getLoginIp() {
		return loginIp;
	}
	@Column(nullable = false)
	public Date getLoginDate() {
		return loginDate;
	}
	@Column(nullable = false)
	public String getPasswordRecoverKey() {
		return passwordRecoverKey;
	}	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false,name="memberRank_id")
	@ForeignKey(name = "FK892776BA60FA1EDE")  
	public MemberRank getMemberRank() {
		return memberRank;
	}

    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="attributeOptionStore")
    @CollectionTable(name="memberattribute")
	public Map<MemberAttribute, String> getMemberAttributeMapStore() {
		return memberAttributeMapStore;
	}
	
	@OneToMany(mappedBy="member", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<Receiver> getReceiverSet() {
		return receiverSet;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@OrderBy("name desc")
	@ForeignKey(name="fk_member_favorite_goods_set")
	public Set<Product> getFavoriteProductSet() {
		return favoriteProductSet;
	}	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@OrderBy("name desc")
	@ForeignKey(name="fk_member_favor_goods_set")
	public Set<Product> getFavorProductSet() {
		return favorProductSet;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@OrderBy("name desc")
	@ForeignKey(name="fk_member_hate_goods_set")
	public Set<Product> getHateProductSet() {
		return hateProductSet;
	}

	@OneToMany(mappedBy="member", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
//	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
//	@Cascade({CascadeType.DELETE})
	@OrderBy("createDate desc")
	public Set<CartItem> getCartItemSet() {
		return cartItemSet;
	}

	@OneToMany(mappedBy="toMember", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<Message> getInboxMessageSet() {
		return inboxMessageSet;
	}

	@OneToMany(mappedBy="fromMember", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<Message> getOutboxMessageSet() {
		return outboxMessageSet;
	}		

	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	@OrderBy("createDate desc")
	public Set<Order> getOrderSet() {
		return orderSet;
	}

	@OneToMany(mappedBy="member", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<Deposit> getDeposits() {
		return deposits;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}	
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}		
	public void setRoleSet(Set<Roles> roleSet) {
		this.roleSet = roleSet;
	}	
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}	
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}	
	
	
	@Transient
	@Override
	public boolean isEnabled() {
		return enabled;
	}		
	@Transient
	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
		for (Roles role : roleSet)
			list.add(new SimpleGrantedAuthority(role.getValue()));
	    return list;
	}
	/**
	 * 账户永远不会过期
	 */
	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}	
	

	public void setMemberRank(MemberRank memberRank) {
		this.memberRank = memberRank;
	}

	public void setMemberAttributeMapStore(Map<MemberAttribute, String> memberAttributeMapStore) {
		this.memberAttributeMapStore = memberAttributeMapStore;
	}

	public void setCartItemSet(Set<CartItem> cartItemSet) {
		this.cartItemSet = cartItemSet;
	}
	
	public void setReceiverSet(Set<Receiver> receiverSet) {
		this.receiverSet = receiverSet;
	}
	
	public void setFavoriteProductSet(Set<Product> favoriteProductSet) {
		this.favoriteProductSet = favoriteProductSet;
	}
 

	public void setFavorProductSet(Set<Product> favorProductSet) {
		this.favorProductSet = favorProductSet;
	}
	
	public void setHateProductSet(Set<Product> hateProductSet) {
		this.hateProductSet = hateProductSet;
	}
	
	public void setInboxMessageSet(Set<Message> inboxMessageSet) {
		this.inboxMessageSet = inboxMessageSet;
	}


	public void setOutboxMessageSet(Set<Message> outboxMessageSet) {
		this.outboxMessageSet = outboxMessageSet;
	}


	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	// 获取会员注册项
	
	@SuppressWarnings("unchecked")
	@Transient
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}
	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}	
	public void setPoint(Integer point) {
		this.point = point;
	}

	
	public void setDeposits(Set<Deposit> deposits) {
		this.deposits = deposits;
//		this.deposit = SystemConfigUtil.getOrderScaleBigDecimal(deposit);
	}	

	
	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	
	@Transient
	public void onSave()
	{
		if ((this.point == null) || (this.point.intValue() < 0))
		      this.point = Integer.valueOf(0);
		    if ((this.deposit == null) || (this.deposit.compareTo(new BigDecimal(0)) < 0))
		      this.deposit = new BigDecimal(0);
		    if (this.enabled == null)
		      this.enabled = Boolean.valueOf(false);
		    if (this.accountNonLocked == null)
		      this.accountNonLocked = Boolean.valueOf(false);
		    if ((this.loginFailureCount == null) || (this.loginFailureCount.intValue() < 0))
		      this.loginFailureCount = Integer.valueOf(0);
	}

	@Transient
	public void onUpdate()
	{
		if ((this.point == null) || (this.point.intValue() < 0))
		      this.point = Integer.valueOf(0);
		    if ((this.deposit == null) || (this.deposit.compareTo(new BigDecimal(0)) < 0))
		      this.deposit = new BigDecimal(0);
		    if (this.enabled == null)
		      this.enabled = Boolean.valueOf(false);
		    if (this.accountNonLocked == null)
		      this.accountNonLocked = Boolean.valueOf(false);
		    if ((this.loginFailureCount == null) || (this.loginFailureCount.intValue() < 0))
		      this.loginFailureCount = Integer.valueOf(0);
	}
	
	
/*    public boolean equals(Object rhs) {
        if (!(rhs instanceof Members) || (rhs == null)) {
        	return false;
        }

        Members user = (Members) rhs;

        if (roleSet.equals(user.roleSet)) {
               return false;
         }

         return (this.getPassword().equals(user.getPassword()) && this.getUsername().equals(user.getUsername())
                   && (this.isAccountNonExpired() == user.isAccountNonExpired())
                   && (this.isAccountNonLocked() == user.isAccountNonLocked())
                   && (this.isCredentialsNonExpired() == user.isCredentialsNonExpired())
                   && (this.isEnabled() == user.isEnabled()));
       }

       public int hashCode() {
       
        int code = 9792;

           for (GrantedAuthority authority : getAuthorities()) {
               code = code * (authority.hashCode() % 7);
           }

           if (this.getPassword() != null) {
               code = code * (this.getPassword().hashCode() % 7);
           }

           if (this.getUsername() != null) {
               code = code * (this.getUsername().hashCode() % 7);
           }

           if (this.isAccountNonExpired()) {
               code = code * -2;
           }

           if (this.isAccountNonLocked()) {
               code = code * -3;
           }

           if (this.isCredentialsNonExpired()) {
               code = code * -5;
           }

           if (this.isEnabled()) {
               code = code * -7;
           }

           return code;
       }	
	*/
	
	
}
