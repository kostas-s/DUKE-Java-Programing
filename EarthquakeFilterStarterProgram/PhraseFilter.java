public class PhraseFilter implements Filter{
    String type;
    String phrase;
    public PhraseFilter(String typePassed, String phrasePassed){
     type = typePassed;
     phrase = phrasePassed;
    }
    public boolean satisfies(QuakeEntry qe){
     String title = qe.getInfo();
     switch (type){
            case "start":{
            return (title.indexOf(phrase)==0);
            }
            case "end":{
            return (title.endsWith(phrase));
            }
            case "any" :{
            return (title.contains(phrase));
            }
     }
     return false;
    }
    public String getName(){
    return "Phrase";
    }
}
