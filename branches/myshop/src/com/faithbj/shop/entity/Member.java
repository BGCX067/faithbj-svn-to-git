package com.faithbj.shop.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.MapKey;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.entity.FieldBlockCartItem;
import com.faithbj.custom.vegetable.entity.MemberAuth;
import com.faithbj.custom.vegetable.entity.RendBlock;

/**
 * 实体类 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-12
 * @version 1.0
 */

@Entity
public class Member extends BaseMember {

	//--------------------------------------------------------------------
	//vegetable member stuff
	//--------------------------------------------------------------------
	private List<RendBlock> rendBlockList = null;
	private List<MemberAuth> authList = null;
	
	private Set<FieldBlock> favoriteFieldBlockSet;// 收藏夹租赁块
	private Set<FieldBlockCartItem> fieldBlockCartItemSet;// 购物车项
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	public List<RendBlock> getRendBlockList()
	{
		return rendBlockList;
	}
	public void setRendBlockList(List<RendBlock> rendBlockList)
	{
		this.rendBlockList = rendBlockList;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	public List<MemberAuth> getAuthList()
	{
		return authList;
	}
	public void setAuthList(List<MemberAuth> authList)
	{
		this.authList = authList;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	public Set<FieldBlock> getFavoriteFieldBlockSet()
	{
		return favoriteFieldBlockSet;
	}
	public void setFavoriteFieldBlockSet(Set<FieldBlock> favoriteFieldBlockSet)
	{
		this.favoriteFieldBlockSet = favoriteFieldBlockSet;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<FieldBlockCartItem> getFieldBlockCartItemSet()
	{
		return fieldBlockCartItemSet;
	}
	public void setFieldBlockCartItemSet(
			Set<FieldBlockCartItem> fieldBlockCartItemSet)
	{
		this.fieldBlockCartItemSet = fieldBlockCartItemSet;
	}
	
	//--------------------------------------------------------------------
	//original code, don't modify
	//--------------------------------------------------------------------
	private static final long serialVersionUID = -5222673112266471971L;

	@Column(updatable = false, nullable = false, unique = true)
	public String getUsername() {
		return super.getUsername();
	}

	@Column(nullable = false)
	public String getPassword() {
		return super.getPassword();
	}
	
	@Column(nullable = false)
	public String getEmail() {
		return super.getEmail();
	}
	
	@Column(nullable = false)
	public Integer getPoint() {
		return super.getPoint();
	}


	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getDeposit() {
		return super.getDeposit();
	}

	@Column(nullable = false)
	public Boolean getIsAccountEnabled() {
		return super.getIsAccountEnabled();
	}

	@Column(nullable = false)
	public Boolean getIsAccountLocked() {
		return super.getIsAccountLocked();
	}

	@Column(nullable = false)
	public Integer getLoginFailureCount() {
		return super.getLoginFailureCount();
	}
	
	@Column(nullable = false, updatable = false)
	public String getRegisterIp() {
		return super.getRegisterIp();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public MemberRank getMemberRank() {
		return super.getMemberRank();
	}
	
	@CollectionOfElements
	@MapKey(targetElement = MemberAttribute.class)
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { CascadeType.DELETE })
	public Map<MemberAttribute, String> getMemberAttributeMapStore() {
		return super.getMemberAttributeMapStore();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<Receiver> getReceiverSet() {
		return super.getReceiverSet();
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@OrderBy("name desc")
	public Set<Product> getFavoriteProductSet() {
		return super.getFavoriteProductSet();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<CartItem> getCartItemSet() {
		return super.getCartItemSet();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "toMember")
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<Message> getInboxMessageSet() {
		return super.getInboxMessageSet();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fromMember")
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<Message> getOutboxMessageSet() {
		return super.getOutboxMessageSet();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@OrderBy("createDate desc")
	public Set<Order> getOrderSet() {
		return super.getOrderSet();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<Deposit> getDepositSet() {
		return super.getDepositSet();
	}

	@SuppressWarnings("unchecked")
	@Transient
	public Map<MemberAttribute, List<String>> getMemberAttributeMap() {
		return super.getMemberAttributeMap();
	}

	@Transient
	public void setMemberAttributeMap(Map<MemberAttribute, List<String>> memberAttributeMap) {
		super.setMemberAttributeMap(memberAttributeMap);
	}
	

}
