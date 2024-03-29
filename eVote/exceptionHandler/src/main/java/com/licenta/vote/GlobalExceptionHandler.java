package com.licenta.vote;
import com.licenta.vote.CustomExceptions.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice(basePackages = {"com.licenta.vote.cmd","com.licenta.vote.query"})
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        Logger.getLogger("exceptie").info("GlobalExceptionHandler initialized");
    }
    @ExceptionHandler(AggregateNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAggregateNotFoundException(AggregateNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AttendanceCalculationException.class)
    public ResponseEntity<ErrorResponse> handleAttendanceCalculationException(AttendanceCalculationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ConcurencyException.class)
    public ResponseEntity<ErrorResponse> handleConcurencyException(ConcurencyException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DateFormatIncorrectException.class)
    public ResponseEntity<ErrorResponse> handleDateFormatIncorrectException(DateFormatIncorrectException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(FutureBirthDateException.class)
    public ResponseEntity<ErrorResponse> handleFutureBirthDateException(FutureBirthDateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidCandidateOptionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCandidateOptionException(InvalidCandidateOptionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(InvalidVotingDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidVotingDateException(InvalidVotingDateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(InviteDeletedException.class)
    public ResponseEntity<ErrorResponse> handleInviteAlreadyDeletedException(InviteDeletedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InviteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInviteNotFoundException(InviteNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MultipleCommandHandlersException.class)
    public ResponseEntity<ErrorResponse> handleMultipleCommandHandlersException(MultipleCommandHandlersException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MultipleQueryHandlersFoundException.class)
    public ResponseEntity<ErrorResponse> handleMultipleQueryHandlersFoundException(MultipleQueryHandlersFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoCommandHandlerException.class)
    public ResponseEntity<ErrorResponse> handleNoCommandHandlerException(NoCommandHandlerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoQueryHandlerRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleNoQueryHandlerRegisteredException(NoQueryHandlerRegisteredException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResultsDateException.class)
    public ResponseEntity<Object> handleResultsDateException(ResultsDateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(TooManyQueryParametersException.class)
    public ResponseEntity<ErrorResponse> handleTooManyQueryParametersException(TooManyQueryParametersException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleUniqueConstraintViolationException(UniqueConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserDeletedException.class)
    public ResponseEntity<ErrorResponse> handleUserDeletedException(UserDeletedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(VoteRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleVoteRegistrationException(VoteRegistrationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(VotingEventDeletedException.class)
    public ResponseEntity<ErrorResponse> handleVotingEventDeletedException(VotingEventDeletedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(VotingEventExpiredException.class)
    public ResponseEntity<ErrorResponse> handleVotingEventExpiredException(VotingEventExpiredException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(VotingEventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVotingEventNotFoundException(VotingEventNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InviteUsedException.class)
    public ResponseEntity<ErrorResponse> handleInviteUsedException(InviteUsedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        // Log the error and return a generic API response
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
