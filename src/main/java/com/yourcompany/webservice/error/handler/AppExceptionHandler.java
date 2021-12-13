package com.yourcompany.webservice.error.handler;

import com.yourcompany.webservice.error.ErrorCode;
import com.yourcompany.webservice.error.exception.AppBaseRuntimeException;
import com.yourcompany.webservice.model.dto.ErrorDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * General Exception handler<br>
 * This handler will handle all exception from RestController
 * 
 * @author candra
 */
@ControllerAdvice(annotations = RestController.class)
public class AppExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

	/**
	 * Handling known exception
	 * 
	 * @param ex {@link Exception}
	 * @return ResponseEntity<ErrorDto> {@link ResponseEntity}<ErrorDto>
	 */
	@ExceptionHandler(AppBaseRuntimeException.class)
	public ResponseEntity<ErrorDto> handleCustomAppsException(final AppBaseRuntimeException ex) {
		log.error("Handling AppsException with message: {} and httpstatus: {} and errorCode:{}",
				ex.getError().getErrorMessage(), ex.getError().getHttpStatus(), ex.getError().getErrorCode());
		return handleKnownError(ex);
	}

	/**
	 * Handling known exception
	 * 
	 * @param ex {@link Exception}
	 * @return ResponseEntity<ErrorDto> {@link ResponseEntity}<ErrorDto>
	 */
	@ExceptionHandler(ProviderNotFoundException.class)
	public ResponseEntity<ErrorDto> handleCustomProviderNotFoundException(final ProviderNotFoundException ex) {
		log.error("Handling unexpected exception with message: {} and httpstatus: {}", ex.getMessage());
		ErrorDto errorsDto = new ErrorDto();
		errorsDto.setStatus(String.valueOf(ErrorCode.UNKNOWN_ERROR.getHttpStatus()));
		errorsDto.setCode(ErrorCode.UNKNOWN_ERROR.getErrorCode());
		errorsDto.setMessage(ErrorCode.UNKNOWN_ERROR.getErrorMessage());
		return ResponseEntity.status(ErrorCode.UNKNOWN_ERROR.getHttpStatus()).body(errorsDto);
	}

	/**
	 * Handling unknown base exception
	 * 
	 * @param ex {@link Exception}
	 * @return ResponseEntity<ErrorDto> {@link ResponseEntity}<ErrorDto>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handleException(final Exception ex) {
		log.error("Handling unexpected exception with message: {} and httpstatus: {}", ex.getMessage(), ex);
		ErrorDto errorsDto = new ErrorDto();
		errorsDto.setStatus(String.valueOf(ErrorCode.UNKNOWN_ERROR.getHttpStatus()));
		errorsDto.setCode(ErrorCode.UNKNOWN_ERROR.getErrorCode());
		errorsDto.setMessage(ErrorCode.UNKNOWN_ERROR.getErrorMessage());
		return ResponseEntity.status(ErrorCode.UNKNOWN_ERROR.getHttpStatus()).body(errorsDto);
	}

	/**
	 * Handling known exception
	 * 
	 * @param ex {@link Exception}
	 * @return ResponseEntity<ErrorDto> {@link ResponseEntity}<ErrorDto>
	 */
	private ResponseEntity<ErrorDto> handleKnownError(final AppBaseRuntimeException ex) {
		ErrorDto errorsDto = new ErrorDto();
		errorsDto.setStatus(String.valueOf(ex.getError().getHttpStatus().value()));
		errorsDto.setCode(ex.getError().getErrorCode());
		errorsDto.setMessage(ex.getError().getErrorMessage());

		if (!ex.getErrorDetails().isEmpty()) {
			errorsDto.setErrorsDetails(ex.getErrorDetails());
		}

		return ResponseEntity.status(ex.getError().getHttpStatus()).body(errorsDto);
	}
}
