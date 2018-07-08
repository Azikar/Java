/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;


import java.security.Key;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;
import org.bouncycastle.jce.spec.ElGamalPrivateKeySpec;
import org.bouncycastle.jce.spec.ElGamalPublicKeySpec;
/**
 *
 * @author nerijus
 */
public class JavaApplication2
{
    private static final String	digits = "0123456789ABCDEF";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        // Savo uzduotis realizuokite kaip klases Main metodus
        // ir juos iskvieskite is sio metodo, kaip pavyzdziui:
        //doListBCCapabilities();
        //doEncryptSimpleAES();
        //doDecryptSerpent();
//Maisa();
      //  doSHA1HMACCheck();
        doRSADecrypt();
       //doElGamalDecrypt();
    }

public static void doElGamalDecrypt() throws Exception
    {
        BigInteger g256 = new BigInteger(
            "65EFF5CCAAC2B7E132335DECB7A7BC21B9AFC7FF422595355BA83141C7910A9A", 16);
        BigInteger p256 = new BigInteger(
            "00EBFCB7E2CB29A9C9EF551690E0A276B643A78B9B54F1C0DF26A7F778F219A1DF", 16);
        ElGamalParameterSpec  egSpec = new ElGamalParameterSpec(p256, g256); 

        BigInteger       ct = new BigInteger ("4C22AD8CBDA8173296B746A2EF4ED7141B206004A3627F68B9CA50397F45D8421E5E3E3172DEA839AFA4B90ED40385DC0F3E847322C0B94100207BCA64AAA6BF", 16);
        byte[]           inputBytes = ct.toByteArray();
        Cipher	         cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
        SecureRandom     random = new SecureRandom();

        KeyFactory      keyFactory = KeyFactory.getInstance("ElGamal", "BC");
        ElGamalPublicKeySpec pubKeySpec = new ElGamalPublicKeySpec(
                new BigInteger("00E54C26F99C62135DA0DC788C20C54DA2836C93D80E26DF0E350B353D286D9A7C", 16),
                egSpec);
        ElGamalPrivateKeySpec privKeySpec = new ElGamalPrivateKeySpec(
                new BigInteger("0BDBE219B628E8C37C2723ECBD7B9E27402DA552386A05C54C44EEAE438E370A", 16),
                egSpec);
        ElGamalPublicKey pubEG = (ElGamalPublicKey)keyFactory.generatePublic(pubKeySpec);
        ElGamalPrivateKey privEG = (ElGamalPrivateKey)keyFactory.generatePrivate(privKeySpec);

        System.out.println("Duotoji sifrograma : " + toHex(inputBytes));
        cipher.init(Cipher.DECRYPT_MODE, privEG);
        byte[] plainText = cipher.doFinal(inputBytes, 0, inputBytes.length);

        System.out.println("Iššifruota tekstograma : " + toHex(plainText));

        //patikrinimas
       // System.out.println(random.toString());
        cipher.init(Cipher.ENCRYPT_MODE, pubEG);
        byte[] cipherText = cipher.doFinal(plainText);

        System.out.println("Vel uzsifruotas : " + toHex(cipherText));

        System.out.println("EG viesas Y : " + toHex(pubEG.getY().toByteArray()));
        System.out.println("EG privatus X : " + toHex(privEG.getX().toByteArray()));
        System.out.println("EG generatorius G : " + toHex(privEG.getParameters().getG().toByteArray()));
        System.out.println("EG modulis P : " + toHex(pubEG.getParameters().getP().toByteArray()));
        //Ar turi sutapti? Kodel?
    }
 public static void doRSADecrypt() throws Exception
    {
        int              ilgis = 0;
        BigInteger       ct = new BigInteger("0D5A33488BDA4543F0B79DE959AE0E78DCFA3BFD5EA2D8FEFB204BCAA7FCB0F8", 16);
        byte[]           inputBytes = ct.toByteArray();
        SecureRandom     random = new SecureRandom();
        Cipher	         cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
        KeyFactory       keyFactory = KeyFactory.getInstance("RSA", "BC");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(
                new BigInteger("C57C7AAC6CC49C1EB0AB8CDDF0A3C22183BA532D8769833549830B958BC01301", 16),
                new BigInteger("03", 16));
        RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(
                new BigInteger("C57C7AAC6CC49C1EB0AB8CDDF0A3C22183BA532D8769833549830B958BC01301", 16),
                new BigInteger("83A851C8488312BF20725DE94B17D6BFD668AC48A3D9770F1B51C8B7B57935E3", 16));

        RSAPublicKey pubKey = (RSAPublicKey)keyFactory.generatePublic(pubKeySpec);
        RSAPrivateKey privKey = (RSAPrivateKey)keyFactory.generatePrivate(privKeySpec);

        System.out.println("Sifrograma : " + toHex(inputBytes, inputBytes.length));
        cipher.init(Cipher.DECRYPT_MODE, privKey,random);
        byte[] plainText = new byte[cipher.getOutputSize(inputBytes.length)];
        
        ilgis += cipher.doFinal(inputBytes, 0, inputBytes.length, plainText, 0);
        System.out.println("Issifruota tekstograma : " + toHex(plainText, ilgis) + " ilgis: " + ilgis);
        System.out.println("Viesasis raktas :" + toHex(pubKey.getPublicExponent().toByteArray()));
        System.out.println("RSA modulis : " + toHex(pubKey.getModulus().toByteArray()));
        System.out.println("Privatusis raktas : " + toHex(privKey.getPrivateExponent().toByteArray()));


        //patikrinimas
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println("Vel uzsifruota : " + toHex(cipherText));
        //Ar sutampa abi sifrogramos?
    }
