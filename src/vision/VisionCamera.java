package vision;

import com.aldebaran.qi.helper.proxies.ALVideoDevice;
import com.aldebaran.qi.helper.proxies.ALVisionRecognition.*;
import com.aldebaran.*;
import javafx.scene.image.Image;
import sun.awt.image.BufferedImageDevice;
import utillities.Uts;
import java.awt.Image.*;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lisa on 02.06.2017.
 */
public class VisionCamera extends Thread {
    ALVideoDevice oVideoDevice;
    String sHandle;
    BufferedImage bufferedImage;

    public VisionCamera() throws Exception {
        oVideoDevice = new ALVideoDevice(Uts.getSESSION());

        //CameraIndex
        // 0 = Top
        // 1 = Bottom

        //Resolution:
        //AL::kQQQQVGA	8	Image of 40*30px
        //AL::kQQQVGA	7	Image of 80*60px
        //AL::kQQVGA	0	Image of 160*120px
        //AL::kQVGA	    1	Image of 320*240px
        //AL::kVGA	    2	Image of 640*480px
        //AL::k4VGA	    3	Image of 1280*960px

        //ColorSpace:
        /*
        AL::kYuvColorSpace	    0	1	1	Buffer only contains the Y (luma component) equivalent to one unsigned char
        AL::kyUvColorSpace	    1	1	1	Buffer only contains the U (Chrominance component) equivalent to one unsigned char
        AL::kyuVColorSpace	    2	1	1	Buffer only contains the V (Chrominance component) equivalent to one unsigned char
        AL::kRgbColorSpace	    3	1	1	Buffer only contains the R (Red component) equivalent to one unsigned char
        AL::krGbColorSpace	    4	1	1	Buffer only contains the G (Green component) equivalent to one unsigned char
        AL::krgBColorSpace	    5	1	1	Buffer only contains the B (Blue component) equivalent to one unsigned char
        AL::kHsyColorSpace	    6	1	1	Buffer only contains the H (Hue component) equivalent to one unsigned char
        AL::khSyColorSpace	    7	1	1	Buffer only contains the S (Saturation component) equivalent to one unsigned char
        AL::khsYColorSpace	    8	1	1	Buffer only contains the Y (Brightness component) equivalent to one unsigned char
        AL::kYUV422ColorSpace	9	2	2	Native format, 0xY’Y’VVYYUU equivalent to four unsigned char for two pixels. With Y luma for pixel n, Y’ luma for pixel n+1, and U and V are the average chrominance value of both pixels.
        AL::kYUVColorSpace	    10	3	3	Buffer contains triplet on the format 0xVVUUYY, equivalent to three unsigned char
        AL::kRGBColorSpace	    11	3	3	Buffer contains triplet on the format 0xBBGGRR, equivalent to three unsigned char
        AL::kHSYColorSpace	    12	3	3	Buffer contains triplet on the format 0xYYSSHH, equivalent to three unsigned char
        AL::kBGRColorSpace	    13	3	3	Buffer contains triplet on the format 0xRRGGBB, equivalent to three unsigned char
        AL::kYYCbCrColorSpace	14	2	2	TIFF format, four unsigned characters for two pixels.
        AL::kH2RGBColorSpace	15	3	3	H from “HSY to RGB” in fake colors.
        AL::kHSMixedColorSpace	16	3	3	HS and (H+S)/2.
         */

        //FPS:
        /*
        AL::kQQQQVGA	from 1 to 30 fps
        AL::kQQQVGA	    from 1 to 30 fps
        AL::kQQVGA	    from 1 to 30 fps
        AL::kQVGA	    from 1 to 30 fps
        AL::kVGA	    from 1 to 30 fps
        AL::k4VGA	               1 fps
        AL::k16VGA	               1 fps
         */
        sHandle = oVideoDevice.subscribeCamera("Vision", 0, 1, 11, 10);
    }

    public void Open(int iCamPos) throws Exception {
        oVideoDevice.openCamera(iCamPos);
    }

    public void Close(int iCamPos) throws Exception {
        oVideoDevice.closeCamera(iCamPos);
    }

    public BufferedImage getImage() throws Exception {
        List<Object> oLst = (List<Object>)oVideoDevice.getImageRemote(sHandle);

        ByteBuffer buff = (ByteBuffer)oLst.get(6);

        int width = (int) oLst.get(0);
        int height = (int) oLst.get(1);

        int[] flattenedData = new int[width*height*3];
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width*height*3; i++)
        {
            flattenedData[i] = buff.get(i);
        }

        img.getRaster().setPixels(0, 0, width, height, flattenedData);


        oVideoDevice.releaseImage(sHandle);
        return img;
    }
    @Override
    public void run(){
        while(true) try {
            Thread.sleep(10);
            bufferedImage = getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
}
