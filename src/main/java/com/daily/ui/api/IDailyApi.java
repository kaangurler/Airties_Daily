package com.daily.ui.api;
import com.daily.business.dto.DailyDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface IDailyApi {
    //CREATE
    ResponseEntity<?> createDaily(DailyDto registerDto);

    //LIST
    ResponseEntity<List<DailyDto>>  listDaily();

    //FIND
    ResponseEntity<DailyDto> findDaily(Long id);


    //UPDATE
    ResponseEntity<DailyDto>  updateDaily(Long id, DailyDto registerDto);

    //DELETE
    ResponseEntity <Map<String, Boolean>> deleteDaily( Long id);
}