/*
sifrograma: DFD1AD8FED3D091F 79D85F1A0E8F1F61 D98C1A5003FDEE0B 615FC394D8FE1C54  bytes: 32
Naudotas raktas : 6665566666655666 3331133333311333
Naudotas IV : 0706050403020100 08090A0B0C0D0E0F
    
    00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F 
*/
    
    
  public static void doSHA1HMACCheck() throws Exception
    {
        boolean ok = false;
//        byte[]  inputBytes = new byte[] {
//                (byte) 0xDA, (byte) 0xD0, (byte) 0x00, 0x00, 0x00, 0x01, 0x02, 0x03,
//                0x04, 0x05, 0x06, 0x07, 0x08,0x09};
//        byte[]  macKeyBytes = new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,0x00};
//        byte[]  hmacBytes = new byte[] {
//                (byte) 0x21, 0x2C, 0x7F, (byte)0xCA, (byte)0xF9, (byte) 0x6B, (byte) 0xE2, (byte)0x89,
//                (byte)0xFC, (byte) 0xB7, 0x18, (byte) 0xC0, (byte) 0xF3, (byte) 0x43, 0x5E, (byte) 0x35};
//   byte[]  inputBytes = new byte[] {
//                (byte) 0xBA, (byte) 0xD0, (byte) 0x00, 0x00, 0x00, 0x01, 0x02, 0x03,
//                0x04, 0x05, 0x06, 0x07, 0x08,0x09 ,(byte)0x0A,(byte)0x0B,0x00};
//        byte[]  macKeyBytes = new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,0x37,0x38,0x39,0x3A,0x3B,0x3C};
//        byte[]  hmacBytes = new byte[] {
//                (byte) 0x37, (byte)0xCE, (byte)0xB1, (byte)0xFC, (byte)0x3A, (byte) 0x3F, (byte) 0x58, (byte)0x85,
//                (byte)0x9C, (byte) 0x53, (byte)0xB7, (byte) 0xE4, (byte) 0x09, (byte) 0x08, 0x59, (byte) 0x89,
//        0x32,(byte)0x99,0x3A,(byte)0xD1,(byte)0x90,(byte)0x86,(byte)0xBA,(byte)0xFD};
//        
byte[]  inputBytes = new byte[] {
                (byte) 0xBA, (byte) 0xD0, (byte) 0x00, 0x00, 0x00, 0x01, 0x02, 0x03,
                0x04, 0x05, 0x06};
 byte[]  macKeyBytes = new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,0x37,0x38,0x39,0x3A,0x3B,0x3C};
 
  byte[]  hmacBytes = new byte[] {
                (byte) 0xD4, (byte)0x4E, (byte)0xA4, (byte)0x38, (byte)0x2C, (byte) 0x9D, (byte) 0xA8, (byte)0x84,
                (byte)0xDF, (byte) 0xB1, (byte)0xB1, (byte) 0xEC, (byte) 0xF8, (byte) 0x13,(byte) 0x86, (byte) 0xF1,
        0x16,(byte)0x14,(byte)0xAA,(byte)0x80,(byte)0x3F,(byte)0x67,(byte)0x9F,(byte)0xC7};
        Mac hMac = Mac.getInstance("HMac-Tiger", "BC");
        Key hMacKey = new SecretKeySpec(macKeyBytes,"HMac-Tiger");

        System.out.println("Tekstograma : " + toHex(inputBytes));
        System.out.println("Slaptas raktas : " + toHex(macKeyBytes));
        System.out.println("Pateiktas hmac : " + toHex(hmacBytes));

        hMac.init(hMacKey);
        hMac.update(inputBytes, 0, inputBytes.length);

        byte[] inputMac = new byte[hMac.getMacLength()];
        inputMac = hMac.doFinal();

        System.out.println("Apskaiciuotas hmac : " + toHex(inputMac) + " ilgis " + hMac.getMacLength());

        ok = MessageDigest.isEqual(inputMac, hmacBytes);
        System.out.println("Pranesimas nesuklastotas : " + ok);
    }
   
    
     public static void Maisa() throws Exception
     {
          boolean ok = false;
        byte[]  inputBytes = new byte[] {
                (byte) 0xAA, (byte) 0xDE, (byte) 0x00, (byte) 0x00, 0x04, 0x05, 0x06, 0x07,
                0x08, 0x09, 0x0A,0x0B,0x0C};
        byte[]  hashBytes = new byte[] {
                0x55, (byte) 0x10, (byte) 0x51, (byte) 0x1B, (byte) 0x6D, (byte) 0xDE, (byte) 0xCC, 0x39,
                (byte) 0xF5, (byte) 0x07,(byte) 0x14, (byte) 0x2B, 0x67, (byte) 0x8A, (byte) 0x5E, 0x23,
       (byte) 0x65, (byte) 0x72,(byte)0xBB,0x55,0x01,(byte) 0x36,0x69,(byte) 0x9C,0x52,(byte)0xF9,(byte) 0xD1,0x4A,0x31,0x14,
       (byte)0xC0,(byte) 0xEF};

        System.out.println("Tekstograma : " + toHex(inputBytes));
        System.out.println("Tiger santrauka : " + toHex(hashBytes));

        MessageDigest   hash = MessageDigest.getInstance("RipeMD256", "BC");

        hash.update(inputBytes, 0, inputBytes.length);
        byte[] inputHash = new byte[hash.getDigestLength()];
        inputHash = hash.digest();

        System.out.println("Apskaičiuota santrauka : " + toHex(inputHash));

        ok = MessageDigest.isEqual(inputHash, hashBytes);
        System.out.println("Tekstogra nepakeista? : " + ok);
     }
    
    public static void doDecryptSerpent() throws Exception
    {
        // pirma uzduotis
//        byte[]  keyBytes = new byte[] {
//                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
//                0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
//        byte[]  input = Hex.decode ("1E37EF00212DA96A 7341836121AE4976\n" +
//                                        "8A19207B201300B4 7FCB9EE52AEA9FD6");
//       // byte[]	ivBytes = Hex.decode ("0706050403020100 08090A0B0C0D0E0F");
//
//        System.out.println("Duotoji tekstograma : " + toHex(input));
//        SecretKeySpec   key = new SecretKeySpec(keyBytes, 0, 16, "twofish");
//        // IV turi buti lygiai tiek baitu, koks yra bloko ilgis
//       // IvParameterSpec ivSpec = new IvParameterSpec(ivBytes, 0, 16);
//        Cipher          cipher = Cipher.getInstance("twofish/ECB/NoPadding", "BC");
//        
//       
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
//
//        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
//        ctLength += cipher.doFinal(cipherText, ctLength);
//
//        System.out.println("Uzsifruota Two fish : " + toHex(cipherText, ctLength) + " bytes: " + ctLength);
//        
//        
//
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] plainText = new byte[cipher.getOutputSize(cipherText.length)];
//
//        int ptLength = cipher.update(cipherText, 0, cipherText.length, plainText, 0);
//        ptLength += cipher.doFinal(plainText, ptLength);
//        System.out.println("Twofish issifruota: " + toHex(plainText, ptLength) + " bytes: " + ptLength);
//        byte[] raktas = key.getEncoded();
//        System.out.println("Naudotas raktas : " + toHex(raktas));
//        
//        
//        
//        //pakeitus sifrogramos bita
//         input = Hex.decode ("AABE000000000000 0000000000000000 0000000000000000 000000000000BABE");
//         
//         
//         
//          cipher.init(Cipher.DECRYPT_MODE, key);
//        plainText = new byte[cipher.getOutputSize(input.length)];
//
//         ptLength = cipher.update(input, 0, input.length, plainText, 0);
//        ptLength += cipher.doFinal(plainText, ptLength);
//        System.out.println("Twofish issifruota: " + toHex(plainText, ptLength) + " bytes: " + ptLength);
//       raktas = key.getEncoded();
//        System.out.println("Naudotas raktas : " + toHex(raktas));
      
        
        
        //su VI
        //66 65 56 66 66 65 56 66   33 31 13 33 33 31 13 33
          byte[]  keyBytes = new byte[] {
                0x66, 0x65, 0x56, 0x66, 0x66, 0x65, 0x56, 0x66,
                0x33, 0x31, 0x13, 0x33, 0x33, 0x31, 0x13, 0x33};
        byte[]  input = Hex.decode ("1A8F94EA4AC2D508 836D9184553C3AC3 78111FB1E8302960");
        byte[]	ivBytes = Hex.decode ("0706050403020100");
