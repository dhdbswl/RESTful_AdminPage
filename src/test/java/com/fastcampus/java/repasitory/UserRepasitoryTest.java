package com.fastcampus.java.repasitory;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.Item;
import com.fastcampus.java.model.entity.User;
import com.fastcampus.java.model.enumclass.UserStatus;
import com.fastcampus.java.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepasitoryTest extends JavaApplicationTests {

    // dependency injection (DI) 의존성 주입
    // 직접 객체를 만들지 않고 스프링이 객체를 직접 관리하고 주입을 시켜줌
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = insert into user(%s, %s, %d) value(account, email, age);
        // jpa 오브젝트를 가지고 데이터베이스를 관리할 수 있게끔 해줌.

        String account = "Test02";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test02@gmail.com";
        String phoneNumber = "010-2222-3333";
        LocalDateTime registerdAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "Adminserver";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registerdAt);
        /*user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);*/

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        // findById는 Optional형태로 리턴
        //Optional<User> user = userRepository.findById(2L); // Long 타입의 id=2

        // QueryMethod -> select * from user where id = ?
        //Optional<User> user = userRepository.findByAccount("TestUser03");

        // Optional은 option의 의미로, 데이터가 존재할 수도, 존재하지 않을 수도 있다.

        // 따라서 user.ifPresent를 사용하여 데이터가 존재한다면 값을 꺼내 사용함.
        /*user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });*/

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("------------주문 묶음------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("------------주문 상세------------");
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println(("파트너사 이름 : " + orderDetail.getItem().getPartner().getName()));
                    System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : "+orderDetail.getStatus());
                    System.out.println("도착예정일자 : "+orderDetail.getArrivalDate());
                });
            });
        }

        Assert.assertNotNull(user);

    }

    /*@Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("ppppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    // 예외를 발생시키면, 자동으로 롤백을 수행한다.
    // ex. delete처리에 있어서 마지막 데이터만 남아있을 때 롤백시켜 데이터를 삭제시키지 않고 남겨둔다.
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        // findById가 반드시 존재하는지(true) 테스트
        Assert.assertTrue(user.isPresent()); // true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        // 데이터 삭제 체크
        Optional<User> deleteUser = userRepository.findById(1L);

        // 반드시 삭제 되었는지(false) 뜨는지 테스트
        Assert.assertFalse(deleteUser.isPresent()); // false

//        if(deleteUser.isPresent()) {
//            System.out.println("데이터 존재 : "+deleteUser.get());
//        }else {
//            System.out.println("데이터 삭제 완료. 데이터 없음.");
//        }
    }*/
}
