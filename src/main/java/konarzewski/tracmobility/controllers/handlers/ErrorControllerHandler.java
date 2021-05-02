package konarzewski.tracmobility.controllers.handlers;

import konarzewski.tracmobility.exceptions.VehicleNotFoundException;
import konarzewski.tracmobility.exceptions.VehicleRepositoryException;
import konarzewski.tracmobility.exceptions.VehicleServiceException;
import konarzewski.tracmobility.models.general.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Class that implements a handler of exceptions and errors in the API,
 * and sending the proper response to the client.
 *
 * @author Pawel Konarzewski
 */

@RestControllerAdvice
public class ErrorControllerHandler {

    /**
     * Method that handles a VehicleRepositoryException and returns an error with
     * status code = 400.
     *
     * @param exception
     * @return ResponseErrorDto
     */

    @ExceptionHandler(value = VehicleRepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDto handleVehicleRepositoryException(VehicleRepositoryException exception){
        return ResponseErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .errorMsg(exception.getLocalizedMessage())
                .build();
    }

    /**
     * Method that handles a VehicleServiceException and returns an error with
     * status code = 400.
     *
     * @param exception
     * @return ResponseErrorDto
     */

    @ExceptionHandler(value = VehicleServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDto handleVehicleServiceException(VehicleServiceException exception){
        return ResponseErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .errorMsg(exception.getLocalizedMessage())
                .build();
    }

    /**
     * Method that handles a VehicleNotFoundException and returns an error with
     * status code = 404.
     *
     * @param exception
     * @return ResponseErrorDto
     */

    @ExceptionHandler(value = VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDto handleVehicleNotFoundException(VehicleNotFoundException exception){
        return ResponseErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .errorMsg(exception.getLocalizedMessage())
                .build();
    }
}
