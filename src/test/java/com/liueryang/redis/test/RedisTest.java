package com.liueryang.redis.test;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liueryang.entity.User;
import com.liueryang.utils.CmsRandom;
import com.liueryang.utils.StringUtils;
/**
 * @author Dnfer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis.xml")
public class RedisTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 3.模拟生成5万条User对象（22分）
		(1)ID使用1-5万的顺序号。（2分）
		(2)姓名使用3个随机汉字模拟，
		可以使用以前自己编写的工具方法。（4分）
		(3)性别在女和男两个值中随机。（4分）
		(4)手机以13开头+9位随机数模拟。（4分）
		(5)邮箱以3-20个随机字母 + @qq.com  
		| @163.com | 
		@sian.com | 
		@gmail.com | 
		@sohu.com | 
		@hotmail.com | 
		@foxmail.com模拟。（4分）
		(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
		以上随机值需要调用以前编写的随机数工具方法，如果没有使用以前的工具方法，现场通过代码解决的，总体扣4分。
		以上5万条数据除性别外不可能有重复数据。
	 */
	@Test
	public void JdkRedis() {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			int flag = (int)Math.random()*10;
			if (flag>5) {
				user.setGender("男");
			}else {
				user.setGender("女");
			}
			String phone="13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			int emailflag = CmsRandom.getRandomInt(3,20);
			String emailnum = StringUtils.getRandomNumber(emailflag);
			int flag2 = (int)Math.random()*10;
			if (flag2==1) {
				user.setEmail(emailnum+"@qq.com");
			}else if (flag2==2) {
				user.setEmail(emailnum+"@163.com");
			}else if (flag2==3) {
				user.setEmail(emailnum+"@sian.com");
			}else if (flag2==4) {
				user.setEmail(emailnum+"@gmail.com");
			}else if (flag2==5) {
				user.setEmail(emailnum+"@hotmail.com");
			}else if (flag2==6) {
				user.setEmail(emailnum+"@sohu.com");
			}else{
				user.setEmail(emailnum+"@foxmail.com");
			}
			Integer yeari = CmsRandom.getRandomInt(1949,2001);
			String year = yeari.toString();
			Integer mi = CmsRandom.getRandomInt(1,12);
			String m = mi.toString();
			if (mi==2) {
				Integer days = CmsRandom.getRandomInt(1,28);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}else if (mi==1||mi==3||mi==5||mi==7||mi==8||mi==10||mi==12) {
				Integer days = CmsRandom.getRandomInt(1,31);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}else {
				Integer days = CmsRandom.getRandomInt(1,30);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}
			redisTemplate.opsForValue().set("user"+i,user);
		}
		long end = System.currentTimeMillis();
		long  time =  end-start;
		System.out.println("这是JDK的序列化方式");
		System.out.println("保存了50000条数据");
		System.out.println("耗时"+time+"毫秒");
	}
	
	
	
	@Test
	public void JsonRedis() {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			int flag = (int)Math.random()*10;
			if (flag>5) {
				user.setGender("男");
			}else {
				user.setGender("女");
			}
			String phone="13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			int emailflag = CmsRandom.getRandomInt(3,20);
			String emailnum = StringUtils.getRandomNumber(emailflag);
			int flag2 = (int)Math.random()*10;
			if (flag2==1) {
				user.setEmail(emailnum+"@qq.com");
			}else if (flag2==2) {
				user.setEmail(emailnum+"@163.com");
			}else if (flag2==3) {
				user.setEmail(emailnum+"@sian.com");
			}else if (flag2==4) {
				user.setEmail(emailnum+"@gmail.com");
			}else if (flag2==5) {
				user.setEmail(emailnum+"@hotmail.com");
			}else if (flag2==6) {
				user.setEmail(emailnum+"@sohu.com");
			}else{
				user.setEmail(emailnum+"@foxmail.com");
			}
			Integer yeari = CmsRandom.getRandomInt(1949,2001);
			String year = yeari.toString();
			Integer mi = CmsRandom.getRandomInt(1,12);
			String m = mi.toString();
			if (mi==2) {
				Integer days = CmsRandom.getRandomInt(1,28);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}else if (mi==1||mi==3||mi==5||mi==7||mi==8||mi==10||mi==12) {
				Integer days = CmsRandom.getRandomInt(1,31);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}else {
				Integer days = CmsRandom.getRandomInt(1,30);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}
			redisTemplate.opsForValue().set("user"+i,user);
		}
		long end = System.currentTimeMillis();
		long  time =  end-start;
		System.out.println("这是Json的序列化方式");
		System.out.println("保存了50000条数据");
		System.out.println("耗时"+time+"毫秒");
	}
	
	
	@Test
	public void HashRedis() {
		long start = System.currentTimeMillis();
		HashMap<String, User> map = new HashMap<>();
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			int flag = (int)Math.random()*10;
			if (flag>5) {
				user.setGender("男");
			}else {
				user.setGender("女");
			}
			String phone="13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			int emailflag = CmsRandom.getRandomInt(3,20);
			String emailnum = StringUtils.getRandomNumber(emailflag);
			int flag2 = (int)Math.random()*10;
			if (flag2==1) {
				user.setEmail(emailnum+"@qq.com");
			}else if (flag2==2) {
				user.setEmail(emailnum+"@163.com");
			}else if (flag2==3) {
				user.setEmail(emailnum+"@sian.com");
			}else if (flag2==4) {
				user.setEmail(emailnum+"@gmail.com");
			}else if (flag2==5) {
				user.setEmail(emailnum+"@hotmail.com");
			}else if (flag2==6) {
				user.setEmail(emailnum+"@sohu.com");
			}else{
				user.setEmail(emailnum+"@foxmail.com");
			}
			Integer yeari = CmsRandom.getRandomInt(1949,2001);
			String year = yeari.toString();
			Integer mi = CmsRandom.getRandomInt(1,12);
			String m = mi.toString();
			if (mi==2) {
				Integer days = CmsRandom.getRandomInt(1,28);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}else if (mi==1||mi==3||mi==5||mi==7||mi==8||mi==10||mi==12) {
				Integer days = CmsRandom.getRandomInt(1,31);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}else {
				Integer days = CmsRandom.getRandomInt(1,30);
				String day = days.toString();
				user.setAge(year+"-"+m+"-"+day);
			}
			map.put("user"+i,user);
		}
		redisTemplate.opsForHash().putAll("users",map);
		long end = System.currentTimeMillis();
		long  time =  end-start;
		System.out.println("这是hash的序列化方式");
		System.out.println("保存了50000条数据");
		System.out.println("耗时"+time+"毫秒");
	}
	
}
