package com.babymate;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import jakarta.persistence.ManyToOne;
import redis.clients.jedis.Jedis;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.babymate.cart.model.CartRepository;
import com.babymate.cart.model.CartVO;
import com.babymate.member.model.*;
import com.babymate.product.model.ProductVO;
import com.babymate.staff.model.*;

@SpringBootApplication
public class Test_Application_CommandLineRunner implements CommandLineRunner {
    
	@Autowired
	MemberRepository repository ;
	
	@Autowired
	StaffRepository repository1 ;
	
	@Autowired
	CartRepository repository2;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunner.class);
    }

    @Override
    public void run(String...args) throws Exception {

    	// Redis Test
//		Jedis jedis = new Jedis("localhost", 6379);
//		System.out.println(jedis.ping());
//		
//		jedis.select(15); // 切換到db15 避開不同人使用key衝突的問題
//		jedis.set("Hello","xxxxx");
//		
//		jedis.close();
    	
    	//● 新增
//    	account, password, name, email_verified, register_date, last_login_time, account_status, 
//      phone, recipient_name, address, gender, birthday, email_auth_token, pwd_reset_token, pwd_reset_expire)
//		Member member1 = new Member();
//		member1.setAccount("may@babymate.com");
//		member1.setPassword("123456");
//		member1.setName("李小美");
//		member1.setEmailVerified((byte) 0);
//		member1.setRegisterDate(LocalDateTime.of(2024, 1, 1, 0, 0));
//		member1.setLastLoginTime(LocalDateTime.now());
//		member1.setAccountStatus((byte)1);
//		member1.setPhone("0912123456");
//		member1.setRecipientName("李小美");
//		member1.setAddress("台北市南京東路一段100號10F");
//		member1.setGender(Gender.女);
//		member1.setBirthday(LocalDate.of(2000, 1, 2));
//		member1.setEmailAuthToken("emaitoken");
//		member1.setPwdResetToken("pwdreset");
//		member1.setPwdResetExpire(LocalDateTime.of(2025, 10, 1, 0, 0, 0));
//		member1.setUpdateDate(LocalDateTime.now());
//		repository.save(member1);
    	
    	// 新增Staff
//		('Mary', 'CSR Mary', '客服', 1, '414348ad7e2e65b87a8cba48ad557c84');
//    	StaffVO staff1 = new StaffVO();
//    	staff1.setAccount("Mary");
//    	staff1.setNickname("CSR Mary");
//    	staff1.setRole("客服");
//    	staff1.setStatus(0);
//    	staff1.setPassword("123456");
//    	staff1.setUpdateDate(LocalDateTime.now());
//    	repository1.save(staff1);
    	
    	// 新增Cart
//    	CartVO [cartId=8, member=3, product=18, quantity=3, updateTime=2025-09-05T10:55:45]
//    	CartVO cart = new CartVO();
//    	MemberVO member2 = new MemberVO();
//    	ProductVO product2 = new ProductVO();
//    	
//    	member2.setMemberId(3);
//    	cart.setMember(member2);
//    	
//    	product2.setProductId(2);
//    	cart.setProduct(product2);
//    	
//    	cart.setQuantity(2);
//    	cart.setUpdateTime(LocalDateTime.now());
//    	repository2.save(cart);
        
		//● 修改
//    	Member member2 = new Member();
//    	member2.setAccount("may1@babymate.com");
//    	member2.setPassword("1234567");
//    	member2.setName("李小美1");
//    	member2.setEmailVerified((byte) 1);
//    	member2.setRegisterDate(LocalDateTime.of(2024, 1, 1, 0, 0));
//		member2.setLastLoginTime(LocalDateTime.now());
//		member2.setAccountStatus((byte)1);
//		member2.setPhone("0922123456");
//		member2.setRecipientName("李小美1");
//		member2.setAddress("台北市南京東路一段100號11F");
//		member2.setGender(Gender.其他);
//		member2.setBirthday(LocalDate.of(2000, 1, 3));
//		member2.setEmailAuthToken("emaitoken1");
//		member2.setPwdResetToken("pwdreset1");
//		member2.setPwdResetExpire(LocalDateTime.of(2025, 11, 1, 0, 0, 0));
//		member2.setUpdateDate(LocalDateTime.now());
//		repository.save(member2);
		
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		repository.deleteByMemberId(11);
		
		//● 刪除   //XXX --> Repository內建的刪除方法目前無法使用，因為有@ManyToOne
		//System.out.println("--------------------------------");
		//repository.deleteById(7001);      
		//System.out.println("--------------------------------");

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<Member> optional = repository.findById(1);
//		Member member3 = optional.get();
//		System.out.print(member3.toString());      
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<Member> list = repository.findAll();
//		for (Member aMember : list) {
//			System.out.println(aMember.toString());
//			System.out.println();
//		}
    	
    	// 查詢 Staff-All
    	List<StaffVO> list = repository1.findAll();
    	for (StaffVO aStaff : list) {
    		System.out.println(aStaff.toString());
    	}
    	
    	// 查詢 Cart-All
//    	List<CartVO> list = repository2.findAll();
//    	for (CartVO aCart : list) {
//    		System.out.println(aCart.toString());
//    	}

		//● 複合查詢-getAll(map) 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "1" });
//		
//		List<EmpVO> list2 = HibernateUtil_CompositeQuery_Emp3.getAllC(map,sessionFactory.openSession());
//		for (EmpVO aEmp : list2) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
		

		//● (自訂)條件查詢
//		List<Member> list3 = repository.findByOthers(2,"%f%", LocalDateTime.of(2024, 7, 5, 11, 0, 0));
//		if(!list3.isEmpty()) {
//			for (Member aMember : list3) {
//				System.out.print(aMember.toString());
//			}
//		} else System.out.print("查無資料\n");
//		System.out.println("--------------------------------");

    }
}
