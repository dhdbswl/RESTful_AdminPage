package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.User;
import com.fastcampus.java.model.enumclass.UserStatus;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.UserApiRequest;
import com.fastcampus.java.model.network.response.UserApiResponse;
import com.fastcampus.java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    // 1. request data 가져오기
    // 2. user 생성
    // 3. 생성된 data를 기준으로 UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 data를 기준으로 UserApiResponse return
        // user객체를 가지고 데이터를 만들기 때문에 다른 메소드에서도 사용할 수 있도록 별도로 메소드 설정
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id를 가지고 repository를 통해서 getOne or getById를 통해서 데이터 가져옴
        // Optional<User> optional = userRepository.findById(id);

        // user객체를 가지고 UserApiResponse return
        // user가 존재한다면 map(다른 return형태로 바꿈), user가 없다면 error return
        return baseRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id를 가지고 user 데이터 찾기
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());
        return optional
                .map(user -> {
                    // 3. data를 가지고 update 시키기
                    user
                        .setAccount(userApiRequest.getAccount())
                        .setPassword(userApiRequest.getPassword())
                        .setStatus(userApiRequest.getStatus())
                        .setPhoneNumber(userApiRequest.getPhoneNumber())
                        .setEmail(userApiRequest.getEmail())
                        .setRegisteredAt(userApiRequest.getRegisteredAt())
                        .setUnregisteredAt(userApiRequest.getUnregisteredAt());

                    return user;
                })
                .map(user -> baseRepository.save(user)) // update -> newUser객체 반환
                .map(updateUser -> response(updateUser)) // 4. userApiResponse 만들기
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id를 가지고 repository를 통해서 user 찾기
        Optional<User> optional = baseRepository.findById(id);

        // 2. repository를 통해서 delete
        // 3. response return
        return optional
                .map(user -> {
                    baseRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    // user객체를 가지고 userApiResponse를 만들어서 return하는 메소드
    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Headr + data return
        return  Header.OK(userApiResponse);
    }
}
