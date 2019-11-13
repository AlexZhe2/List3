import java.util.Comparator;

public class Boo {

    Integer value;
    String word;
    Float percent;

    public Boo(Integer value, String word, Float percent) {
        this.value = value;
        this.word = word;
        this.percent = percent;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return

                "letter '" + word + '\'' +" value=" + value +
                ", percent=" + percent ;
    }


    static class BooValueComparator implements Comparator<Boo> {
        @Override
        public int compare(Boo o1, Boo o2) {

            return Integer.compare(o2.getValue(), o1.getValue());  //  сравнивает числа
        }
    }

}
