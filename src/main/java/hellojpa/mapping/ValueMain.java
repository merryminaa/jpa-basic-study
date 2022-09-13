package hellojpa.mapping;

public class ValueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = 10;
        System.out.println("a == b: " + (a == b)); //true

        Address address1 = new Address("Seoul", "star-ro","1000");
        Address address2 = new Address("Seoul", "star-ro","1000");

        System.out.println("address1 == address2: " + (address1 == address2)); //false
        System.out.println("address1 equals address2: " + (address1.equals(address2))); //true
        //Address 엔티티에서 equlas 메소드를 오버라이딩해서 재정의해두었기 때문(equals 기본은 == 비교여서 오버라이딩 필요)
    }
}
