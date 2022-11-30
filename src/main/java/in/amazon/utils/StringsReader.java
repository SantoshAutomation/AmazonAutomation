package in.amazon.utils;

import in.amazon.base.ITBaseClass;

public class StringsReader extends ITBaseClass {
    public StringsReader() {}

    public static String getMessage(String message) {
        return getProperty(message);
    }
}
