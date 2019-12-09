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
	 * 3.ģ������5����User����22�֣�
		(1)IDʹ��1-5���˳��š���2�֣�
		(2)����ʹ��3���������ģ�⣬
		����ʹ����ǰ�Լ���д�Ĺ��߷�������4�֣�
		(3)�Ա���Ů��������ֵ���������4�֣�
		(4)�ֻ���13��ͷ+9λ�����ģ�⡣��4�֣�
		(5)������3-20�������ĸ + @qq.com  
		| @163.com | 
		@sian.com | 
		@gmail.com | 
		@sohu.com | 
		@hotmail.com | 
		@foxmail.comģ�⡣��4�֣�
		(6)����Ҫģ��18-70��֮�䣬�����ڴ�1949�굽2001��֮�䡣��4�֣�
		�������ֵ��Ҫ������ǰ��д����������߷��������û��ʹ����ǰ�Ĺ��߷������ֳ�ͨ���������ģ������4�֡�
		����5�������ݳ��Ա��ⲻ�������ظ����ݡ�
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
				user.setGender("��");
			}else {
				user.setGender("Ů");
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
		System.out.println("����JDK�����л���ʽ");
		System.out.println("������50000������");
		System.out.println("��ʱ"+time+"����");
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
				user.setGender("��");
			}else {
				user.setGender("Ů");
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
		System.out.println("����Json�����л���ʽ");
		System.out.println("������50000������");
		System.out.println("��ʱ"+time+"����");
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
				user.setGender("��");
			}else {
				user.setGender("Ů");
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
		System.out.println("����hash�����л���ʽ");
		System.out.println("������50000������");
		System.out.println("��ʱ"+time+"����");
	}
	
}