System.out.println(input.length);
        System.out.println("Duotoji šifrograma : " + toHex(input));
        SecretKeySpec   key = new SecretKeySpec(keyBytes, 0, 16, "TEA");
        // IV turi buti lygiai tiek baitu, koks yra bloko ilgis
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes, 0, 8);
        Cipher          cipher = Cipher.getInstance("TEA/CBC/PKCS7Padding", "BC");
        
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] plainText = new byte[cipher.getOutputSize(input.length)];
        
        int ptLength = cipher.update(input, 0, input.length, plainText, 0);
        ptLength += cipher.doFinal(plainText, ptLength);
        System.out.println("meh"+ptLength);
        System.out.println("TEA  iššifruota tekstograma : " + toHex(plainText, ptLength) + " bytes: " + ptLength);
        byte[] raktas = key.getEncoded();
        System.out.println("Naudotas raktas : " + toHex(raktas));
        System.out.println("Naudotas IV : " + toHex(ivSpec.getIV()));

        //Patikrinimas
        
      
      
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = new byte[cipher.getOutputSize(ptLength)];

        int ctLength = cipher.update(plainText, 0, ptLength, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        System.out.println("Vėl užšifruota tekstograma : " + toHex(cipherText, ctLength) + " bytes: " + ctLength);
        
        
        input = Hex.decode ("ABBA000000000000 1011121314000000 0000ACDC00");
        System.out.println(input.length);
        plainText = new byte[cipher.getOutputSize(input.length)];
        System.out.println(toHex(input,input.length));
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        cipherText = new byte[cipher.getOutputSize(input.length)];

        ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        System.out.println("Vėl užšifruota tekstograma : " + toHex(cipherText, ctLength) + " bytes: " + ctLength);
//        
//        
        
        
        
        
        
    }

    /**
      * List the available capabilities for ciphers, key agreement, macs, message
      * digests, signatures and other objects in the BC provider.
    */
    public static void doListBCCapabilities() throws Exception
    {
        Provider	provider = Security.getProvider("BC");
        Iterator        it = provider.keySet().iterator();

        while (it.hasNext())
        {
            String	entry = (String)it.next();
            // this indicates the entry refers to another entry
            if (entry.startsWith("Alg.Alias."))
            {
                entry = entry.substring("Alg.Alias.".length());
            }
            String  factoryClass = entry.substring(0, entry.indexOf('.'));
            String  name = entry.substring(factoryClass.length() + 1);

            System.out.println(factoryClass + ": " + name);
        }
    }

    /**
     * Du pagalbiniai metodai skirti "graziai" atvaizduoti baitu masyvus
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer	buf = new StringBuffer();
        for (int i = 0; i != length; i++)
        {
            int	v = data[i] & 0xff;

            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));

            if (((i+1) % 8 == 0) && (i>0)) buf.append(" ");

        }
        return buf.toString();
    }

    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }

}
    

