package src.model3.caesarCipher;

public class WordPlay {
    public static boolean isVowel(char ch){
        ch=Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }


    public static String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder();
        for(char c: phrase.toCharArray()){
            char candidate = isVowel(c)?ch:c;
            sb.append(candidate);
        }
        return sb.toString();
    }
    public static boolean isSameChar(char a, char b){
        return Character.toLowerCase(a)==Character.toLowerCase(b);
    }


    public static String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < phrase.length(); i++){
            if(isSameChar(phrase.charAt(i),ch)){
                char sign = (i&1)==0?'*':'+';
                sb.append(sign);
            }else{
                sb.append(phrase.charAt(i));
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(replaceVowels("a",'*'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
}
