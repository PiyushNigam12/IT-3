// Outer Class
public class Q1B {
    private int instanceVar = 10; // Instance variable
    private static int staticVar = 20; // Static variable

    // Static Nested Class
    static class StaticNestedClass {
        static {
            System.out.println("Static block in StaticNestedClass.");
        }

        {
            System.out.println("Instance block in StaticNestedClass.");
        }

        public StaticNestedClass() {
            System.out.println("Constructor in StaticNestedClass.");
        }

        public void accessOuterClass() {
            // Cannot access instance variables of OuterClass directly
            System.out.println("Accessing staticVar from OuterClass: " + staticVar);
            // OuterClass.this cannot be used in a static nested class
        }
    }

    // Final Nested Inner Class
    final class FinalInnerClass {
        final int finalVar = 50; // Final variable

        public FinalInnerClass() {
            System.out.println("Constructor in FinalInnerClass.");
        }

        public final void finalMethod() {
            System.out.println("Final method in FinalInnerClass. FinalVar: " + finalVar);
        }
    }

    // Override toString to demonstrate the use of this and super
    @Override
    public String toString() {
        return "OuterClass: instanceVar = " + this.instanceVar + ", staticVar = " + staticVar;
    }

    // Static method in OuterClass
    public static void staticMethod() {
        System.out.println("Static method in OuterClass.");
        System.out.println("Accessing staticVar: " + staticVar);
        // Cannot access instanceVar directly in a static method
        // System.out.println("Accessing instanceVar: " + instanceVar); // Error
    }

    // Synchronized block in OuterClass
    public void threadSafeAccess() {
        synchronized (OuterClass.class) {
            staticVar += 10;
            System.out.println("Thread-safe increment of staticVar: " + staticVar);
        }
    }

    public static void main(String[] args) {
        // Access static nested class
        StaticNestedClass staticNested = new StaticNestedClass();
        staticNested.accessOuterClass();

        // Create an instance of FinalInnerClass
        OuterClass outer = new OuterClass();
        FinalInnerClass inner = outer.new FinalInnerClass();
        inner.finalMethod();

        // Demonstrate toString and super
        System.out.println(outer.toString());

        // Demonstrate the static method
        OuterClass.staticMethod();

        // Demonstrate synchronized block
        outer.threadSafeAccess();
    }
}

// Utility Class
final class UtilityClass {
    private UtilityClass() {
        // Private constructor to prevent instantiation
    }

    public static void utilityMethod1() {
        System.out.println("Utility method 1.");
    }

    public static void utilityMethod2() {
        System.out.println("Utility method 2.");
    }
}
