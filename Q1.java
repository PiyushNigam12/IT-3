class ClassA {
    private String varA; // Private variable

    // Parameterized constructor
    public ClassA(String varA) {
        this.varA = varA;
    }

    // Copy constructor
    public ClassA(ClassA obj) {
        this.varA = obj.varA;
    }

    // Getter and setter for varA
    public String getVarA() {
        return varA;
    }

    public void setVarA(String varA) {
        this.varA = varA;
    }
}

class ClassB extends ClassA {
    private String varB; // Private variable

    // Private constructor to prohibit direct object creation
    private ClassB(String varA, String varB) {
        super(varA); // Call ClassA constructor
        this.varB = varB;
    }

    // Copy constructor
    public ClassB(ClassB obj) {
        super(obj); // Call ClassA copy constructor
        this.varB = obj.varB;
    }

    // Getter and setter for varB
    public String getVarB() {
        return varB;
    }

    public void setVarB(String varB) {
        this.varB = varB;
    }

    // Factory method to create instances of ClassB
    public static ClassB createInstance(String varA, String varB) {
        return new ClassB(varA, varB);
    }
}

class ClassC extends ClassB {
    private String varC; // Private variable

    // Parameterized constructor
    public ClassC(String varA, String varB, String varC) {
        super(ClassB.createInstance(varA, varB)); // Use factory method to create ClassB
        this.varC = varC;
    }

    // Copy constructor
    public ClassC(ClassC obj) {
        super(obj); // Call ClassB copy constructor
        this.varC = obj.varC;
    }

    // Getter and setter for varC
    public String getVarC() {
        return varC;
    }

    public void setVarC(String varC) {
        this.varC = varC;
    }

    // Synchronized method to display variables
    public synchronized void displayVariables() {
        System.out.println("ClassA varA: " + getVarA());
        System.out.println("ClassB varB: " + getVarB());
        System.out.println("ClassC varC: " + varC);
    }
}

public class Q1 {
    public static void main(String[] args) {
        // Create objects using parameterized constructors
        ClassC objC1 = new ClassC("ValueA", "ValueB", "ValueC");

        // Display variables
        objC1.displayVariables();

        // Create a deep copy of objC1
        ClassC objC2 = new ClassC(objC1);

        // Modify objC2 and display variables
        objC2.setVarC("NewValueC");
        objC2.displayVariables();
    }
}
