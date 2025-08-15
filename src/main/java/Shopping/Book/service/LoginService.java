package Shopping.Book.service;

import Shopping.Book.entity.MemberEntity;
import Shopping.Book.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {

        Optional<MemberEntity> memberEntity =
                loginRepository.findByMemberEmail(memberEmail);

        if(memberEntity.isPresent()) {

            return User.withUsername(memberEntity.get().getMemberEmail())
                    //passwordEncoder 비밀번호 보안작업
                    .password(passwordEncoder.encode(memberEntity.get().getMemberPassword()))
                    .roles(memberEntity.get().getRoleType().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }
    }

    public Map<String, String > findEmail(String memberPassword, String memberName) {
        Optional<MemberEntity> memberEntity = loginRepository.findByMemberPasswordAndMemberName(memberPassword, memberName);

        if (memberEntity.isEmpty()) {
            throw new UsernameNotFoundException("비밀번호 또는 이름이 일치하지 않습니다.");
        }

        Map<String, String> result = new HashMap<>();
        result.put("memberEmail", memberEntity.get().getMemberEmail());

        return result;
    }
}
