package com.web.board.user.service;

import com.web.board.user.dao.UserRepository;
import com.web.board.user.dto.UserDto;
import com.web.board.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    /*
    * @param id 아이디
    * @return UserDetilas
    * @throws UserNameNotFoundException 사용자가 없을 때 예외 발생
    * */

    @Override
    //기본적인 반환 타입은 UserDetails
    //UserDetails를 상속받은 UserEntity로 반환 타입 지정(자동으로 다운 캐스팅 됨)
    public UserEntity loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id));
    }


    /*
    * 회원정보 저장
    * @param UserEntity 회원정보가 들어있는 DTO
    * @return 저장되는 회원의 PK
    * */
    public Long save(UserDto userDto){
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       userDto.setPassword(encoder.encode(userDto.getPassword()));

       return userRepository.save(UserEntity.builder()
               .id(userDto.getId())
               .name(userDto.getName())
               .password(userDto.getPassword()).build()).getIdx();
    }
}
