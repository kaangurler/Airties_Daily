package com.daily.bean;


public class DailyDataBean {}

/*
@RequiredArgsConstructor
@Configuration
public class RegisterDataBean {
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;
    @Bean
    CommandLineRunner speedRegister(IRegisterRepository repository){
        return (args)->{
            for (int i = 1; i <=5 ; i++) {
                UUID uuid=UUID.randomUUID();
                RegisterDto registerDto=RegisterDto.builder()
                        .email(uuid.toString().concat("@hotmail.com"))
                        .password(passwordEncoderBean.passwordEncoderMethod().encode("root"+i))
                        .name("adı"+i)
                        .surname("soyadı"+i)
                        .createdDate(new Date(System.currentTimeMillis() ))
                        .build();
                RegisterEntity registerEntity=modelMapperBean.modelMapperMethod().map(registerDto,RegisterEntity.class);
                repository.save(registerEntity);
            }

        };
    }
}
*/
