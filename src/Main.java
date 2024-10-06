class Main {
    public static void main(String[] args) {
        StringProcessor sp = new StringProcessor();

        // Testing isStrongPassword
        System.out.println("Is this password strong: " + sp.isStrongPassword("Pa$sw0rd")); // true
        System.out.println("Is this password strong: " + sp.isStrongPassword("Password!")); // false, because no digit
        System.out.println("Is this password strong: " + sp.isStrongPassword("Passw0rd")); // false, because no special sign
        System.out.println("Is this password strong: " + sp.isStrongPassword("Pasword") + "\n"); // false, because length is less than 8

        // Testing calculateDigits
        System.out.println("The amount of digits is: " + sp.calculateDigits("The year is 2024")); // 4
        System.out.println("The amount of digits is: " + sp.calculateDigits("Hello world")); // 0, only words
        System.out.println("The amount of digits is: " + sp.calculateDigits("123456789") + "\n"); // 9

        // Testing calculateWords
        System.out.println("The amount of words in sentence is: " + sp.calculateWords("This is a test sentence.")); // 5
        System.out.println("The amount of words in sentence is: " + sp.calculateWords("123 123")); // 2, because 1 space
        System.out.println("The amount of words in sentence is: " + sp.calculateWords("") + "\n"); // empty

        // Testing calculateExpression
        System.out.println("The result of expression is: " + sp.calculateExpression("(2 + 3) * 4 / 2")); // 10.0
        System.out.println("The result of expression is: " + sp.calculateExpression("2 + 2 * 2")); // 6.0
    }
}