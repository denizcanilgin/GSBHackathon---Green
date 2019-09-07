package website.timrobinson.opencvtutorial;

import android.content.Context;
import android.content.SharedPreferences;

public class GlobalData {

    private static Boolean QRCode;
    private static Boolean CVerify;
    private static int countOfPlantedTrees;

    public static Boolean getCVerify() {
        return CVerify;
    }

    public static int getCountOfPlantedTrees(Context context) {


        SharedPreferences pref = context.getSharedPreferences("SPrefs",0); // 0 - for private mode
        int countOfPlantedTreesInt = pref.getInt("countOfPlantedTrees",1);
        return countOfPlantedTreesInt;

    }

    public void setCountOfPlantedTrees(int countOfPlantedTreesInt, Context context) {
        GlobalData.countOfPlantedTrees = countOfPlantedTreesInt;

        if(countOfPlantedTreesInt == 10){

            countOfPlantedTreesInt = 0;

        }

        SharedPreferences pref = context.getSharedPreferences("SPrefs", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("countOfPlantedTrees",countOfPlantedTreesInt);
        editor.commit();

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

}
