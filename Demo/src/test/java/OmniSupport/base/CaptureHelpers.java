package OmniSupport.base;

import io.qameta.allure.Attachment;

public class CaptureHelpers {
    // Giả lập bắt đầu ghi video/log
    public static void startRecord(String testName) {
        System.out.println("[Start Record] Test: " + testName);
        saveTextLog("🎬 Start recording test: " + testName);
    }

    // Giả lập kết thúc ghi video/log
    public static void stopRecord() {
        System.out.println("[Stop Record]");
        saveTextLog("🛑 Stop recording test.");
    }

    // Ghi log dưới dạng text trong Allure Report
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
}
