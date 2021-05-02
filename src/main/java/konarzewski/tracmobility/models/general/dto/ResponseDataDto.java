package konarzewski.tracmobility.models.general.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Class that implements a generic response to the API end-points.
 *
 * @author Pawel Konarzewski
 * */
@Data
@AllArgsConstructor
@Builder
public class ResponseDataDto<T> {
    private T data;
}
