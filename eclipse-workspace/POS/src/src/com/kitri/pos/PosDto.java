package com.kitri.pos;

public class PosDto {

	// �ʵ� ����

	private int USER_CODE; // �����ڵ�
	private String name; // �̸�
	private String attendCode; // ����ڵ�
	private String loginTime; // ��ٽð�
	private String logoutTime; // ��ٽð�
	private String workTime; // �ٹ��ð�
	private String pw; // �н�����
	private String id; // ���̵�
	private String authority; // ����
	// ===================================//
	private String cooperateName; // ���޻�
	private String company; // ������
	private String productCode; // ��ǰ�ڵ�
	private String productName; // ��ǰ�̸�
	private String levelCode; // �з��ڵ�
	private String majorLevel; // ��з�
	private String mediumLevel; // �ߺз�
	private String minorLevel; // �Һз�
	private int price; // ����
	private int purchase; // ���԰�
	private int volume; // ����
	private String realExp; // �������
	// ===================================//
	private String sellId; // �Ǹž��̵�
	private String sellDate; // �Ǹ�����
	private int sellCount; // �Ǹż���
	private String countDate; // ���곯¥
	private int comsCalc; // ���ݰ����ݾ�
	private int cashPrice;
	private String discountPct; // ������
	private int currentMoney; // �������
	private int totalCalc; // ����
	private String phone; // �ڵ�����ȣ
	private int point; // ����Ʈ
	private String payment;
	private int cardPrice; // ī������ݾ�
	private String inDate; // �԰�¥
	private int totalPrice;
	private String discountCode; // �����ڵ�
	private String membershipId; // �ɹ������̵�
	// =====================================//
	private int ranking;           // �������
	private int statTotalPrice;   // �����հ�
	private int totalTax;           // �ΰ����հ�
	private int customerCount; // ����

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getStatTotalPrice() {
		return statTotalPrice;
	}

	public void setStatTotalPrice(int statTotalPrice) {
		this.statTotalPrice = statTotalPrice;
	}

	public int getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(int totalTax) {
		this.totalTax = totalTax;
	}

	public int getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserCode() {
		return USER_CODE;
	}

	public void setUserCode(int USER_CODE) {
		this.USER_CODE = USER_CODE;
	}

	public String getAttendCode() {
		return attendCode;
	}

	public void setAttendCode(String attendCode) {
		this.attendCode = attendCode;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getCooperateName() {
		return cooperateName;
	}

	public void setCooperateName(String cooperateName) {
		this.cooperateName = cooperateName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getMajorLevel() {
		return majorLevel;
	}

	public void setMajorLevel(String majorLevel) {
		this.majorLevel = majorLevel;
	}

	public String getMediumLevel() {
		return mediumLevel;
	}

	public void setMediumLevel(String mediumLevel) {
		this.mediumLevel = mediumLevel;
	}

	public String getMinorLevel() {
		return minorLevel;
	}

	public void setMinorLevel(String minorLevel) {
		this.minorLevel = minorLevel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPurchase() {
		return purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getRealExp() {
		return realExp;
	}

	public void setRealExp(String realExp) {
		this.realExp = realExp;
	}

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

	public int getComsCalc() {
		return comsCalc;
	}

	public void setComsCalc(int comsCalc) {
		this.comsCalc = comsCalc;
	}

	public int getCashPrice() {
		return cashPrice;
	}

	public void setCashPrice(int cashPrice) {
		this.cashPrice = cashPrice;
	}

	public String getDiscountPct() {
		return discountPct;
	}

	public void setDiscountPct(String discountPct) {
		this.discountPct = discountPct;
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}

	public int getTotalCalc() {
		return totalCalc;
	}

	public void setTotalCalc(int totalCalc) {
		this.totalCalc = totalCalc;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public int getCardPrice() {
		return cardPrice;
	}

	public void setCardPrice(int cardPrice) {
		this.cardPrice = cardPrice;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

}