


package mi.functions;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;



public class QRgen {


        private static final String QR_CODE_IMAGE_PATH = System.getProperty("user.dir")+"/temp/QRCode.png";


    

    
        private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        
            
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
                    Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
                    
                    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");		
                    hintMap.put(EncodeHintType.MARGIN, 1); 
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            
                    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
        
                    Path path = FileSystems.getDefault().getPath(filePath);
        
                    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            
        }
    
    
    
    
    

        public static void GEN_QR_FROM_TEXT(String text) { 

        try {
            generateQRCodeImage(text, 190, 200, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }

        
        
    public static void GEN_QR_VCARD() { 
    
        String str = "BEGIN:VCARD\n" +
                     "VERSION:3.0\n" +
                     "N:TheFour;Brasil;;;\n" +
                     "FN:TheFour Brasil\n" +
                     "TITLE:Endereço:\n" +                     
                     "ADR:Pavilhão Vera Cruz - Av. Lucas Nogueira Garcéz 856, Centro, São Bernardo do Campo, SP\n" +
                     "END:VCARD";
        
        try {
            generateQRCodeImage(str, 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    
    
    }
    
    
    
    
}

