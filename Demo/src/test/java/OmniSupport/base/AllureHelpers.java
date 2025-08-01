package OmniSupport.base;

import io.qameta.allure.Attachment;

public class AllureHelpers {
    @Attachment(value = "Simple Log", type = "text/plain")
    public static String logMessage(String message) {
        return message;
    }
}
