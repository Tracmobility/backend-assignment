package konarzewski.tracmobility.models.general.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Class that implements a generic response error object to the API end-points.
 *
 * @author Pawel Konarzewski
 * */

@Data
@AllArgsConstructor
@Builder
public class ResponseErrorDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String errorMsg;
}
