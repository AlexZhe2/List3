import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortWP {

//    1. Сосчитать частоту встречаемости слова или словосочетания в тексте (слово передаете аргументом).
//            2. Собрать все слова в группы по количеству букв: например, в одну группу попадут слова: [the, war, jar, get, met...], в другую [on, up, no, of...].
//            3. Вывести топ 10 самых частых слов и фраз.
// 4. Тоже, что и 2, но без артиклей, частиц и тп (без the, a, on, to...).
//            5. Вывести частоту встречаемости букв анг алфавита по этой книге. Вывести в процентах для каждой буквы


    int cnt = 0;


    List<String> strings = new LinkedList<>();
    LinkedList<String> listStrings = new LinkedList<>();

    LinkedList<String> listOfWords = new LinkedList<>();
    LinkedList<String> listOfWordsUnic = new LinkedList<>();
    LinkedList<String> listOfLettersUnic = new LinkedList<>();


    LinkedList<String> listGroupOne = new LinkedList<>();
    LinkedList<String> listGroupTwo = new LinkedList<>();
    LinkedList<String> listGroupTree = new LinkedList<>();
    LinkedList<String> listGroupFour = new LinkedList<>();

    LinkedList<String> tempListStrings = new LinkedList<>();


    TreeSet<String> setOfWords = new TreeSet<>();
    TreeSet<String> setOfletters = new TreeSet<>();

    TreeMap<String, Integer> mapOfWords = new TreeMap<>();
    TreeMap<String, Integer> mapOfLetteres = new TreeMap<>();


    public static void main(String[] args) throws IOException {

        SortWP start = new SortWP();

        start.wordCounter("the");
        start.wordSeparation(3, 2);
        start.wordSeparationWithOut(3, 2, "on", "the", "to", "of");
        start.printTopTeen();
        start.lettersSeparation();
    }

    public void wordCounter(String word) throws IOException {

        ClassLoader loader = SortWP.class.getClassLoader();
        File file = new File(loader.getResource("wp.txt").getFile());
        strings = Files.readAllLines(file.toPath());
        listStrings.addAll(strings);

        String readyWord = "=" + word + "=";
        String stringTemp;
        String stringTempLowCase;
        String anyString;

        for (int i = 0; i < listStrings.size(); i++) {

            stringTemp = listStrings.get(i);
            stringTemp = stringTemp.replaceAll("[^A-Za-z]", "=");
            stringTemp = stringTemp.replaceAll("^", "=");
            stringTemp = stringTemp.replaceAll("$", "=");
            stringTemp = stringTemp.replaceAll("={2,}", "=");
            stringTempLowCase = stringTemp.toLowerCase();

            tempListStrings.add(i, stringTempLowCase);

        }


        for (String i : tempListStrings) {
            anyString = i;
            Pattern pattern1 = Pattern.compile(readyWord, Pattern.CASE_INSENSITIVE);
            Matcher matcher1 = pattern1.matcher(anyString);


            while (matcher1.find()) {
                cnt++;
            }
        }

        System.out.println("Слово - \"" + word + "\" встречается в тексте " + cnt + " раз.");


    }

    public void wordSeparation(Integer lenпthWordOne, Integer lenпthWordTwo) throws IOException {
        System.out.println();


        int lengthWord;


        for (String i : tempListStrings) {

            String[] arr = i.split("=");

            listOfWords.addAll(Arrays.asList(arr));

        }

        Iterator<String> listOfWordsIterator = listOfWords.listIterator();

        while (listOfWordsIterator.hasNext()) {
            if (listOfWordsIterator.next().equals("")) {
                listOfWordsIterator.remove();
            }
        }

        for (String i : listOfWords) {

            lengthWord = i.length();

            if (lengthWord == lenпthWordOne) {

                listGroupOne.add(i);
            }
            if (lengthWord == lenпthWordTwo) {

                listGroupTwo.add(i);
            }

        }

        System.out.println("listGroupOne" + listGroupOne);
        System.out.println("listGroupTwo" + listGroupTwo);

    }

    public void wordSeparationWithOut(Integer lenпthWordThree, Integer lenпthWordFour, String withOutOne, String withOutTwo, String withOutThree, String withOutFour) {
        System.out.println();


        int lengthWord;

        for (String i : listOfWords) {

            lengthWord = i.length();

            if (lengthWord == lenпthWordThree && !i.equals(withOutOne) && !i.equals(withOutTwo) && !i.equals(withOutThree) && !i.equals(withOutFour)) {

                listGroupTree.add(i);
            }
            if (lengthWord == lenпthWordFour && !i.equals(withOutOne) && !i.equals(withOutTwo) && !i.equals(withOutThree) && !i.equals(withOutFour)) {

                listGroupFour.add(i);
            }

        }

        System.out.println("listGroupTree" + listGroupTree);
        System.out.println("listGroupFour" + listGroupFour);

    }


    public void printTopTeen() {
        System.out.println();

        setOfWords.addAll(listOfWords);

        listOfWordsUnic.addAll(setOfWords);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.addAll(listOfWords);


        int cntUnic = 0;
        String a1;
        String a2;

        for (String i : listOfWordsUnic) {
            cntUnic = 0;
            for (int j = 0; j < arrayList.size(); j++) {

                a1 = i;
                a2 = listOfWords.get(j);
                if (a1.equals(a2)) {

                    cntUnic++;
                    mapOfWords.put(i, cntUnic);

                }

            }
        }


        LinkedList<String> list7 = new LinkedList<>();
        list7.addAll(mapOfWords.keySet());
        LinkedList<Integer> list8 = new LinkedList<>();
        list8.addAll(mapOfWords.values());


        LinkedList<Coo> list9 = new LinkedList<>();

        for (int i = 0; i < list7.size(); i++) {
            list9.add(i, new Coo(list8.get(i), list7.get(i)));

        }

        Comparator<Coo> CooComparator = new Coo.CooBComparator();

        TreeSet<Coo> cooCoo = new TreeSet<>(CooComparator);

        for (int i = 0; i < list9.size(); i++) {

            cooCoo.add(list9.get(i));

        }

        LinkedList<Coo> topTen = new LinkedList<>();
        LinkedList<Coo> topTen2 = new LinkedList<>();

        topTen.addAll(cooCoo);

        for (int i = 0; i < 10; i++) {
            topTen2.add(i, topTen.get(i));
        }

        for (Coo i : topTen2) {

            System.out.println(i);
        }

    }

    public void lettersSeparation() {

        System.out.println();
        LinkedList<String> listOfLetters = new LinkedList<>();

        for (String i : tempListStrings) {

            String[] arrForLetters = i.split("");

            listOfLetters.addAll(Arrays.asList(arrForLetters));

        }


        Iterator<String> listOfLettersIterator = listOfLetters.listIterator();

        while (listOfLettersIterator.hasNext()) {
            if (listOfLettersIterator.next().equals("=")) {
                listOfLettersIterator.remove();
            }
        }

        int lengthOfLetters= listOfLetters.size();

        setOfletters.addAll(listOfLetters);
        listOfLettersUnic.addAll(setOfletters);

        ArrayList<String> arrayList2 = new ArrayList<>();

        arrayList2.addAll(listOfLetters);


        int cntUnic;
        String a1;
        String a2;

        for (String i : listOfLettersUnic) {
            cntUnic = 0;
            for (int j = 0; j < arrayList2.size(); j++) {


                a1 = i;
                a2 = listOfLetters.get(j);
                if (a1.equals(a2)) {

                    cntUnic++;
                    mapOfLetteres.put(i, cntUnic);
                }

            }
        }

        LinkedList<String> list7 = new LinkedList<>();
        list7.addAll(mapOfLetteres.keySet());
        LinkedList<Integer> list8 = new LinkedList<>();
        list8.addAll(mapOfLetteres.values());


        LinkedList<Boo> list9 = new LinkedList<>();

        float prs;

        for (int i = 0; i < list7.size(); i++) {

            prs=(list8.get(i)*100/(float)lengthOfLetters);
            list9.add(i, new Boo(list8.get(i), list7.get(i),prs));
        }

        Comparator<Boo> BooComparator = new Boo.BooValueComparator();  // если имена одинаковые второй вариант сравнивает по возрасту

        TreeSet<Boo> booBoo = new TreeSet<>(BooComparator);

        for (int i = 0; i < list9.size(); i++) {

            booBoo.add(list9.get(i));
        }

        for (Boo i: booBoo) {

            System.out.println(i);
        }

    }

}
