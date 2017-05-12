package vision;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALBarcodeReader;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;

public class BarcodeDetectedEvent {

    private static ALMemory alMemory;
    private static ALBarcodeReader alBarcodeReader;
    private static ALMotion alMotion;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alBarcodeReader = new ALBarcodeReader(session);
        alMotion = new ALMotion(session);

        alBarcodeReader.subscribe("Test",100,0.0f);
        alMemory.subscribeToEvent(
                "BarcodeReader/BarcodeDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Barcode Detected");
                        //Hier Anweisung
                    }
                });
    }
}
