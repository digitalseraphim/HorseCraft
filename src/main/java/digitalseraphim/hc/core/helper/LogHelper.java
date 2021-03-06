package digitalseraphim.hc.core.helper;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class LogHelper {
    private static Logger hcLogger = FMLLog.getLogger();
    private static Marker m = MarkerManager.getMarker("HorseCraft");

    public static void init() {
    }

    public static void log(Level logLevel, String message) {
        hcLogger.log(logLevel, m, message);
    }

    public static void info(String message){
    	hcLogger.info(m, Thread.currentThread().getName() + ": " + message);
    }
    
}