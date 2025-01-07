package stringsFirstAssignments;

import edu.duke.URLResource;

public class Part4 {
    public static void main(String[] args) {
        String url = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource urlResource = new URLResource(url);
       // for(String str: urlResource.lines()){
      //      System.out.println(str);
       // }
        for(String word : urlResource.words()){
            String lowerCase = word.toLowerCase();
            int index = lowerCase.indexOf("youtube.com");
            if(index!=-1){
                System.out.println(word);
                int beginIndex = word.lastIndexOf("\"",index);
                int endIndex = word.indexOf("\"",index+1);

                System.out.println(word.substring(beginIndex+1,endIndex));
            }

        }

    }
}
