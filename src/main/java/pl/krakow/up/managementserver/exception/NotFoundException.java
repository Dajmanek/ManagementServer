package pl.krakow.up.managementserver.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public static class ClientNotFoundException extends NotFoundException {

        public ClientNotFoundException() {
        }

        public ClientNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends NotFoundException {

        public UserNotFoundException() {
        }

        public UserNotFoundException(String message) {
            super(message);
        }
    }

}
