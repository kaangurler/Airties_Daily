package com.daily.ui.mvc.impl;

import com.daily.bean.ModelMapperBean;
import com.daily.bean.PasswordEncoderBean;
import com.daily.business.dto.DailyDto;
import com.daily.data.entity.DailyEntity;
import com.daily.data.repository.IDailyRepository;
import com.daily.exception.ResourceNotFoundException;
import com.daily.ui.mvc.IDailyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//lombok
@RequiredArgsConstructor
@Log4j2

//Controller
@Controller
//@RequestMapping("/controller")
public class DailyController implements IDailyController {


    //Inject
    private final IDailyRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // SPEED DATA
    // http://localhost:2222/speedData
    @Override
    @GetMapping("/speedData")
    public String createSpeedData(Model model) {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            UUID uuid = UUID.randomUUID();
            DailyEntity registerEntity = DailyEntity.builder()
                    .dailyHeader("başlık " + i).dailyContent("içerik "+i)
                    .password("Hm1234"+i)
                    .email(uuid.toString().concat("@gmail.com")).build();
            repository.save(registerEntity);
            counter++;
        }
        model.addAttribute("key_dataset", counter + " tane daily Entity oluşturuldu");
        return "redirect:/daily/list";
    }

    // SPEED DELETE
    // http://localhost:2222/speedData
    @Override
    @GetMapping("/speedDelete")
    public String deleteSpeedData(Model model) {
        repository.deleteAll();
        return "redirect:/daily/list";
    }



    // CREATE 2497-2588
    // http://localhost:2222/daily/create
    @Override
    @GetMapping("/daily/create")
    public String validationGetDaily(Model model) {
        model.addAttribute("key_daily", new DailyDto());
        return "daily_create";
    }

    //CREATE
    // http://localhost:1111/daily/create
    @Override
    @PostMapping("/daily/create")
    public String validationPostDaily(@Valid @ModelAttribute("key_daily") DailyDto dailyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "daily_create";
        }
        //eğer valiadtion bir hata yoksa
        model.addAttribute("daily_success", "Üye Kaydı Başarılı " + dailyDto);
        log.info("Başarılı " + dailyDto);

        //Database
        //masking password
        dailyDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(dailyDto.getPassword()));

        //model mapper
        DailyEntity registerEntity = modelMapperBean.modelMapperMethod().map(dailyDto, DailyEntity.class);
        //model mapper yerine biz yazarsak
        //RegisterEntity registerEntity=new RegisterEntity();
        //registerEntity.setId(registerDto.getId());
        //registerEntity.setName(registerDto.getName());
        //registerEntity.setSurname(registerDto.getSurname());
        //registerEntity.setEmail(registerDto.getEmail());
        //registerEntity.setPassword(registerDto.getPassword());
        try {
            if (registerEntity != null) {
                repository.save(registerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //File
        return "success";
    }


    // LIST
    // http://localhost:2222/daily/list
    @Override
    @GetMapping("/daily/list")
    public String dailyList(Model model) {
        List<DailyEntity> list = repository.findAll();
        model.addAttribute("key_daily", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        return "daily_list";
    }

    // FIND
    // http://localhost:2222/daily/find
    // http://localhost:2222/daily/find/1
    @Override
    @GetMapping( "/daily/find/{id}")
    public String dailyFindById(@PathVariable(name = "id") Long id, Model model) {
        //1.YOL
        /*Optional<RegisterEntity> findData = repository.findById(id);
        if (findData.isPresent()) {
            return "Data: " + findData.get();
        } else {
            return id + " numaralı Data: Bulunamadı  ";
        }*/

        //2.YOL
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        model.addAttribute("daily_find", registerEntity);
        return "daily_detail_page";
    }

    // DELETE
    // http://localhost:2222/daily/delete
    // http://localhost:2222/daily/delete/1
    @Override
    @GetMapping({"/daily/delete", "/daily/delete/{id}"})
    public String dailyDeleteById(@PathVariable(name = "id", required = false) Long id, Model model) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (registerEntity != null) {
            repository.deleteById(id);
            model.addAttribute("key_delete", registerEntity + " silindi");
        } else
            model.addAttribute("key_delete", id + " numaralı veri yoktur");
        return "redirect:/daily/list";
    }

    //UPDATE
    // http://localhost:2222/update/daily
    @Override
    @GetMapping("/daily/update/{id}")
    public String updateGetDaily(@PathVariable(name = "id") Long id, Model model) {
        DailyEntity registerEntityFind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (registerEntityFind != null) {
            model.addAttribute("key_update", registerEntityFind);
        } else
            model.addAttribute("key_update", id + " numaralı veri yoktur");
        return "daily_update";
    }

    //UPDATE
    // http://localhost:2222/update/daily
    @Override
    @PostMapping("/daily/update/{id}")
    public String updatePostDaily(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("key_update") DailyDto dailyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "daily_update";
        }
        DailyEntity registerEntity = modelMapperBean.modelMapperMethod().map(dailyDto, DailyEntity.class);
        try {
            if (registerEntity != null) {
                repository.save(registerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/daily/list";
    }
}