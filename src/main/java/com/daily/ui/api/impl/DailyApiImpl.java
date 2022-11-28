package com.daily.ui.api.impl;

import com.daily.business.dto.DailyDto;
import com.daily.business.services.IDailyServices;
import com.daily.ui.api.IDailyApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@Log4j2
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class DailyApiImpl implements IDailyApi {

    //injection services
    private final IDailyServices services;

    //http://localhost:2222/api/v1/daily/create
    //CREATE
    @Override
    @PostMapping("daily/create")
    public ResponseEntity<?>  createDaily(@Valid @RequestBody DailyDto dailyDto) {
        services.createDaily(dailyDto);
        return ResponseEntity.ok(dailyDto);
    }

    //http://localhost:2222/api/v1/daily/list
    //LIST
    @Override
    @GetMapping("daily/list")
    public ResponseEntity<List<DailyDto>>  listDaily() {
        return ResponseEntity.ok(services.listDaily());
    }


    //http://localhost:2222/api/v1/daily/find/1
    //FIND
    @Override
    @GetMapping("daily/find/{id}")
    public ResponseEntity<DailyDto> findDaily(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(services.findDaily(id));
    }

    //http://localhost:2222/api/v1/daily/update/1
    //UPDATE
    @Override
    @PutMapping("daily/update/{id}")
    public ResponseEntity<DailyDto>  updateDaily(@PathVariable(name = "id") Long id, @Valid @RequestBody DailyDto dailyDto) {
        services.updateDaily(id,dailyDto);
        return ResponseEntity.ok(dailyDto);
    }


    //http://localhost:2222/api/v1/daily/delete/1
    //DELETE
    @Override
    @DeleteMapping("daily/delete/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteDaily(@PathVariable(name = "id") Long id) {
        services.deleteDaily(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok( response);
    }

}
