package com.szy.session;

import com.alibaba.fastjson.annotation.JSONField;
import com.szy.util.MD5Util;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Session {

	@JSONField(name="n")
	private long number;//用户名
	@JSONField(name="na")
	private String name;  //姓名
	@JSONField(name="p")
	private String passwd;//密码
	@JSONField(name="ph")
	private String telePhone;
	@JSONField(name="t")
	private long loginTime; //服务器时间
	@JSONField(name="c")
	private int category;//大类
	@JSONField(name="g")
	private int grade;//年级
	@JSONField(name="v")
	private String verify; //校验位
	@JSONField(name="l")
	private int limit; //校验位
	private transient String key;

	private static String prefix = "^Sess_io@n";
	private static Random random = new Random();
	//todo:临时方式，加密方式后期改动
	public String createSessionKey(long number) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		verify =  String.format("%08x", random.nextInt(0x7FFFFFFF)) ;
		key = MD5Util.md5(prefix + number);
		return key + verify;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
