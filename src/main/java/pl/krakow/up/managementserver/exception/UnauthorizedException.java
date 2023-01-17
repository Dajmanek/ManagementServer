package pl.krakow.up.managementserver.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public static class UserUnauthorizedException extends UnauthorizedException {

        public UserUnauthorizedException() {
        }

        public UserUnauthorizedException(String message) {
            super(message);
        }
    }

    public static class ClientUnauthorizedException extends UnauthorizedException {

        public ClientUnauthorizedException() {
        }

        public ClientUnauthorizedException(String message) {
            super(message);
        }
    }

}
