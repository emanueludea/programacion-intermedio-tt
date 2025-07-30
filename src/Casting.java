public class Casting {
    public static void main(String[] args) {
        String str = "80";
        int b = 60;
        byte a = (byte) b;
        System.out.println(a + " " + b);
        b= Integer.parseInt(str);
        float f = Float.parseFloat(str);
        System.out.println(b + " " + f);
    }
}
