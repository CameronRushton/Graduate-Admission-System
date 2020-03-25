package sysc4806.graduateAdmissions.controller;

/**
 * an exception to throw when login does not occur correctly
 *
 * @author luke
 */
public class InvalidLoginException extends Exception {
    public InvalidLoginException(String s) {
        super(s);
    }
}
