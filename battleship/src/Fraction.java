import static java.lang.Math.*;

public class Fraction {
    private int numerator; private int denominator;

    public Fraction(int n, int d) {
        if(d < 0) {
            this.denominator = abs(d);
            this.numerator = -n;
        }
        else if(d == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0!");
        } else {
            this.numerator = n;
            this.denominator = d;
        }
    }
    public Fraction(int n) {
        this.numerator = n; this.denominator = 1;
    }
    public Fraction() {
        this.numerator = 0; this.denominator = 1;
    }

    public int getNumerator() {
        return this.numerator;
    }
    public int getDenominator() {
        return this.denominator;
    }
    public String toString() {
        return this.numerator+"/"+this.denominator;
    }
    public double toDouble() {
        return this.numerator/this.denominator;
    }
    private Fraction lcd(Fraction f) {
        int num = this.numerator * f.denominator;
        int den = this.denominator * f.denominator;
        f.numerator = f.numerator * this.denominator;
        f.denominator = f.denominator * this.denominator;
        this.numerator = num;
        this.denominator = den;
        return f;
    }
    private static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
    private Fraction simplify(Fraction f) {
        int gcd = gcd(f.numerator, f.denominator);
        f.numerator = f.numerator/gcd;
        f.denominator = f.denominator/gcd;
        return f;
    }
    public Fraction add(Fraction f) {
        lcd(f);
        f.numerator = this.numerator + f.numerator;
        return(this.simplify(f));
    }
    public Fraction sub(Fraction f) {
        lcd(f);
        f.numerator = this.numerator - f.numerator;
        return(this.simplify(f));
    }
    public Fraction mul(Fraction f) {
        f.numerator = f.numerator * this.numerator;
        f.denominator = f.denominator * this.denominator;
        return f;
    }
    public Fraction div(Fraction f) {
        int tmp = f.denominator * this.numerator;
        f.denominator = f.numerator * this.denominator;
        f.numerator = tmp;
        return f;
    }
    private void debug(Fraction f) {
        System.out.println("##############################");
        System.out.println("This num: "+this.numerator);
        System.out.println("This den: "+this.denominator);
        System.out.println("F num: "+f.numerator);
        System.out.println("F den: "+f.denominator);
    }
}