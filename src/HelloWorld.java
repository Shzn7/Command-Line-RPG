public class HelloWorld {
    /*
      Use the method below to print out hello + your name!
      For example: "Hello Bernardo!"
     */
    public static void main(String[] args) {
        System.out.println(hello("Shaazaan"));
    }

    /**
     * Provides the user a warm welcome!
     * @param name of individual
     * @return "Hello <name>"
     */
    public static String hello(String name) {
        return "Hello " + name + "!";
    }
}
