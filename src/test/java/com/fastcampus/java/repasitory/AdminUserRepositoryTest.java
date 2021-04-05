package com.fastcampus.java.repasitory;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.AdminUser;
import com.fastcampus.java.model.enumclass.AdminUserStatus;
import com.fastcampus.java.repository.AdminUserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends JavaApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void created() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUSer03");
        adminUser.setPassword("AdminUSer03");
        adminUser.setStatus(AdminUserStatus.REGISTERED);
        adminUser.setRole("PARTNER");
        /*adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("AdminServer");*/

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);

        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);
    }
}
