
public class part1 {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1) {
            break;
        }
        
        if (index >= input.length()-3) {
            break;
        }
        System.out.println("Index is: " + index);
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+3);
        System.out.println("Index after update is: " + index);
    }
}
   public void test() {
    //findAbc("abcd"); 1111111111222222222233333333334444
    //       01234567890123456789012345678901234567890123  length is 7
    findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
}

}
