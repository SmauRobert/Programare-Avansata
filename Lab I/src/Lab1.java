/**
 * author: Smău George Robert 2A3
 */
public class Lab1 {
    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        lab1.compulsory();
    }

    void compulsory() {
        String[] languages = { "C", "C++", "C#", "Python", "Go", "Rust", "Javascript", "PHP", "Swift", "Java" };
        int n = (int)(Math.random() * 1_000_000);
        n *= 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        while(n > 9) {
            int newN = 0;
            while(n != 0) {
                newN += n % 10;
                n /= 10;
            }
            n = newN;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    void homework(int n, int k) {

    }

    void bonus() {

    }
}