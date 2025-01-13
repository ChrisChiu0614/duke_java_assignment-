package src.model3.caesarCipher;

public class TestCaesarCipherTwo {

    public static void main(String[] args) {
        CaesarCipherTwo cct = new CaesarCipherTwo(14,24);
        String input = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encr = cct.encrypt(input);
        System.out.println(encr);
        String decr = cct.decrypt(encr);
        System.out.println(decr);
    }
}
