public class TeWeinigGeldException extends Exception {

/*
    TeWeinigGeldException() {

    }
*/

    TeWeinigGeldException(Exception e) {
        super(e);
    }

    TeWeinigGeldException(String message) {
        super(message);
    }

}
