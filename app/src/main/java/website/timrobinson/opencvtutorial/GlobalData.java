package website.timrobinson.opencvtutorial;

public class GlobalData {

    private static Boolean QRCode;
    private static Boolean CVerify;

    public static Boolean getCVerify() {
        return CVerify;
    }

    public static void setCVerify(Boolean CVerify) {
        GlobalData.CVerify = CVerify;
    }

    public static Boolean getQRCode() {
        return QRCode;
    }

    public static void setQRCode(Boolean QRCode) {
        GlobalData.QRCode = QRCode;
    }

    public void saveSharedPrefs(){



    }
}
